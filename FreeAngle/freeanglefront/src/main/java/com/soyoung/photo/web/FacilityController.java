package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 设备类型表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Controller
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    //请求父节点摄影设备
    @PostMapping("/getFather")
    @ResponseBody
    public String getFather(){
        String resultJSON= JSON.toJSONString(facilityService.getFatherFacilityList());
        return resultJSON;
    }

    //请求子节点摄影设备
    @PostMapping("/getChild/{id}")
    @ResponseBody
    public String getChild(@PathVariable("id")Integer id){
        String resultJSON=JSON.toJSONString(facilityService.getisFidThenChildFacilityList(id));
        return resultJSON;
    }
}
