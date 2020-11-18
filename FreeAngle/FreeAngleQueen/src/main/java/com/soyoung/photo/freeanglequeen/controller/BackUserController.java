package com.soyoung.photo.freeanglequeen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.entity.State;
import com.soyoung.photo.freeanglequeen.service.StateService;
import com.soyoung.photo.freeanglequeen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 陈海嘉
 * */

@Controller
@RequestMapping("/back")
public class BackUserController {

    //注入后台的用户
    @Autowired
    private UserService userService;
    //查询地区
    @Autowired
    public StateService stateService;


    @GetMapping("/countUser")
    @ResponseBody
    public Map<String, Object> countUser(Model model){
        Map<String, Object> result = new HashMap<String, Object>();
        int userSum = userService.getUserSum();
        result.put("userSum",userSum);
        return result;
    }

    //查询所有的摄影师（用户）
    @RequestMapping("/userlist")
    public String userList(Integer currentPage, String userName, Date createTime,Date createTimeDao , Model model, HttpServletRequest request){
        Map<String, Object> map=new HashMap<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

        map.put("userName",userName);
        map.put("createTime",createTime!=null ? dateFormat.format(createTime) : null );
        map.put("createTimeDao",createTimeDao!=null ? dateFormat.format(createTimeDao) : null);
        PageHelper.startPage(currentPage == null ? 1 :currentPage,8);
        List<Map<String, Object>> maps = userService.everyDayUserPageHelper(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(maps);
        model.addAttribute("usermap",maps);
        model.addAttribute("pageInfo",pageInfo);
        return "/pages/tables/users-table";
    }

    //查询所有的摄影师（用户）
    @RequestMapping("/srcuserlist")
    public String srcuserList(Integer currentPage, String userName, Date createTime,Date createTimeDao , Model model, HttpServletRequest request){
        Map<String, Object> map=new HashMap<>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

        map.put("userName",userName);
        map.put("createTime",createTime!=null ? dateFormat.format(createTime) : null );
        map.put("createTimeDao",createTimeDao!=null ? dateFormat.format(createTimeDao) : null);
        PageHelper.startPage(currentPage == null ? 1 :currentPage,8);
        List<Map<String, Object>> maps = userService.everyDayUserPageHelper(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(maps);
        model.addAttribute("usermap",maps);
        model.addAttribute("pageInfo",pageInfo);
        return "/pages/tables/users-src";
    }


    @RequestMapping("/userByid/{id}")
    public String userById(@PathVariable("id") Integer id, Model model){
        Map<String, Object> userById = userService.getUserId(id);
        List<State> stateList = stateService.getStateList();
        model.addAttribute("stateList",stateList);
        model.addAttribute("userById",userById);
        return "pages/forms/users-add";
    }

    /**
     * 实现推荐用户和赋予达人操作
     */

    @RequestMapping("/tuijian")
    @ResponseBody
    public String tuiJian(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("ifrecommend",1);
        int i = userService.tuiJian(map);
        if (i>0){
            return "sourcess";
        }
        return "error";
    }

    @RequestMapping("/daren")
    @ResponseBody
    public String daRen(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("ifexpert",1);
        int i = userService.daRen(map);
        if (i>0){
            return "sourcess";
        }
        return "error";
    }

    /*
    * 封号处理*/
    @RequestMapping("/fengHao")
    @ResponseBody
    public String fengHao(Integer seal,Integer id){
        int i = userService.fengHao(seal, id);
        if (i>0){
            return "sourcess";
        }
        return "error";
    }
}
