package org.example.chenduoduo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.example.chenduoduo.common.BaseResponse;
import org.example.chenduoduo.common.ErrorCode;
import org.example.chenduoduo.common.ResultUtils;
import org.example.chenduoduo.exception.BusinessException;
import org.example.chenduoduo.model.User;
import org.example.chenduoduo.model.request.UserLoginRequest;
import org.example.chenduoduo.model.request.UserRegisterRequest;
import org.example.chenduoduo.service.UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import static org.example.chenduoduo.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author luochen
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:5173"},allowCredentials = "true")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result= userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user= userService.userLogin(userAccount,userPassword,request);
        return ResultUtils.success(user);
    }

    /**
     * 注销
     */
    @PostMapping("/logout")
    public BaseResponse<Integer>usersLogout(HttpServletRequest request){
        if(request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result=userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long userId = currentUser.getId();
        //todo校验用户是否合法，登录态是否是管理员状态
        User user= userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request){
        //仅管理员可以查询
        if(!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userlist = userService.list(queryWrapper);
        List<User> list = userlist.stream().map(user -> {
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());
        return ResultUtils.success(list);
    }
    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList){
        if(CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList =userService.searchUsersByTags(tagNameList);
        return ResultUtils.success(userList);
    }
    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommendUsers(int pageSize,int pageNum,HttpServletRequest request){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //对当前用户进行分页处理
        Page<User> userList = userService.page(new Page<>(pageNum,  pageSize), queryWrapper);
        return ResultUtils.success(userList);
    }
    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user,HttpServletRequest request){
        //校验参数是否为空
        if(user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //校验权限
        User loginUser = userService.getLoginUser(request);
        int userUpdate = userService.updateUser(user,loginUser);
        return ResultUtils.success(userUpdate);

    }
    @GetMapping("/delete")
    public BaseResponse<Boolean>deleteUser(@RequestBody Long id,HttpServletRequest request){
        //仅管理员可以查询
        if(!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        if(id <=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean byId = userService.removeById(id);
        return ResultUtils.success(byId);
    }
}
