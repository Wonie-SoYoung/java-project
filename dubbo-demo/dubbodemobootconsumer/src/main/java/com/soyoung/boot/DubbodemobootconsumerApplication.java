package com.soyoung.boot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class DubbodemobootconsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubbodemobootconsumerApplication.class, args);
    }

}
