package com.soyoung.photo.freeanglequeen.controller;

import com.soyoung.photo.freeanglequeen.entity.QueenUser;
import com.soyoung.photo.freeanglequeen.service.ProductionService;
import com.soyoung.photo.freeanglequeen.service.QueenUserService;
import com.soyoung.photo.freeanglequeen.service.UserService;
import com.soyoung.photo.freeanglequeen.util.FaceSpot;
import com.soyoung.photo.freeanglequeen.util.Page;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/faceLogin")
public class FaceController {

    //需要使用到的接口
    @Autowired
    private QueenUserService queenUserService;
    @Autowired
    private ProductionService productionService;
    @Autowired
    private UserService userService;

    /*
     * 陈海嘉
     * */

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    //跳转登录页面
    @GetMapping("/home")
    public String source(Integer currentPage,Integer currentPage1, Model model){
        Map<String, Object> map=new HashMap<>();
        map.put("currentPage",currentPage==null ? 1:currentPage);
        Map<String, Object> map1=new HashMap<>();
        map1.put("currentPage1",currentPage1==null ? 1:currentPage1);
        Page page = userService.everyDayUser(map);
        Page page1 = productionService.everyDayProductionList(map1);
     /*   page.setPageSize(6);
        page1.setPageSize(6);*/

        model.addAttribute("page",page);
        model.addAttribute("page1",page1);
        return "pages/home/index";
    }

    //登录
    @PostMapping("/searchFace")
    @ResponseBody
    public String searchFace(String img,HttpSession session) {
        JSONObject js = FaceSpot.searchFace(img, "c5_face");
        //System.out.println(js.toString(2));
        Object error_code = js.get("error_code");
        if ((Integer) error_code==0){
            JSONObject result = js.getJSONObject("result");
            JSONObject user_list = result.getJSONArray("user_list").getJSONObject(0);
            int user_id = user_list.getInt("user_id");
            QueenUser UrlByIdQueenUser = queenUserService.getFaceUrlByIdQueenUser(user_id);
            session.setAttribute("queenUser",UrlByIdQueenUser);
        }
        return js.toString();
    }

    //跳转注册页面
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //注册
    @PostMapping("/registered")
    @ResponseBody
    public Map<String,String> Registered(String img, HttpSession session) {
            Map<String,String> stringMap=new HashMap<>();
        JSONObject js = FaceSpot.faceverifyFace(img);
        Object error_code = js.get("error_code");
        if ((Integer) error_code==0){
            JSONObject result = js.getJSONObject("result");
            JSONObject thresholds = result.getJSONObject("thresholds");
            double face_liveness = (double)result.getInt("face_liveness");
            if (face_liveness < 0.393241){
                stringMap.put("frr_1e_3","检测活体失败,请重新检测上传");
                return stringMap;
            }
        }else{
            stringMap.put("error_code","上传检测失败，请重新上传");
            return stringMap;
        }
        if ((int) error_code ==0){
            //user_id ：添加账户的id
           QueenUser queenUser = (QueenUser)session.getAttribute("queenUser");
           if (queenUser==null){
               //返回页面，无法注册
               System.out.println("注册失败");
           }
           int id = queenUser.getId();
           JSONObject js1 = FaceSpot.addFace(img, "c5_face",id+"");
           stringMap.put("test","插入成功");
       }
        return stringMap;
    }
}
