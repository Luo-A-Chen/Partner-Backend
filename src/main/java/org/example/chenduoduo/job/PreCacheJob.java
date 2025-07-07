package org.example.chenduoduo.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.chenduoduo.model.domain.User;
import org.example.chenduoduo.service.UserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: chen_d9
 * @description:缓存预热任务
 * @date: 2023/3/27 21:05
 */
@Component
public class PreCacheJob {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RedissonClient redissonClient;
    //重点用户
    private List<Long> mainUserList=Arrays.asList(1L);
    //每天23:59:00执行,预热推荐用户数据
    @Scheduled(cron = "0 59 23 * * ?")
    public void doCacheRecommendUser()
    {
        RLock lock = redissonClient.getLock("preCacheJob:doCacheRecommendUser:lock");
        try {
            if(lock.tryLock(0,30000L, TimeUnit.MILLISECONDS)){
                for (Long userId:mainUserList)
                {
                    QueryWrapper<User> querWarpper=new QueryWrapper<>();
                    Page<User> userpage=userService.page(new Page<>(1,20),querWarpper);
                    String redisKey=String.format("chenduoduo:user:recommed:%s",userId);
                    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
                    try {
                        valueOperations.set(redisKey,userId,30000, TimeUnit.MILLISECONDS);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
