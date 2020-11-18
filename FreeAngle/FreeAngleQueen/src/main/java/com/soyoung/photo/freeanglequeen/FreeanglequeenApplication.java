package com.soyoung.photo.freeanglequeen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.soyoung.photo.freeanglequeen.dao")
@EnableAsync
@EnableScheduling
public class FreeanglequeenApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeanglequeenApplication.class, args);
    }

}
