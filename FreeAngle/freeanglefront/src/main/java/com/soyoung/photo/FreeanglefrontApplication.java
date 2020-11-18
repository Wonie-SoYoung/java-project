package com.soyoung.photo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.soyoung.photo.mapper")
@EnableAsync
public class FreeanglefrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeanglefrontApplication.class, args);
    }

}
