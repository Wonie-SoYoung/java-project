package com.soyoung.photo.web;


import com.alibaba.fastjson.JSON;
import com.soyoung.photo.entity.Dictionary;
import com.soyoung.photo.entity.Hotlabels;
import com.soyoung.photo.service.DictionaryService;
import com.soyoung.photo.service.HotlabelsService;
import com.soyoung.photo.service.ProductionService;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 热门标签表 前端控制器
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Controller
@RequestMapping("/hotlabels")
public class HotlabelsController {
    @Autowired
    private HotlabelsService hotlabelsService;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private DictionaryService dictionaryService;

    //标签页面
    @GetMapping("/getLabel")
    public String getLabel(Integer hid,Integer currPage,HttpServletRequest request){
        //保存根标签
        List<Hotlabels> dadLables=hotlabelsService.getDadByLabels();
        request.setAttribute("dadLabels",dadLables);
        //保存子节点
        hid=hid==null||hid==0 ? dadLables.get(0).gethId():hid;
        currPage=currPage==null ||currPage==0 ? 1:currPage;
        FreePage freePage=hotlabelsService.getSonByLables(hid,currPage);
        request.setAttribute("labels",freePage);
        request.setAttribute("hid",hid);
        request.setAttribute("headType",3);
        return "tally";
    }

    //ajax获取标签数据
    @PostMapping("/getLabel")
    @ResponseBody
    public String getLabel(Integer hid,Integer currPage){
        //保存子节点
        FreePage freePage=hotlabelsService.getSonByLables(hid,currPage);
        return JSON.toJSONString(freePage.getList());
    }

    //查询详细标签对应的作品
    @GetMapping("/getLabelByPro")
    public String getLabelByPro(String label,Integer proType,Integer currPage,HttpServletRequest request){
        currPage=currPage==null ||currPage==0 ? 0:currPage;
        FreePage freePage=productionService.getTermProListPage(label,null,proType,currPage,Dict.PROTERMSIZE);
        request.setAttribute("label",label);
        request.setAttribute("proPage",freePage);
        return "tallyshow";
    }

    //查询详细标签对应的作品
    @PostMapping("/getLabelByPro")
    @ResponseBody
    public String getLabelByPro(String label,Integer proType,Integer currPage,Integer pageSize){
        proType=proType==0 ? null:proType;
        FreePage freePage=productionService.getTermProListPage(label,null,proType,currPage,pageSize);
        return JSON.toJSONString(freePage.getList());
    }
}
