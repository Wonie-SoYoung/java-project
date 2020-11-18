package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSON;
import com.soyoung.photo.freeanglequeen.entity.Medal;
import com.soyoung.photo.freeanglequeen.service.MedalService;
import com.soyoung.photo.freeanglequeen.util.Note;
import com.soyoung.photo.freeanglequeen.util.file.FileUtil;
import com.soyoung.photo.freeanglequeen.util.file.UploadingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* 范宏伦
* */

@Controller
@RequestMapping("/MedalController")
public class MedalController {

    @Resource
    private UploadingUtil uploadingUtil;
    @Resource
    public MedalService medalService;

    //获取当前系统时间
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
    * 勋章信息添加
    * */
    @PostMapping("/toMedal-add-upd")
    public String insMedalAddUpd(Medal medal){
        medal.setCreateTime(sdf.format(new Date()));//获取当前系统时间为创建时间
        Integer i = medalService.insMedal(medal);
        if (i>0){
            return "redirect:/TablesController/toMedal-table";
        }else{
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 勋章信息修改
    * */
    @PutMapping("/toMedal-add-upd")
    public String updMedalAddUpd(Medal medal){
        medal.setUpdateTime(sdf.format(new Date()));//获取当前时间为修改时间
        Integer i = medalService.updMedalMid(medal);
        if (i>0){
            return "redirect:/TablesController/toMedal-table";
        }else{
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 勋章图片上传
    * */
    @RequestMapping("/file")
    @ResponseBody
    public String uploadMedalFile(HttpServletRequest request, @RequestParam MultipartFile[] file) throws IOException {
        //勋章上传路径
        String uploadPathOSS = "Free/Medal/";
        Map<String,Object> resultMap = new HashMap<>();
        String uploadPathNewName = "";//OSS服务器勋章图片名称
        String uploadPath = "";//OSS服务器勋章图片路径
        File f = null;
        if (file[0].equals("") || file[0].getSize()<=0){
            file[0]=null;
        }else{
            InputStream inputStream = file[0].getInputStream();
            f = new File(file[0].getOriginalFilename());
            FileUtil.inputStreamToFile(inputStream,f);
        }
        String suffix = file[0].getOriginalFilename().substring(file[0].getOriginalFilename().lastIndexOf(".")+1);
        //保存文件名称
        uploadPathNewName = System.currentTimeMillis()+ Note.getNumber();
        //OSS服务器勋章图片路径 = OSS公共路径 + 图片名称 + “.”+ 图片后缀
        uploadPath = uploadPathOSS+uploadPathNewName+"."+suffix;
        //上传给OSS服务器
        uploadingUtil.uploadFile(f,uploadPath,request.getSession(),uploadPathNewName);
        resultMap.put("newName",uploadPathNewName);
        resultMap.put("proURL",uploadPath);
        String resultJSON = JSON.toJSONString(resultMap);
        System.out.println(resultJSON);
        return resultJSON;
    }

    /*
    * 获取图片上传进度
    * */
    @RequestMapping("medal/percent")
    @ResponseBody
    public String getUplodaPercent(String key,HttpServletRequest request){
        HttpSession session = request.getSession();
        String percent = session.getAttribute(key)==null?"":session.getAttribute(key).toString();
        return JSON.toJSONString(percent);
    }
}
