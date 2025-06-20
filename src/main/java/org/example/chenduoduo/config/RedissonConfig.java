package org.example.chenduoduo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * redisson 配置类
 * @author chenduoduo
 */
@Configuration
public class RedissonConfig {
    //连接redis 的配置类
    @Bean
    public RedissonClient redissonClient() {
        Config config=new Config();
        String redisAddress="redis://127.0.0.1:6379";
        config.useSingleServer().setAddress(redisAddress).setDatabase(3);

        RedissonClient redisson= Redisson.create(config);
        return redisson;
    }
}
