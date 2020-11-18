package com.soyoung.photo.freeanglequeen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.entity.*;
import com.soyoung.photo.freeanglequeen.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

import static com.soyoung.photo.freeanglequeen.util.Constants.REPORT_TYPE;

@Controller
@RequestMapping("/TablesController")
public class TablesController {

    @Resource
    public HotlabelsService labelTableService;
    @Resource
    public SlideShowService slideShowService;
    @Resource
    public DictionaryService dictionaryService;
    @Resource
    public ReportService reportService;
    @Resource
    public ProductionService productionService;
    @Resource
    public UserService userService;
    @Resource
    public CommentService commentService;
    @Resource
    public MedalService medalService;


    /*
    * 进入主页面
    * */
    @GetMapping("/toHomePage")
    public String toHomePage(){
        return "/pages/home/index";
    }

    /*
    * 进入用户管理页面
    * */

    @GetMapping("/toUsers-table")
    public String toUserTable(){
        return "/pages/tables/users-table";
    }

    /*
    * 进入用户添加页面
    * */
    @GetMapping("/toUsers-add")
    public String toUsersAdd(){
        return "/pages/forms/users-add";
    }

    /*
    * 进入所有作品页面
    * */
    @GetMapping("/toProduction-table")
    public String toProductionTable(){
        return "pages/tables/production-table";
    }

    /*
    * 进入查看作品详细信息页面
    * */
    @GetMapping("/toProduction-look")
    public String toProductionLook(){
        return "/pages/look-overs/production-look";
    }



    /*
    * 查看作品视频
    * */
    @GetMapping("/toLook-over")
    public String toLookOver(){
        return "/pages/look-overs/video";
    }

    /*
    * 进入标签管理页面
    * */
    @GetMapping("/toHotlabels-table")
    public String toLableTable(Model model,Integer parentId,Integer hId,
                               @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "4") int pageSize){
        //一级标签查询
        List<Hotlabels> list = labelTableService.selLabelTableListHId();
        model.addAttribute("oneLabel",list);
        //设置分页规则
        PageHelper.startPage(pageNum,pageSize );
        //返回所有分页信息参数为查询所有记录的信息
        PageInfo<Hotlabels> pageInfo = labelTableService.selLabelTableListAll(parentId==null ?null:parentId,hId==null?null:hId);
        model.addAttribute("labels",pageInfo);
        return "/pages/tables/hotlabels-table";
    }

    /*
    * 进入标签添加页面
    * */
    @GetMapping("/toHotlabelsAddPage")
    public String toHotlabelsAddPage(Model model){
        //一级标签查询
        List<Hotlabels> list = labelTableService.selLabelTableListHId();
        model.addAttribute("oneLabel",list);
        return "pages/forms/hotlabels-add-upd";
    }

    /*
    * 进入标签修改页面
    * */
    @GetMapping("/toHotlabelsUpdPage/{hId}")
    public String toHotlabelsUpdPage(Model model,@PathVariable("hId") Integer hId){
        //一级标签查询
        List<Hotlabels> list = labelTableService.selLabelTableListHId();
        model.addAttribute("oneLabel",list);
        Hotlabels hotlabels = labelTableService.selHotlabelsHid(hId);
        model.addAttribute("hotlabelsUpd",hotlabels);
        return "pages/forms/hotlabels-add-upd";
    }

    /*
    * 进入轮播图页面
    * */
    @GetMapping("/toSlideShow")
    public String toSlideShow(Model model){
        List<SlideShow> list = slideShowService.selSlideShowListAll();
        model.addAttribute("SlideShow",list);
        return "pages/tables/slideShow-table";
    }

    /*
    * 进入轮播图添加页面
    * */
    @GetMapping("/toSlideShowAdd")
    public String toSlideShowAdd(){
        return "pages/forms/slideShow-add-upd";
    }

    /*
     * 进入轮播图修改页面
     * */
    @GetMapping("/toSlideShowUpdPage/{sid}")
    public String toSlideShowUpdPage(Model model,@PathVariable("sid") Integer sid){
        SlideShow slideShow = slideShowService.selSlideShowListId(sid);
        model.addAttribute("slideShowUpd",slideShow);
        return "pages/forms/slideShow-add-upd";
    }

    /*
    * 进入举报管理页面
    * */
    @GetMapping("/toReport-table")
    public String toReportTable(Model model,Integer reType,
                                @RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize){
        //举报类型条件查询
        List<Dictionary> list = dictionaryService.selDictionaryListTypeCode(REPORT_TYPE);
        model.addAttribute("typeCode",list);
        //设置分页规则
        PageHelper.startPage(pageNum,pageSize);

        PageInfo<Report> pageInfo = reportService.selReportListAll(reType==null?null:reType);
        model.addAttribute("labels",pageInfo);
        return "pages/tables/report-table";
    }

    /*
    * 进入举报详情页面
    * */
    @GetMapping("/toReport-look")
    public String toReportLook(Model model,Integer reType,Integer reTypeCodeId,
                               @RequestParam(value = "byuid",defaultValue = "0") Integer byuid,Integer rid,Integer userId){

        /*
        * 作品举报：
        *           根据举报类型查询出作品信息
        *           ---> 再根据作品中的 Uid 查询出该作品的用户信息
        *           ---> 最后根据作品的 id 去评论表查询出该作品下的评论信息
        *           */
        if (reType==11){
            try{
                Production production = productionService.selProductionId(reTypeCodeId);
                production.setPicUrl(production.getProURL().split("\\+"));
                model.addAttribute("lb",production.getPicUrl().length);
                if (production.getPicUrl().length>1){

                }
                User user = userService.selUserId(production.getUid());
                List<Comment> comments = commentService.selCommentListPid(production.getId());
                model.addAttribute("production",production);
                model.addAttribute("user",user);
                model.addAttribute("comments",comments);
                model.addAttribute("rid",rid);
                model.addAttribute("uid",userId);
                model.addAttribute("byuid",byuid);
                model.addAttribute("a","作品举报");
            }catch (Exception e){
                return "redirect:/TablesController/toError500Page";
            }

        /*
        * 用户举报：（用户举报为扩展内容：由于种种原因，暂时放弃用户举报）
        *           根据用户 ID 查询出用户信息
        *           ---> 再根据作品中的用户 ID 外键查询出该用户下的所有作品信息
        *           ---> 最后根据作品的 id 去评论表查询出该作品下的评论信息
        *           */
        }else if (reType==12){
            try {
                Production production = productionService.selProductionId(reTypeCodeId);
                Integer uid = production.getUid();
                User user = userService.selUserId(uid);
                model.addAttribute("production",production);
                model.addAttribute("user",user);
                model.addAttribute("rid",rid);
                model.addAttribute("b","用户举报");
            }catch (Exception e){
                return "redirect:/TablesController/toError500Page";
            }

        /*
        * 评论举报：
        *           根据评论 id 查询出违规评论信息
        *           ---> 再根据评论表中的 pid 查询出所有该作品下的评论信息
        *           ---> 最后根据评论表中的 pid 查询该作品信息
        *           */
        }else if (reType==13){
            try {
                Comment comment = commentService.selCommentReTypeCodeId(reTypeCodeId);//违规评论
                model.addAttribute("n", comment);
                model.addAttribute("comment", comment);
                List<Comment> comments = commentService.selCommentListPid(comment.getPid());//根据评论表 pid 查询出该作品下的评论信息
                model.addAttribute("comments", comments);
                User user = comment.getUser();
                model.addAttribute("user", user);
                Production production = productionService.selProductionId(comment.getPid());//根据评论表外键 pid 查询出作品表的作品信息
                production.setPicUrl(production.getProURL().split("\\+"));
                model.addAttribute("lb", production.getPicUrl().length);
                if (production.getPicUrl().length > 1) {

                }
                model.addAttribute("production", production);
                model.addAttribute("rid",rid);
                model.addAttribute("uid",userId);
                model.addAttribute("byuid",byuid);
                model.addAttribute("c", "评论举报");
            }catch (Exception e){
                return "redirect:/TablesController/toError500Page";
            }
        }
        return "pages/look-overs/report-look";
    }

    /*
    * 进入勋章管理页面
    * */
    @GetMapping("/toMedal-table")
    public String toMedalTable(Model model){
        List<Medal> medalList = medalService.selMedalList();
        model.addAttribute("medals",medalList);
        return "pages/tables/medal-table";
    }

    /*
     * 进入轮播图添加页面
     * */
    @GetMapping("/toMedalAddPage")
    public String toMedalAddPage(){
        return "pages/forms/medal-add-upd";
    }

    /*
     * 进入轮播图修改页面
     * */
    @GetMapping("/toMedalUpdPage/{mid}")
    public String toMedalUpdPage(Model model,@PathVariable("mid") Integer mid){
        Medal medal = medalService.selMedalMid(mid);
        model.addAttribute("medalUpd",medal);
        return "pages/forms/medal-add-upd";
    }

    /*
    * 500页面
    * */
    @RequestMapping("toError500Page")
    public String toError500Page(){
        return "pages/error/error-500";
    }

    /*
    * 404页面
    * */
    @RequestMapping("/toError404Page")
    public String toError404Page(){
        return "pages/error/error-404";
    }



    /*
    * ------------------------------------------------------------->陈海嘉
    * */

    /*
     * 进入查看作品详细信息页面
     * */
    @GetMapping("/toQueenUser")
    public String toQueenUser(){
        return "/pages/queen/queenUser";
    }

    /*
     * 进入查看作品详细信息页面
     * */
    @GetMapping("/topersonalCenter")
    public String topersonalCenter(){
        return "/pages/queen/personalCenter";
    }

    /*
     * 进入查看作品详细信息页面
     * */
    @GetMapping("/addQueenUser")
    public String addQueenUser(){
        return "/pages/queen/addQueenUser";
    }

    /*
     * 进入查看作品详细信息页面
     * */
    @GetMapping("/msghome")
    public String msghome(){
        return "/pages/msg/messagehome";
    }

}
