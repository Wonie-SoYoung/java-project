package com.soyoung.project.controller;

import com.soyoung.project.entity.GoodsDO;
import com.soyoung.project.service.GoodsService;
import com.soyoung.project.verify.EntityAdd;
import com.soyoung.project.verify.EntityUpdate;
import com.soyoung.project.verify.VerifyUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
*  前端控制器
*/
@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 自定义方法绑定请求参数的Date类型
     *
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);//true表示允许为空，false反之
        binder.registerCustomEditor(Date.class, editor);
    }

    @GetMapping("/addHtml")
    public String addHtml(HttpServletRequest request){
        request.setAttribute("type","1");
        return "addorupdate";
    }

    @GetMapping("/updateHtml/{id}")
    public String updateHtml(@PathVariable("id") Integer id, HttpServletRequest request){
        request.setAttribute("goods",goodsService.getGoods(id));
        request.setAttribute("type","2");
        return "addorupdate";
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询物品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gname", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "created", value = "登记时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "createdUser", value = "登记人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "分类", required = true, dataType = "String")
    })
    public String list(@RequestParam Map<String,String> map, Model model){
        List<GoodsDO> goodsDOS = goodsService.listGoodsCondition(map);
        model.addAttribute("goods",goodsDOS);
        return "list";
    }

    @PostMapping("/insert")
    @ApiOperation(value = "增加用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gname", value = "名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "created", value = "登记时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "createdUser", value = "登记人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "分类", required = true, dataType = "String")
    })
    public String insert(@Validated(value = {EntityAdd.class}) GoodsDO goodsDO, BindingResult bindingResult,HttpServletRequest request){
        request.setAttribute("type","1");
        if(bindingResult.hasErrors()) {
            request.setAttribute("error",VerifyUtil.getValidateError(bindingResult));
            return "addorupdate";
        }
        if(goodsService.insertGoods(goodsDO) > 0) {
            return "redirect:list";
        }
        request.setAttribute("error","系统繁忙");
        return "addorupdate";
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改物品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsDO", value = "物品实体", required = true, dataType = "Object")
    })
    public String update(@Validated(value = {EntityUpdate.class}) GoodsDO goodsDO, BindingResult bindingResult,HttpServletRequest request){
        request.setAttribute("type","2");
        request.setAttribute("goods",goodsService.getGoods(goodsDO.getId()));
        if(bindingResult.hasErrors()) {
            request.setAttribute("error",VerifyUtil.getValidateError(bindingResult));
            return "addorupdate";
        }
        if(goodsService.updateGoods(goodsDO) > 0) {
            return "redirect:list";
        }
        request.setAttribute("error","系统繁忙");
        return "addorupdate";
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
    })
    public String update(@PathVariable("id") Integer id){
        if(goodsService.deleteGoods(id) > 0) {
            return "redirect:/list";
        }
        return "redirect:/list";
    }
}
