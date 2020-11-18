package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.AttentionService;
import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.service.StateService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private StateService stateService;
    @Autowired
    private UploadingUtil uploadingUtil;

    //查询摄影师列表
    @RequestMapping("/getUserList")
    public String getUserList(Integer type, Integer currPage, HttpServletRequest request){
        type=type==null ? 3:type;
        currPage=currPage==null ? 1:currPage;
        FreePage freePage=userService.getUserList(type,currPage);
        request.setAttribute("userPage",freePage);
        request.setAttribute("type",type);
        request.setAttribute("headType",4);
        return "photographer";
    }

    //查询摄影师
    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable("id") Integer id,String type,HttpServletRequest request){
        FreePage freePage=null;
        if("focus".equals(type)){
            freePage=attentionService.selFocusPageList(id,0);
            request.setAttribute("focusPage",freePage);
        }else if("fans".equals(type)){
            freePage=attentionService.selFansPageList(id,0);
            request.setAttribute("fansPage",freePage);
        }
        User user=userService.getUserInById(id);
        request.setAttribute("userInfo",user);
        freePage=productionService.getProByUserPage(id,0, Dict.USERINPROSIZE);
        request.setAttribute("proPage",freePage);
        return "personal";
    }

    //ajax查询摄影师作品列表
    @PostMapping("/getPro")
    @ResponseBody
    public String getPro(Integer id,Integer currPage,Integer pageSize){
        FreePage freePage=productionService.getProByUserPage(id,currPage, pageSize);
        return JSON.toJSONString(freePage.getList());
    }

    //Ajax请求session的User
    @RequestMapping("/sessionUser")
    @ResponseBody
    public String getUser(HttpServletRequest request){
        return JSON.toJSONString(request.getSession().getAttribute("user"));
    }

    //进入修改页面
    @RequestMapping("/getalert")
    public String getalertHtml(HttpServletRequest request){
        request.setAttribute("user",userService.selectById(((User)request.getSession().getAttribute("user")).getId()));
        request.setAttribute("stateList",stateService.getStateList());
        return "alterpersonal";
    }

    //修改用户
    @PostMapping("/updateuser")
    public String addPro(@PathVariable("file") MultipartFile file, User user,HttpServletRequest request){
        user.setUpdateTime(new Date());
        Boolean isNot=false;
        String objectName=user.getHeadURL();
        String newObjectName=null;
        if(file!=null && !file.equals("") && file.getSize()>0){
            //作品上传所需数据
            File f=null;
            InputStream ins = null;
            try {
                ins = file.getInputStream();
                f=new File(file.getOriginalFilename());
                FileUtil.inputStreamToFile(ins, f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newObjectName=Dict.OBJECTFILEUSERBEAD+System.currentTimeMillis()+ Note.getNumber()+".png";
            uploadingUtil.uploadNullSyncFile(f,newObjectName);
            user.setHeadURL(newObjectName);
            isNot=true;
        }
        if(userService.updateUser(user)){
            if(isNot){
                if(!Dict.DEFAULTHEADURL.equals(objectName)){
                    uploadingUtil.delUpload(objectName);
                }
                request.getSession().setAttribute("user",userService.selectById(user.getId()));
            }
        }
        return "redirect:/user/getUser/"+user.getId();
    }
}
