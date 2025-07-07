package org.example.chenduoduo.service;

import jakarta.annotation.Resource;
import org.example.chenduoduo.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate redisTemplate;
    @Test
    void test(){
        //增
        ValueOperations valueoptional=redisTemplate.opsForValue();
        valueoptional.set("luoString","aaa");
        valueoptional.set("luoInt",1);
        valueoptional.set("luoDouble",1.00);
        User user=new User();
        user.setId(1);
        user.setUserAccount("luo");
        valueoptional.set("luoUser",user);
        //查
        Object luo=valueoptional.get("luoString");
        Assertions.assertTrue("aaa".equals((String)luo));
        luo=valueoptional.get("luoInt");
        Assertions.assertTrue(1==(Integer)luo);
        luo=valueoptional.get("luoDouble");
        Assertions.assertTrue(1.00==(Double)luo);
        luo=valueoptional.get("luoUser");
        Assertions.assertTrue(user.equals(luo));
    }
}
