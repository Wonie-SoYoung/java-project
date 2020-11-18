package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.config.WebSocket;
import com.soyoung.photo.entity.Attention;
import com.soyoung.photo.entity.Messages;
import com.soyoung.photo.service.AttentionService;
import com.soyoung.photo.service.MessagesService;
import com.soyoung.photo.service.UserService;
import com.soyoung.photo.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 关注表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-13
 */
@Controller
@RequestMapping("/attention")
public class AttentionController {
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;

    //执行关注
    @GetMapping("/execute")
    @ResponseBody
    public String addAttention(Attention attention){
        Integer isNot=attentionService.addAttention(attention);
        try{
            if(isNot>0){
                //关注成功添加消息
                Messages messages=new Messages(attention.getUid(),attention.getByuid(), Dict.NEWSTYPEATTENTION,attention.getAid());
                if(messagesService.addMessages(messages)){
                    webSocket.sendMessage(attention.getByuid()+"+"+Dict.NEWSTYPEATTENTION);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(isNot);
    }

    //取消关注
    @GetMapping("/cancel")
    @ResponseBody
    public String delAttention(Integer uid,Integer byuid){
        return JSON.toJSONString(attentionService.delAttention(uid,byuid));
    }

}
