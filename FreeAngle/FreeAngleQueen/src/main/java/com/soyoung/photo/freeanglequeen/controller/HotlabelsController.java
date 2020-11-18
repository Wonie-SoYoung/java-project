package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soyoung.photo.freeanglequeen.entity.Hotlabels;
import com.soyoung.photo.freeanglequeen.service.HotlabelsService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/HotlabelsController")
public class HotlabelsController {

    @Resource
    private UploadingUtil uploadingUtil;
    @Resource
    public HotlabelsService labelTableService;

    /*
    * 级联二级查询，ajax
    * */
    @GetMapping("/selTwoLabel")
    @ResponseBody
    public List<Hotlabels> selTwoLabel(Integer hId){
        List<Hotlabels> list = labelTableService.selLabelTableListParentId(hId);
        return list;
    }

    /*
    * 标签信息添加
    * */
    @PostMapping("/toHotlabels-add-upd")
    @ResponseBody
    public String insHotlabels(String hName,String hUrl,Integer parentId,Integer ishot){
        Hotlabels hotlabels = new Hotlabels();
        hotlabels.sethName(hName);
        hotlabels.sethUrl(hUrl);
        hotlabels.setParentId(parentId);
        hotlabels.setIshot(ishot);
        Integer i = labelTableService.insHotlabels(hotlabels);
        if (i>0){
            return JSONObject.toJSONString(i);
        }else {
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 标签信息修改
    * */
    @RequestMapping(value = "toHotlabels-add-upd",method = RequestMethod.PUT)
    @ResponseBody
    public String updHotlabels(Integer hId,String hName,String hUrl,Integer parentId,Integer ishot){
        Hotlabels hotlabels = new Hotlabels();
        hotlabels.sethId(hId);
        hotlabels.sethName(hName);
        hotlabels.sethUrl(hUrl);
        hotlabels.setParentId(parentId);
        hotlabels.setIshot(ishot);
        Integer i = labelTableService.updHotlabelsHid(hotlabels);
        if (i>0){
            return JSONObject.toJSONString(i);
        }else{
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 标签图片文件上传*/
    @RequestMapping("file")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestParam MultipartFile[] file) throws IOException{
        //勋章上传路径
        String uploadPathOSS = "Free/Title/";
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
        //OSS服务器勋章图片路径 = OSS公共路径 + 图片名称 + ”.“ +图片后缀
        uploadPath = uploadPathOSS+uploadPathNewName+"."+suffix;
        //上传给OSS服务器
        uploadingUtil.uploadFile(f,uploadPath,request.getSession(),uploadPathNewName);
        resultMap.put("newName",uploadPathNewName);
        resultMap.put("proURL",uploadPath);
        String resultJSON = JSON.toJSONString(resultMap);
        return resultJSON;
    }

    /*
    * 获取勋章图片上传进度
    * */
    @RequestMapping("hotlabels/percent")
    @ResponseBody
    public String getUplodaPercent(String key,HttpServletRequest request){
        HttpSession session = request.getSession();
        String percent = session.getAttribute(key)==null?"":session.getAttribute(key).toString();
        return JSON.toJSONString(percent);
    }

    /*
    * 查询热门标签的数据条数 （前台热门标签的显示条数不得超过24条）
    * */
    @GetMapping("/selHotlabelsIshot")
    @ResponseBody
    public String selHotlabelsIshot(){
        Integer i = labelTableService.selHotlabelsIshot();
        return JSON.toJSONString(i);
    }

    /*
    * 删除标签
    * */
    @GetMapping("/delHotlabelsHid")
    @ResponseBody
    public String delHotlabelsHid(Integer hId){
        Integer i = labelTableService.delHotlabelsHid(hId);
        return JSON.toJSONString(i);
    }


}
