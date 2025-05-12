package org.example.chenduoduo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.chenduoduo.mapper")
public class chenduoduoApplication {

    public static void main(String[] args) {
        SpringApplication.run(chenduoduoApplication.class, args);
    }

}
