package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.config.WebSocket;
import com.soyoung.photo.entity.Collect;
import com.soyoung.photo.entity.Messages;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.CollectService;
import com.soyoung.photo.service.MessagesService;
import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-28
 */
@Controller
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private WebSocket webSocket;

    //执行收藏
    @GetMapping("/execute")
    @ResponseBody
    public String addCollect(Collect collect, HttpServletRequest request){
        collect.setUid(((User)request.getSession().getAttribute("user")).getId());
        Integer isNot=collectService.addUserIsCollect(collect);
        try{
            if(isNot>0){
                //收藏成功后添加消息
                Integer byuid=userService.getProIsIdByUser(collect.getPid()).getId();
                Messages messages=new Messages(collect.getUid(),byuid, Dict.NEWSTYPECOLLECT,collect.getCid(),collect.getPid());
                if(messagesService.addMessages(messages)){
                    webSocket.sendMessage(byuid+"+"+Dict.NEWSTYPECOLLECT);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(isNot);
    }

    //取消收藏
    @GetMapping("/cancel")
    @ResponseBody
    public String delCollect(Integer pid,HttpServletRequest request){
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        return JSON.toJSONString(collectService.delUserIsCollect(uid,pid));
    }

    //查询收藏下的作品
    @RequestMapping("/collectpro/{currPage}")
    public String getCollectPro(@PathVariable("currPage") Integer currPage, HttpServletRequest request){
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        request.setAttribute("collectProPage",productionService.getCollectProductionPage(uid,currPage));
        return "collect";
    }

    //ajax查询收藏下的作品
    @ResponseBody
    @RequestMapping("/ajaxcollectpro")
    public String getAJAXCollectPro(Integer currPage, HttpServletRequest request){
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        return JSON.toJSONString(productionService.getCollectProductionPage(uid,currPage).getList());
    }
}
