package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSON;
import com.soyoung.photo.freeanglequeen.entity.User;
import com.soyoung.photo.freeanglequeen.service.ReportService;
import com.soyoung.photo.freeanglequeen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ReportController")
public class ReportController {

    @Resource
    public ReportService reportService;
    @Resource
    public UserService userService;

    /*
    * ajax 根据用户 ID 查询用户信息
    * */
    @GetMapping("/selUserId")
    @ResponseBody
    public String selUserId(Integer userId){
        Map<String,Object> map = new HashMap<>();
        User user = userService.selUserId(userId);
        map.put("a",user);
        String jsonUser = JSON.toJSONString(user);
        System.out.println(jsonUser);
        return jsonUser;
    }


}
