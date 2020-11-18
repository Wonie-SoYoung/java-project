package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSONObject;
import com.soyoung.photo.freeanglequeen.service.CommentService;
import com.soyoung.photo.freeanglequeen.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/CommentController")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private ReportService reportService;

    /*
    * 用户举报通过：（封杀被举报人作品）*/
    @GetMapping("/delCommentCid")
    @ResponseBody
    public String delCommentCid(Integer cid,Integer rid){
        Integer i = commentService.delCommentCid(cid);
        if (i>0){
            reportService.updReportRid(1,rid);
            return JSONObject.toJSONString(i);
        }else{
            return "redirect:/TablesController/toError500Page";
        }
    }

    /*
    * 用户举报不通过
    * */
    @GetMapping("/updReportIsnot")
    @ResponseBody()
    public String updReportIsnot(Integer rid){
        Integer i = reportService.updReportRid(1,rid);
        if (i>0){
            return JSONObject.toJSONString(i);
        }else{
            return "redirect:/TablesController/toError500Page";
        }
    }
}
