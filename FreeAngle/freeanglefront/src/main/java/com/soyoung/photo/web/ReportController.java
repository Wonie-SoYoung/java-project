package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.Report;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.service.CommentService;
import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.service.ReportService;
import com.soyoung.photo.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 举报表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-02
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ProductionService productionService;

    //添加评论举报
    @GetMapping("/addComment")
    @ResponseBody
    public String addCommentReport(Report report, HttpServletRequest request){
        //封装数据
        report.setUid(((User)request.getSession().getAttribute("user")).getId());
        report.setByuid(commentService.selectById(report.getReTypeCodeId()).getUid());
        report.setReType(Dict.REPORT_COMMENT);
        return JSON.toJSONString(reportService.addReport(report));
    }

    //添加作品举报
    @GetMapping("/addProduction")
    @ResponseBody
    public String addProductionReport(Report report,HttpServletRequest request){
        //封装数据
        report.setUid(((User)request.getSession().getAttribute("user")).getId());
        report.setByuid(productionService.selectById(report.getReTypeCodeId()).getUid());
        report.setReType(Dict.REPORT_PROJECT);
        return JSON.toJSONString(reportService.addReport(report));
    }
    //添加用户举报


}
