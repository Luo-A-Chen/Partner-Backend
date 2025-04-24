package org.example.center02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.center02.mapper")
public class Center02Application {

    public static void main(String[] args) {
        SpringApplication.run(Center02Application.class, args);
    }

}
