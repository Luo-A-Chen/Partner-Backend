//package org.example.chenduoduo.service;
//
//import org.junit.jupiter.api.Test;
//import org.redisson.api.RList;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class RedissonTest {
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @Test
//    public void test() {
//        RList<String> redis = redissonClient.getList("user");
//        redis.remove("1");
//        System.out.println(redis.get(0));
//    }
//}
