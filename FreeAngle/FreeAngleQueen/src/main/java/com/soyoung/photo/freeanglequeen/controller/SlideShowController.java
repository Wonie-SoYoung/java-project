package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSON;
import com.soyoung.photo.freeanglequeen.entity.SlideShow;
import com.soyoung.photo.freeanglequeen.service.SlideShowService;
import com.soyoung.photo.freeanglequeen.util.Note;
import com.soyoung.photo.freeanglequeen.util.file.FileUtil;
import com.soyoung.photo.freeanglequeen.util.file.UploadingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/*
* 范宏伦
* */

@Controller
@RequestMapping("/SlideShowController")
public class SlideShowController {
    @Autowired
    private UploadingUtil uploadingUtil;
    @Resource
    public SlideShowService slideShowService;

    /*
    * 根据 ID 修改轮播图信息
    * */
    @PutMapping("/toSlideShow-add-upd")
    public String updSlideShow(SlideShow slideShow){
        Integer i = slideShowService.updSlideShowIntId(slideShow);
        if (i>0){
            //修改成功
            return "redirect:/TablesController/toSlideShow";
        }else{
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    //选择文件进行跳转上传
    @RequestMapping("/file")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestParam MultipartFile[] file) throws IOException {
        //上传路径
        String objectName="Free/Carousel/";
        Map<String,Object> resultMap=new HashMap<>();
        String newName="";//保存文件名称
        String proURL="";//保存文件路径
            File f=null;
            if(file[0].equals("") || file[0].getSize()<=0){
                file[0]=null;
            }else{
                InputStream ins = file[0].getInputStream();
                f=new File(file[0].getOriginalFilename());
                FileUtil.inputStreamToFile(ins, f);
            }
            String suffix = file[0].getOriginalFilename().substring(file[0].getOriginalFilename().lastIndexOf(".") + 1);
            //保存文件名称
            newName=System.currentTimeMillis()+ Note.getNumber();
            proURL=objectName+newName+"."+suffix;
            uploadingUtil.uploadFile(f,proURL,request.getSession(),newName);
        resultMap.put("newName",newName);
        resultMap.put("proURL",proURL);
        String resultJSON= JSON.toJSONString(resultMap);
        System.out.println(resultJSON);
        return resultJSON;
    }
    /**
     * 获取实时长度进度
     */
    @RequestMapping("item/percent")
    @ResponseBody
    public String getUploadPercent(String key,HttpServletRequest request){
        HttpSession session = request.getSession();
        String percent = session.getAttribute(key) == null ? "": session.getAttribute(key).toString();
        return JSON.toJSONString(percent);
    }

    /*
    * 添加轮播图信息
    * */
    @PostMapping("/toSlideShow-add-upd")
    public String insSlideShowAddUpd(SlideShow slideShow){
        Integer i = slideShowService.insSlideShowInt(slideShow);
        if (i>0){
            //添加成功
            return "redirect:/TablesController/toSlideShow";
        }else{
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 根据 ID 删除轮播图信息
    * */
    @GetMapping("/delSlideShowInt/{sid}")
    public String delSlideShowInt(@PathVariable("sid") Integer sid,String picUrl){
        //删除服务器图片
        uploadingUtil.delUpload(picUrl);
        //删除数据库中轮播信息
        Integer i = slideShowService.delSlideShowInt(sid);
        if (i>0){
            //删除成功
            return "redirect:/TablesController/toSlideShow";
        }else{
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 查询轮播图的数据条数 （前台轮播图的显示条数不得超过7条）
    * */
    @GetMapping("/selSlideShowCount")
    @ResponseBody
    public String selSlideShowCount(){
        String json = JSON.toJSONString(slideShowService.selSlideShowCount());
        return json;
    }
}
