package org.example.chenduoduo.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.chenduoduo.mapper.UserMapper;
import org.example.chenduoduo.model.User;
import org.example.chenduoduo.service.UserService;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.Array;

/**
 * @author: chen_d9
 * @description:缓存预热任务
 * @date: 2023/3/27 21:05
 */
@Component
public class PreCacheJob {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Resource
    private ValueOperations<String, Object> redisTemplate;
    //重点用户
    private List<Long> mainUserList=Array.asList(1L);
    //每天23:59:00执行,预热推荐用户数据
    @Scheduled(cron = "0 59 23 * * ?")
    public void doCacheRecommendUser()
    {
        for (Long userId:mainUserList)
        {
            QueryWrapper<User> querWarpper=new QueryWrapper<>();
            Page<User> userpage=userService.page(new Page<>(1,20),querWarpper);
            String redisKey=String.format("chenduoduo:user:recommed:%s",userId);
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(redisKey,userId,30000, TimeUnit.MILLISECONDS);
        }
    }

}
