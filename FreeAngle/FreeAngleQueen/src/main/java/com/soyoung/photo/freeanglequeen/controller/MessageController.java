package com.soyoung.photo.freeanglequeen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.entity.Dictionary;
import com.soyoung.photo.freeanglequeen.entity.Notice;
import com.soyoung.photo.freeanglequeen.entity.QueenUser;
import com.soyoung.photo.freeanglequeen.service.*;
import com.soyoung.photo.freeanglequeen.util.MessageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 陈海嘉
 * */

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    MessageStyle messageStyle = new MessageStyle();


    @RequestMapping("/fabu")
    @ResponseBody
    public  List<Dictionary> fabu(){
        List<Dictionary> msg = dictionaryService.getMsg();
        return msg;
    }

    @RequestMapping("/message")
    public String message(Integer currentPage, String aid, String utype , Model model){
        Map<String, Object> map=new HashMap<>();
        map.put("aid",aid);
        map.put("uid",utype);
        PageHelper.startPage(currentPage == null ? 1 :currentPage,6);
        List<Map<String, Object>> message = noticeService.getNotice(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(message);
        //List<Dictionary> msg = dictionaryService.getMsg();
        model.addAttribute("message",message);
        model.addAttribute("pageInfo",pageInfo);
        //model.addAttribute("msg",msg);
        return "pages/msg/messagehome";
    }

    /**
     * 群消息发布
     * 群消息消息发布，需要实现在消息表中插入数据，
     * @return
     */
    @PostMapping("/qunMessage")
    @ResponseBody
    public String qunMessage(String content, Model model, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> messagemap = new HashMap<>();
        List<Map<String, Object>> userByCreatetime = userService.getUserList();
        QueenUser queenUser = (QueenUser)session.getAttribute("queenUser");
        messageStyle.setSpanNeiRong(content);
        messageStyle.setTouXiangImageUrl(queenUser.getHeadURL());
        map.put("nid","");
        map.put("aid",queenUser.getId());
        //群消息发布，被通知的用户为0
        map.put("uid",0);
        //富文本中的内容
//        content+"";
        map.put("content",messageStyle.getStyle1());
        //当天时间
        map.put("createTime",new Date());
        int addNotice = noticeService.addNotice(map);
        Integer nid = (Integer) map.get("nid");
        messagemap.put("uid",queenUser.getId());
        messagemap.put("mType",18);
        messagemap.put("keyid",nid);
        messagemap.put("isNot",0);
        messagemap.put("createTime",new Date());
        int addMessage = 0;
        for (int i = 0; i < userByCreatetime.size(); i++) {
            messagemap.put("byuid",userByCreatetime.get(i).get("id"));
            addMessage = messagesService.addMessage(messagemap);
        }
        if (addMessage>0){
            return "sourcss";
        }
        return "error";
    }

    /**
     * 个人信息发布
     * @return
     */
    @PostMapping("/geMessage")
    @ResponseBody
    public String geMessage(String content,String selUser, Model model, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> messagemap = new HashMap<>();
        QueenUser queenUser = (QueenUser)session.getAttribute("queenUser");

        messageStyle.setSpanNeiRong(content);
        messageStyle.setTouXiangImageUrl(queenUser.getHeadURL());
        map.put("nid","");
        map.put("aid",queenUser.getId());
        //富文本中的内容
        map.put("content",messageStyle.getStyle1());
        //当天时间
        map.put("createTime",new Date());
        String [] byuidaray = selUser.split(",");
        messagemap.put("uid",queenUser.getId());
        messagemap.put("mType",18);
        messagemap.put("isNot",0);
        messagemap.put("createTime",new Date());
        int addMessage = 0;
        for (int i = 0; i <byuidaray.length ; i++) {
            //群消息发布，根据管理人员选择通知的人，循环插入
            map.put("uid",Integer.parseInt(byuidaray[i]));
            int addNotice = noticeService.addNotice(map);
            Integer nid = (Integer) map.get("nid");
            messagemap.put("keyid",nid);
            messagemap.put("byuid",Integer.parseInt(byuidaray[i]));
            addMessage = messagesService.addMessage(messagemap);
        }
        if (addMessage>0){
            return "sourcss";
        }
        return "errors";
    }

    /**
     * 上传富文本编辑器的图片及表情
     * @return
     */
    @RequestMapping("uploadEdiotImage")
    public String uploadEdiotImage(){

        return "";
    }

    @GetMapping("/famsg")
    public String famsg(){
        return "/pages/msg/famessage";
    }

    @GetMapping("/updmsg")
    public String updmsg(){
        return "/pages/msg/updmessage";
    }

    /**
     * 修改信息，和删除信息
     */
    @RequestMapping("selUpdMsg")
    @ResponseBody
    public Notice selUpdMsg(Integer nid){
        Notice notice = noticeService.getNoticeEntity(nid);
        return notice;
    }
    @RequestMapping("updmessage")
    @ResponseBody
    public String updMessage(String content,Integer nid,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        map.put("nid",nid);
        int i = noticeService.UpdMessage(map);
        if (i>0){
           return "sourcss";
        }
        return "error";
    }


    @RequestMapping("/delMsg")
    @ResponseBody
    public String delMsg(Integer id){
        try {
            int i = messagesService.delMsg(id);
            int i1 = noticeService.delNotice(id);
            return "sourcess";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
