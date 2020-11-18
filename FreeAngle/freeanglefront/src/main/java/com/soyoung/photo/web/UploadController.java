package com.soyoung.photo.web;

import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.Production;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.HotlabelsService;
import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FileUtil;
import com.soyoung.photo.util.Note;
import com.soyoung.photo.util.UploadingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadingUtil uploadingUtil;
    @Autowired
    private HotlabelsService hotlabelsService;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private UserService userService;

    //跳转上传
    @RequestMapping("/show/{type}")
    public String getUploadView(@PathVariable("type") Integer type, HttpServletRequest request){
        request.setAttribute("type",type);
        request.setAttribute("labels",hotlabelsService.getSuggestLabelsList());
        return "uploading";
    }

    //选择文件进行跳转上传
    @RequestMapping("/file")
    @ResponseBody
    public String uploadFile(HttpServletRequest request,@RequestParam MultipartFile[] file,String type) throws IOException {
        //上传路径
        String objectName="";
        if(Dict.PRODUCTION_TYPE_IMG.equals(type)){
            objectName=Dict.OBJECTFILEIMG;
        }else if(Dict.PRODUCTION_TYPE_VIDEO.equals(type)){
            objectName=Dict.OBJECTFILEVIDEO;
        }else if(Dict.PRODUCTION_TYPE_360.equals(type)){
            objectName=Dict.OBJECTFILE360;
        }else if((Dict.PRODUCTION_TYPE_VIDEO+"change").equals(type)){
            objectName=Dict.OBJECTFILEVIDEOCHANGE;
        }else if((Dict.PRODUCTION_TYPE_360+"change").equals(type)){
            objectName=Dict.OBJECTFILE360CHANGE;
        }
        Map<String,Object> resultMap=new HashMap<>();
        List<String> newNames=new ArrayList<>();   //保存文件名称
        List<String> proURLs=new ArrayList<>();    //保存文件路径
        for(MultipartFile multipartFile:file){
            File f=null;
            if(multipartFile.equals("") || multipartFile.getSize()<=0){
                multipartFile=null;
            }else{
                InputStream ins = multipartFile.getInputStream();
                f=new File(multipartFile.getOriginalFilename());
                FileUtil.inputStreamToFile(ins, f);
            }
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            //保存文件名称
            String newName=System.currentTimeMillis()+ Note.getNumber();
            String newobjectName=objectName+newName+"."+suffix;
            uploadingUtil.uploadFile(f,newobjectName,request.getSession(),newName);
            newNames.add(newName);
            proURLs.add(newobjectName);
        }
        resultMap.put("type",type);
        resultMap.put("newNames",newNames);
        resultMap.put("proURLs",proURLs);
        String resultJSON=JSON.toJSONString(resultMap);
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

    /**
     * 重置上传进度
     */
    @RequestMapping ("/percent/reset")
    public void resetPercent(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("upload_percent","");
    }

    /**
     * 删除文件
     */
    @PostMapping("delFile")
    @ResponseBody
    public String delUpload(String fileKey){
        boolean isNot=false;
        try{
            uploadingUtil.delUpload(fileKey);
            isNot=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(isNot);
    }

    //跳转修改作品
    @GetMapping("/update/{pid}")
    public String getupdateHtml(@PathVariable("pid") Integer pid,HttpServletRequest request){
        //查询作品
        Production production=productionService.getisByPro(pid);
        //封装URL
        production.setProURLs(production.getProURL().split("\\+"));
        //封装标签
        production.setLabels(production.getLabel().split("\\+"));
        request.setAttribute("production",production);
        request.setAttribute("labels",hotlabelsService.getSuggestLabelsList());
        return "updateisuploading";
    }

    //修改用户背景
    @PostMapping("/alterbackdrop")
    @ResponseBody
    public String updatebackdrop(HttpServletRequest request,@RequestParam MultipartFile file,String fileUrl) throws IOException {
        //上传路径
        String objectName=Dict.OBJECTFILEUSERBACKDROP;
        //封装信息
        Map<String,Object> resultMap=new HashMap<>();
        String resultJSON=null;
        File f=null;
        InputStream ins = file.getInputStream();
        f=new File(file.getOriginalFilename());
        FileUtil.inputStreamToFile(ins, f);
        User user=new User();
        user.setId(((User)request.getSession().getAttribute("user")).getId());
        //获取后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String newName=System.currentTimeMillis()+ Note.getNumber();//保存文件名称
        String newobjectName=objectName+newName+"."+suffix;         //保存文件路径
        //保存文件
        uploadingUtil.uploadNullFile(f,newobjectName);
        if(!Dict.DEFAULTBACKURL.equals(user.getBackgroundURL())){
            //删除原有文件
            uploadingUtil.delUpload(fileUrl);
        }
        user.setBackgroundURL(newobjectName);
        //执行修改
        try{
            if(userService.updateUserIshiapkURL(user)>0){
                //保存数据
                resultMap.put("newName",newName);
                resultMap.put("newobjectName",newobjectName);
                resultJSON=JSON.toJSONString(resultMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultJSON;
    }
}
