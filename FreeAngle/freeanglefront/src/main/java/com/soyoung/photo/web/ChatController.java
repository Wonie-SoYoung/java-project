package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.soyoung.photo.config.WebSocket;
import com.soyoung.photo.entity.Chat;
import com.soyoung.photo.entity.Messages;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.ChatService;
import com.soyoung.photo.service.MessagesService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 私聊表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-05
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;

    //查询列表
    @GetMapping("/list")
    @ResponseBody
    public String getChat(Integer auid,Integer buid,HttpServletRequest request){
        return JSON.toJSONString(chatService.getUserisUserorChat(auid,buid), SerializerFeature.DisableCircularReferenceDetect);
    }

    //添加信息
    @GetMapping("/add")
    @ResponseBody
    public String addChat(Chat chat,HttpServletRequest request){
        chat.setCozeid(((User)request.getSession().getAttribute("user")).getId());
        Integer isNot=chatService.addChat(chat);
        try{
            if(isNot>0){
                //添加信息后添加消息
                Messages messages=new Messages(chat.getCozeid(),chat.getTalkid(), Dict.NEWSTYPEPRIVATE,chat.getCid());
                if(messagesService.addMessages(messages)){
                    webSocket.sendMessage(chat.getTalkid()+"+"+Dict.NEWSTYPEPRIVATE);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(isNot);
    }
}
