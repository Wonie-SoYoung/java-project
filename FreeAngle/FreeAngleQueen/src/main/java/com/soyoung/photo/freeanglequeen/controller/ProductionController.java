package com.soyoung.photo.freeanglequeen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soyoung.photo.freeanglequeen.dao.ProductionMapper;
import com.soyoung.photo.freeanglequeen.entity.Dictionary;
import com.soyoung.photo.freeanglequeen.entity.Medal;
import com.soyoung.photo.freeanglequeen.service.*;
import com.soyoung.photo.freeanglequeen.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 陈海嘉
* */
@Controller
@RequestMapping("/production")
public class ProductionController {

    @Autowired
    private ProductionService productionService;
    @Autowired
    private UserService userService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private MedalService medalService;
    @Autowired
    private ProductionMapper productionMapper;
    @Resource
    private ReportService reportService;


    @GetMapping("/countProduction")
    @ResponseBody
    public Map<String, Object> countUser(Model model){
        Map<String, Object> result = new HashMap<String, Object>();
        int productionSum = productionMapper.getProductionSum(result);
        result.put("productionSum",productionSum);
        return result;
    }

    //作品的分页查询
    @RequestMapping("/productionSel")
    public String productionSel(Integer currentPage,String pName, String userName, String pType, Model model){
        Map<String, Object> map=new HashMap<>();
        map.put("pName",pName);
        map.put("userName",userName);
        map.put("pType",pType);
        map.put("currentPage",currentPage==null ? 1:currentPage);
        Page page = productionService.getProductionList(map);
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryList();
        model.addAttribute("page",page);
        model.addAttribute("pName",pName);
        model.addAttribute("userName",userName);
        model.addAttribute("pType",pType);
        model.addAttribute("dictionaryList",dictionaryList);
        return "pages/tables/production-table";
    }

    //查看作品的详细信息
    @GetMapping("/detail/{id}")
        public String detail(@PathVariable("id") String id,Model model){
        Map<String, Object> productionByIdMap = productionService.getProductionById(Integer.parseInt(id));
        Integer uid = (Integer) productionByIdMap.get("uid");
        Map<String, Object> userByIdMap = userService.getUserById(uid);
        model.addAttribute("productionByIdMap",productionByIdMap);
        model.addAttribute("userByIdMap",userByIdMap);
        return "pages/look-overs/production-look";
    }

    @GetMapping("selShouXun")
    public String selShouXun(Model model){
        List<Medal> mdealList = medalService.getMdealList();
        model.addAttribute("mdealList",mdealList);
        return "pages/tables/selShouXun";
    }

    //授勋
    @RequestMapping("shouxun")
    @ResponseBody
    public String shouXun(String checksplict,Integer pid){
        Map<String,Object> map = new HashMap<>();
        map.put("pid",pid);
        String[] split = checksplict.split(",");
       try {
           for (int i = 0; i < split.length; i++) {
               map.put("mid",split[i]);
               productionService.shouXun(map);
           }
           return "success";
       }catch (Exception e){
           e.printStackTrace();
           return "error";
       }
    }

    @RequestMapping("/productionByType")
    @ResponseBody
    public Map<String,Object> productionByType(Integer userId){
        Map<String,Object> map = new HashMap<>();
        map.put("uid",userId);
        map.put("protypeId",5);
        List<Map<String, Object>> productionByType = productionService.getProductionByType(map);
        map.put("protypeId",6);
        List<Map<String, Object>> productionByType1 = productionService.getProductionByType(map);
        map.put("protypeId",7);
        List<Map<String, Object>> productionByType2 = productionService.getProductionByType(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("productionByType",productionByType);
        map1.put("productionByType1",productionByType1);
        map1.put("productionByType2",productionByType2);
        return map1;

    }

    @GetMapping("/selimage")
    public String selImage(){
        return "/pages/tables/image";
    }

    /*
    * 用户举报通过：（封杀被举报人作品）
    * */
    @GetMapping("/delProduction")
    @ResponseBody
    public String delProduction(Integer id,Integer rid){
        Integer i = productionService.delProductionId(id);
        if (i>0){
            //修改举报表为已审核
            reportService.updReportRid(1,rid);
            return JSONObject.toJSONString(i);
        }else {
            //失败，进入500页面
            return "redirect:/TablesController/toError500Page";
        }
    }
    /*
    * 用户举报不通过
    * */
    @GetMapping("/updProduction")
    @ResponseBody
    public String updProduction(Integer id,Integer rid){
        System.out.println(id);
        //修改举报表为已审核
        Integer i = reportService.updReportRid(1,rid);
        if (i>0){
            return JSONObject.toJSONString(i);
        }else{
            return "redirect:/TablesController/toError500Page";
        }
    }
}
