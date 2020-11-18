package com.soyoung.photo.web;

import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/hunt")
public class HuntInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductionService productionService;

    //进行搜索
    @RequestMapping("/info")
    public String getInfo(String value, String type, Integer currPage, Integer pageSize, HttpServletRequest request){
        currPage=currPage==null ? 0:currPage;
        request.setAttribute("value",value);
        request.setAttribute("type",type);
        if("picture".equals(type)){
            type= Dict.PRODUCTION_TYPE_IMG;
            pageSize=Dict.HUNTIMGPAGESIZE;
            FreePage freePage=productionService.getSeekPro(value,Integer.parseInt(type),currPage,pageSize);
            request.setAttribute("proPage",freePage);
            return "seek/picture";
        }else if("video".equals(type)){
            type=Dict.PRODUCTION_TYPE_VIDEO;
            pageSize=Dict.HUNTVIDEOPAGESIZE;
            FreePage freePage=productionService.getSeekPro(value,Integer.parseInt(type),currPage,pageSize);
            request.setAttribute("proPage",freePage);
            return "seek/video";
        }else if("panorama".equals(type)){
            type=Dict.PRODUCTION_TYPE_360;
            pageSize=Dict.HUNT360PAGESIZE;
            FreePage freePage=productionService.getSeekPro(value,Integer.parseInt(type),currPage,pageSize);
            request.setAttribute("proPage",freePage);
            return "seek/panorama";
        }else{
            pageSize=Dict.HUNTUSERPAGESIZE;
            FreePage freePage=userService.getSeekUser(value,currPage,pageSize);
            request.setAttribute("userPage",freePage);
            return "seek/username";
        }
    }

    //进行ajax搜索
    @RequestMapping("/infoAjax")
    @ResponseBody
    public String getInfoAjax(String value, String type, Integer currPage, Integer pageSize){
        if("picture".equals(type)){
            type= Dict.PRODUCTION_TYPE_IMG;
            pageSize=Dict.HUNTIMGPAGESIZE;
            FreePage freePage=productionService.getSeekPro(value,Integer.parseInt(type),currPage,pageSize);
            return JSON.toJSONString(freePage.getList());
        }else if("video".equals(type)){
            type=Dict.PRODUCTION_TYPE_VIDEO;
            pageSize=Dict.HUNTVIDEOPAGESIZE;
            FreePage freePage=productionService.getSeekPro(value,Integer.parseInt(type),currPage,pageSize);
            return JSON.toJSONString(freePage.getList());
        }else if("panorama".equals(type)){
            type=Dict.PRODUCTION_TYPE_360;
            pageSize=Dict.HUNT360PAGESIZE;
            FreePage freePage=productionService.getSeekPro(value,Integer.parseInt(type),currPage,pageSize);
            return JSON.toJSONString(freePage.getList());
        }else{
            pageSize=Dict.HUNTUSERPAGESIZE;
            FreePage freePage=userService.getSeekUser(value,currPage,pageSize);
            return JSON.toJSONString(freePage.getList());
        }
    }
}
