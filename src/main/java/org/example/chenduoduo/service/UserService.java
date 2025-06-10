package org.example.chenduoduo.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.chenduoduo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author luochen
 * @description 针对表user的服务类
 * @createDate 2025-04-08 15:43:08
 */
public interface UserService extends IService<User> {

    /**
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     * @param planetCode
     * @return输入账户，密码，校验码，星球编号
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * @param userAccount
     * @param userPassword
     * @return登录功能的实现，输入账户密码
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
    /**
     * 根据标签搜索用户
     * @param tagNameList
     * @return
     */
    List<User> searchUsersByTags(List<String> tagNameList);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    Integer  updateUser(User user,User userLogin);
    /**
     * 获取当前用户登陆信息,校验权限
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);
    /**
     * 是否为管理员
     */
    boolean isAdmin(HttpServletRequest request);
    boolean isAdmin(User user);
}
