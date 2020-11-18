package com.soyoung.photo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典特殊字段
 */
public class Dict {

    //作品主页热门标签
    public static final Map<String,String> HOTLABEL=new HashMap<String,String>(){
        {
            put("1","自然");
            put("2","城市");
            put("3","运动");
            put("4","人物");
        }
    };
    //主页热门作品PageSize
    public static final Integer HOMEPAGESIZE=16;
    //主页热门标签PageSize
    public static final Integer HOMEHOTTITMESIZE=24;
    //主页关注用户作品所需PageSize
    public static final Integer HOMEFOCUSPAGESIZE=20;
    //查询作品所需PageSize
    public static final Integer PROTERMSIZE=36;
    //标签页所需PageSize
    public static final Integer HOTLABELSIZE=27;
    //标签页子节点查询作品大小
    public static final Integer HOTLABEISPROLSIZE=3;
    //查询摄影师列表每页显示数据
    public static final Integer CAMERAMANLISTSIZE=10;
    //个人摄影师主页展示作品数据大小
    public static final Integer USERINPROSIZE=20;
    //评论显示行数
    public static final Integer COMMENTPAGESIZE=10;

    /**
     * 登录数据
     */
    public static final Integer QQLOGIN=1;      //QQ登录
    public static final Integer WEIXINLOGIN=2;  //微信登录
    public static final Integer WEIBOLOGIN=3;   //微博登录
    public static final Integer PHONELOGIN=4;   //手机登录

    /**
     * 作品类型
     */
    public static final String PRODUCTION_TYPE_IMG="5";     //图片
    public static final String PRODUCTION_TYPE_VIDEO="6";   //视频
    public static final String PRODUCTION_TYPE_360="7";     //360°

    /**
     *  举报类型
     */
    public static final Integer REPORT_PROJECT=11;      //作品举报
    public static final Integer REPORT_USER=12;      //人员举报
    public static final Integer REPORT_COMMENT=13;      //评论举报

    public static final String CHINESELANGUAGE="zh";   //百度API中文语言
    public static final String ENGLISHLANGUGE="en";    //百度API英文语言

    /**
     * OSS文件所需数据
     */
    public static final String OBJECTFILEIMG="Free/Work/Picture/";                  //图片路径
    public static final String OBJECTFILEVIDEO="Free/Work/Video/";                  //视频路径
    public static final String OBJECTFILE360="Free/Work/Panorama/";                 //360全景图路径
    public static final String OBJECTFILEVIDEOCHANGE="Free/Work/Video/picture/";    //视频背景
    public static final String OBJECTFILE360CHANGE="Free/Work/Panorama/Picture/";   //360背景
    public static final String OBJECTFILEUSERBEAD="Free/User/People/";              //用户头像
    public static final String OBJECTFILEUSERBACKDROP="Free/User/Backdrop/";        //用户背景

    /**
     * 搜索所需的PageSize
     */
    public static final Integer HUNTIMGPAGESIZE=42;
    public static final Integer HUNTVIDEOPAGESIZE=45;
    public static final Integer HUNT360PAGESIZE=39;
    public static final Integer HUNTUSERPAGESIZE=30;

    /**
     * 消息类型
     */
    public static final Integer NEWSTYPELIKES=14;
    public static final Integer NEWSTYPECOMMENT=15;
    public static final Integer NEWSTYPEATTENTION=16;
    public static final Integer NEWSTYPECOLLECT=17;
    public static final Integer NEWSTYPEINFORM=18;
    public static final Integer NEWSTYPEPRIVATE=19;

    /**
     * 消息所需的PageSize
     */
    public static final Integer NEWSPAGESIZE=10;

    /**
     * 系统默认信息
     */
    public static final String DEFAULTHEADURL="Free/User/People/default.png";   //头像
    public static final String DEFAULTBACKURL="Free/User/Backdrop/bg-big11.jpg";//背景
}
