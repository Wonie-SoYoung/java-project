package com.soyoung.web.controller;

import com.soyoung.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: framework
 * @description: asd
 * @author: 梅晓寒
 * @create: 2020-08-07 20:25
 **/
@RestController
@RequestMapping("/soyoung")
public class ApiTest {
    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(getClass());
    @GetMapping("/hello")
    public String hello(){
        log.trace("trace日志");
        log.debug("debug日志");
        log.info("info日志");
        log.warn("warn日志");
        log.error("error日志");
        System.out.println(userService.listUser(0,10));
        return "SoYoung软件科技有限公司欢迎您";
    }
}
