package com.soyoung.web.controller;

import com.soyoung.common.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.soyoung.core.service.UserService;
import org.springframework.web.bind.annotation.RestController;

/**
*  前端控制器
*/
@RestController
@RequestMapping("/abt/user")
public class UserController {
    @Autowired
    private UserService userService;

}
