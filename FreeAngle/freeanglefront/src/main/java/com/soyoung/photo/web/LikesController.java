package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.config.WebSocket;
import com.soyoung.photo.entity.Likes;
import com.soyoung.photo.entity.Messages;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.LikesService;
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
 * 点赞记录表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-27
 */
@Controller
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;

    //执行点赞
    @GetMapping("/execute")
    @ResponseBody
    public String addLike(Likes likes, HttpServletRequest request){
        likes.setUid(((User)request.getSession().getAttribute("user")).getId());
        Integer isNot=likesService.addProLike(likes);
        try{
            if(isNot>0){
                //点赞成功添加消息
                Integer byuid=userService.getProIsIdByUser(likes.getPid()).getId();
                Messages messages=new Messages(likes.getUid(),byuid, Dict.NEWSTYPELIKES,likes.getLid(),likes.getPid());
                if(messagesService.addMessages(messages)){
                    webSocket.sendMessage(byuid+"+"+Dict.NEWSTYPELIKES);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(isNot);
    }

    //取消点赞
    @GetMapping("/cancel")
    @ResponseBody
    public String delLike(Integer pid,HttpServletRequest request){
        Integer uid=((User)request.getSession().getAttribute("user")).getId();
        return JSON.toJSONString(likesService.delProLike(pid,uid));
    }
}
