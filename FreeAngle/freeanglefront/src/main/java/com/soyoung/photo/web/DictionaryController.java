package com.soyoung.photo.web;


import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.util.FreePage;
import com.soyoung.photo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    @RequestMapping("/hello")
    @ResponseBody
    public String get(){
        return "我是8080服务器";
    }
}
