package org.example.chenduoduo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("org.example.chenduoduo.mapper")
@EnableScheduling
public class chenduoduoApplication {

    public static void main(String[] args) {
        SpringApplication.run(chenduoduoApplication.class, args);
    }

}
