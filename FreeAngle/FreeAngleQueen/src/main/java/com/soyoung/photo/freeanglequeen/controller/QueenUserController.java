package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.entity.QueenUser;
import com.soyoung.photo.freeanglequeen.service.ProductionService;
import com.soyoung.photo.freeanglequeen.service.QueenUserService;
import com.soyoung.photo.freeanglequeen.service.UserService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 陈海嘉
* */

@Controller
@RequestMapping("/queen") //后台账户控制器
public class QueenUserController {
    @Autowired
    private QueenUserService queenUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductionService productionService;
    @Resource
    private UploadingUtil uploadingUtil;

    @RequestMapping("/Login")
    public String queenLogin(QueenUser queenUser, Model model, HttpSession session){
        QueenUser queenUser1 = queenUserService.getUserByPwd(queenUser);
        if (queenUser.getUserName()==null || queenUser.getUserName()==""){
            model.addAttribute("error","账号不能为空");
            return "login";
        }
        if (queenUser.getPassword()==null || queenUser.getPassword()==""){
            model.addAttribute("error","密码不能为空");
            return "login";
        }
        if (queenUser1==null){
            model.addAttribute("error","账号或者密码错误");
            return "login";
        }
        session.setAttribute("queenUser",queenUser1);
        return "redirect:/faceLogin/home";
    }

    //用来做首页第一个大报表的Controller
    @RequestMapping("/echartsList")
    @ResponseBody
    public Map<String,Object> echartsList(){
        Map<String,Object> map = new HashMap<>();
        //查询作品的信息
        List<Map<String, Object>> productionByCreatetime = productionService.getProductionByCreatetime();
        //Map里面应该放入，逻辑判前断后的数据，前台可以直接渲染
        map.put("productionByCreatetime",productionByCreatetime);
        return map;
    }

    //后台用户分页查询
    @GetMapping("/selQueenUser")
    public String selQueenUser(Integer currentPage, String userName, String phone, String ustart, Date createTime, Date createTimeDao, Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        map.put("phone",phone);
        map.put("ustart",ustart);
        map.put("createTime",createTime);
        map.put("createTimeDao",createTimeDao);
        map.put("currentPage",currentPage==null ? 1:currentPage);
        PageHelper.startPage(currentPage == null ? 1 :currentPage,6);
        List<Map<String, Object>> queenUserList = queenUserService.getQueenUserList(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(queenUserList);
        model.addAttribute("queenUserList",queenUserList);
        model.addAttribute("pageInfo",pageInfo);
        return "pages/queen/queenUser";
    }

    @RequestMapping("/gopersonalCenter")
    public String gopersonalCenter(HttpSession session,Model model){
        QueenUser queenUser = (QueenUser) session.getAttribute("queenUser");
        try {
            QueenUser queenUser1 = queenUserService.getFaceUrlByIdQueenUser(queenUser.getId());
            model.addAttribute("queenUser",queenUser1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/pages/queen/personalCenter";
    }

    @RequestMapping("/checkupdQueenUser/{password}")
    @ResponseBody
    public String checkupdQueenUser(@PathVariable("password") String password, HttpSession session){
        QueenUser queenUser = (QueenUser) session.getAttribute("queenUser");
        if (password.equals(queenUser.getPassword())){
            return "true";
        }
        return "false";
    }

    @PutMapping("/updQueenUser")
    private String updQueenUser(String pwd,HttpSession session,Model model){
        QueenUser queenUser = (QueenUser) session.getAttribute("queenUser");
        Integer id = queenUser.getId();
        try {
            int i = queenUserService.updPwd(id, pwd);
            String error="";
            if (i<1){
                error="修改失败";
            }
            model.addAttribute("error",error);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "login";
    }

    @GetMapping("/addQueenUser")
    public String addQueenUser(){
        return "/pages/queen/addQueenUser";
    }

    @PostMapping("addQueenUser")
    public String addQueenUser(QueenUser queenUser){
        try {
            queenUserService.addQueenUser(queenUser);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/queen/selQueenUser";
    }

    @RequestMapping("/delQueenUser/{id}")
    public String delQueenUser(@PathVariable("id") Integer id,Model model){
        System.out.printf("进入");
        int i = queenUserService.delQueenUser(id);
       /* if (i>0){

        }*/
        return  "redirect:/queen/selQueenUser";
    }

    @RequestMapping("/queenById/{id}")
    @ResponseBody
    public QueenUser getQueenById(@PathVariable("id") Integer id,Model model){
        QueenUser faceUrlByIdQueenUser = queenUserService.getFaceUrlByIdQueenUser(id);

        return  faceUrlByIdQueenUser;
    }


    /*
    * 范宏伦
    * */

    //选择文件进行跳转上传
    @RequestMapping("/file")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestParam MultipartFile[] file,Integer queenUserId) throws IOException {
        //上传路径
        String objectName="Free/User/People/";
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
        try {
            uploadingUtil.uploadFile(f,proURL,request.getSession(),newName);
            //执行修改数据库头像操作
            queenUserService.updQueenUserIdHeadURL(proURL,queenUserId);
        }catch (Exception e){
            return "redirect:/TablesController/toError500Page";
        }
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



}
