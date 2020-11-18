package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.AttentionService;
import com.soyoung.photo.service.MessagesService;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private AttentionService attentionService;

    //进入页面
    @RequestMapping("/notifications")
    public String getMessagesHtml(Integer type,Integer currPage,HttpServletRequest request){
        currPage=currPage==null ? 1:currPage;
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        if(type== Dict.NEWSTYPEATTENTION){
            request.setAttribute("fansList",attentionService.getAllIsUidList(uid));
        }
        if(type!=Dict.NEWSTYPEPRIVATE){
            messagesService.updateMessageYes(type,uid);
        }
        request.setAttribute("type",type);
        FreePage freePage=messagesService.getTypeMessagesList(type,uid,currPage);
        request.setAttribute("mesPage",freePage);
        return "notifications";
    }

    //ajax查询信息未读数量
    @GetMapping("/getmessageNum")
    @ResponseBody
    public Object getMessageJSONNum(HttpServletRequest request){
        User user=(User) request.getSession().getAttribute("user");
        if(user!=null){
            return messagesService.getMessageAllNum(user.getId());
        }
        return JSON.toJSONString(null);
    }


    //修改已读
    @GetMapping("/update")
    @ResponseBody
    public String getUpdateYes(Integer type,HttpServletRequest request){
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        return JSON.toJSONString(messagesService.updateMessageYes(type,uid));
    }
}
