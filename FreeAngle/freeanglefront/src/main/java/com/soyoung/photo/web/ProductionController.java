package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.Attention;
import com.soyoung.photo.entity.Production;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.*;
import com.soyoung.photo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 作品表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Controller
@RequestMapping("/production")
public class ProductionController {
    @Autowired
    private ProductionService productionService;
    @Autowired
    private HotlabelsService hotlabelsService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private SlideshowService slideshowService;

    //主页所需数据
    @RequestMapping("/home")
    public String getHome(HttpServletRequest request){
        //添加热门作品
        for (String hot: Dict.HOTLABEL.keySet()){
            FreePage freePage=productionService.gethomeProListPage(Dict.HOTLABEL.get(hot),Dict.HOMEPAGESIZE);
            request.setAttribute("homePage"+hot,freePage);
        }
        //添加热门标签
        request.setAttribute("slideshowList",slideshowService.getSlideshowList());
        request.setAttribute("hotTitle",hotlabelsService.getHotHomeList(Dict.HOMEHOTTITMESIZE));
        request.setAttribute("headType",1);
        return "home";
    }

    //条件查询（标签、名称、类型）作品
    @GetMapping("/getProList")
    public String getProList(String label,String pName,Integer proType,Integer currPage,Integer pageSize,HttpServletRequest request){
        //验证数据
        label=StringUtils.isEmpty(label) ? null:label;
        pName=StringUtils.isEmpty(pName) ? null:pName;
        proType=StringUtils.isEmpty(proType) ? null:proType;
        currPage=currPage==null||currPage==0 ? 0:currPage;
        pageSize=pageSize==null||pageSize==0 ? Dict.PROTERMSIZE:pageSize;
        //查询操作
        request.setAttribute("proPage",productionService.getTermProListPage(label,pName,proType,currPage,pageSize));
        request.setAttribute("label",label);
        request.setAttribute("headType",2);
        return "explore";
    }

    //条件查询（标签、名称、类型）作品JSON
    @PostMapping("/getProList")
    @ResponseBody
    public String postProList(String label,String pName,Integer proType,Integer currPage,Integer pageSize,HttpServletRequest request){
        //验证数据
        label=StringUtils.isEmpty(label) ? null:label;
        pName=StringUtils.isEmpty(pName) ? null:pName;
        proType=StringUtils.isEmpty(proType) ? null:proType;
        currPage=currPage==null||currPage==0 ? 0:currPage;
        pageSize=pageSize==null||pageSize==0 ? Dict.PROTERMSIZE:pageSize;
        String str= JSON.toJSONString(productionService.getTermProListPage(label,pName,proType,currPage,pageSize).getList());
        return str;
    }

    //作品详情
    @RequestMapping("/getPro/{pid}")
    public String getPro(@PathVariable("pid") Integer pid,HttpServletRequest request){
        Production production=productionService.getisByPro(pid);
        request.setAttribute("pro",production);
        request.setAttribute("commentPage",commentService.getProIsCommentPage(pid,1));
        request.setAttribute("proLikeList",productionService.getIdIsLikeByPros(production.getLabels(),pid));
        productionService.updateviewNum(pid);
        return "demonstrate";
    }

    //添加作品
    @PostMapping("/addPro")
    public String addPro(@PathVariable("file") MultipartFile file,Production production, String fileUrl, HttpServletRequest request)throws IOException {
        //作品上传所需数据
        User user=null;
        String hiapkURL=null;
        String duration=null;
        try{
            user=(User) request.getSession().getAttribute("user");
        }catch (Exception e){
            e.printStackTrace();
            throw new NullPointerException();
        }
        if(production.getProtypeId()==Integer.parseInt(Dict.PRODUCTION_TYPE_IMG)){
            //作品类型为IMG
            hiapkURL=production.getProURL().split("\\+")[0];
        }else if(production.getProtypeId()==Integer.parseInt(Dict.PRODUCTION_TYPE_VIDEO)){
            //作品类型为Video
            File f=null;
            if(!StringUtils.isEmpty(production.getHiapkURL())){
                hiapkURL=production.getHiapkURL();
            }else{
                //准备上传视频背景
                if(file.equals("") || file.getSize()<=0){
                    file=null;
                }else{
                    InputStream ins = file.getInputStream();
                    f=new File(file.getOriginalFilename());
                    FileUtil.inputStreamToFile(ins, f);
                }
                String objectName=System.currentTimeMillis()+ Note.getNumber()+".png";
                hiapkURL= VideoResolverUtil.getVideoPic(f,objectName);
                duration=VideoResolverUtil.getVideoDuration(f);
            }
        }else if(production.getProtypeId()==Integer.parseInt(Dict.PRODUCTION_TYPE_360)){
            //作品类型为360全景图
        }
        //封装数据
        production.setUid(user.getId());
        production.setHiapkURL(hiapkURL);
        production.setDuration(duration);
        production.setProURL(production.getProURL());
        Integer pid=productionService.insertPro(production);
        return "redirect:/production/getPro/"+pid;
    }

    //删除作品
    @GetMapping("/del/{pid}")
    @ResponseBody
    public String delPro(@PathVariable("pid") Integer pid,HttpServletRequest request){
        if(productionService.delProduction(pid)){
            return JSON.toJSONString(((User)request.getSession().getAttribute("user")).getId());
        }else{
            return JSON.toJSONString(0);
        }
    }

    //修改作品
    @PostMapping("/update")
    public String updateProduction(Production production){
        if(productionService.updateProduction(production)){
            return "redirect:/production/getPro/"+production.getId();
        }else{
            return "redirect:/upload/update/"+production.getId();
        }
    }

    //关注用户的作品
    @GetMapping("/focuspro")
    @ResponseBody
    public String getFocusPro(Integer currPage,HttpServletRequest request){
        currPage=currPage==null ? 0:currPage;
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        List<Integer> uidList=new ArrayList<>();
        for (Attention attention:
             attentionService.getAllIsUidList(uid)) {
            uidList.add(attention.getByuid());
        }
        return JSON.toJSONStringWithDateFormat(productionService.getFocusProductionPage(uidList,currPage).getList(),"yyyy年MM月dd日");
    }

}
