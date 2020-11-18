package com.soyoung.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.soyoung")
@MapperScan(basePackages = "com.soyoung.*.dao")
@EnableTransactionManagement
@EnableSwagger2
public class SsmMysqlProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmMysqlProjectApplication.class, args);
    }

}
