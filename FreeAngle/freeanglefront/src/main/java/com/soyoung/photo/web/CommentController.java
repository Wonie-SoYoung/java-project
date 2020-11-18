package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.soyoung.photo.config.WebSocket;
import com.soyoung.photo.entity.Comment;
import com.soyoung.photo.entity.Messages;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.CommentService;
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
 * 评论表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-29
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;

    //评论ajax查询
    @GetMapping("/list")
    @ResponseBody
    public String listComment(Integer pid,Integer currPage){
        return JSON.toJSONString(commentService.getProIsCommentPage(pid,currPage), SerializerFeature.DisableCircularReferenceDetect);
    }

    //添加评论
    @GetMapping("/add")
    @ResponseBody
    public String addComment(Comment comment, HttpServletRequest request){
        //添加用户
        comment.setUid(((User)request.getSession().getAttribute("user")).getId());
        Integer isNot=commentService.addComment(comment);
        try{
            //判断添加消息
            if(isNot>0){
                //评论成功添加消息
                Integer byuid=userService.getProIsIdByUser(comment.getPid()).getId();
                if(byuid!=comment.getUid()){
                    Messages messages=new Messages(comment.getUid(),byuid, Dict.NEWSTYPECOMMENT,comment.getCid(),comment.getPid());
                    if(messagesService.addMessages(messages)){
                        webSocket.sendMessage(byuid+"+"+Dict.NEWSTYPECOMMENT);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(isNot);
    }

    //删除评论
    @GetMapping("/del")
    @ResponseBody
    public String delComment(Integer cid){
        return JSON.toJSONString(commentService.delComment(cid));
    }
}
