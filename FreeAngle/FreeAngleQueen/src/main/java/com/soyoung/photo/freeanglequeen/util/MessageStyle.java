package com.soyoung.photo.freeanglequeen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageStyle {
    private String touXiangImageUrl; // 发送者头像
    private String time; //时间
    private String spanNeiRong;
    private String zuoPin; //作品名称
    private String zuoImage; // 作品 壁纸

    public MessageStyle() {
    }

    public MessageStyle(String touXiangImageUrl, String time, String spanNeiRong, String zuoPin, String zuoImage) {
        this.touXiangImageUrl = touXiangImageUrl;
        this.time = time;
        this.spanNeiRong = spanNeiRong;
        this.zuoPin = zuoPin;
        this.zuoImage = zuoImage;
    }

    public String getTouXiangImageUrl() {
        return touXiangImageUrl;
    }

    public void setTouXiangImageUrl(String touXiangImageUrl) {
        this.touXiangImageUrl = touXiangImageUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getZuoPin() {
        return zuoPin;
    }

    public void setZuoPin(String zuoPin) {
        this.zuoPin = zuoPin;
    }

    public String getZuoImage() {
        return zuoImage;
    }

    public void setZuoImage(String zuoImage) {
        this.zuoImage = zuoImage;
    }

    public String getSpanNeiRong() {
        return spanNeiRong;
    }

    public void setSpanNeiRong(String spanNeiRong) {
        this.spanNeiRong = spanNeiRong;
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getTimeMd(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd");//设置当前时间的格式，为年-月-日
        return dateFormat.format(new Date());
    }
    public String getTimeHM(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");//设置当前时间的格式，为年-月-日
        return dateFormat.format(new Date());
    }

    /**
     * 纯文本方式
     * @return
     */
    public String getStyle1(){
        String style = "<div class=\"FZuO _3ccW\">\n" +
                "\t\t\t<div class=\"_2HT4\" target=\"\">\n" +
                "\t\t\t\t<div class=\"_3L67 _2sR0 _3cz3\">\n" +
                "\t\t\t\t\t<a class=\"\" target=\"\">\n" +
                "\t\t\t\t\t\t<div class=\"JTA4 _3gEz undefined\">\n" +
                "\t\t\t\t\t\t\t<div class=\"_3llO _1Gim _1LNE _2G4A hUPH\">\n" +
                "\t\t\t\t\t\t\t\t<img src=\"https://free-angle.oss-cn-beijing.aliyuncs.com/"+touXiangImageUrl+"\" alt=\"\" width=\"100%\" height=\"100%\">\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</a>\n" +
                "\t\t\t\t\t<div class=\"_2MmI\">\n" +
                "\t\t\t\t\t\t<div class=\"qRWk _1IqX\">\n" +
                "\t\t\t\t\t\t\t<span>"+spanNeiRong+"</span>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"_3_RH\">\n" +
                "\t\t\t\t\t\t\t<div class=\"_1GWY\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"_3fuh\">\n" +
                "\t\t\t\t\t\t\t\t\t<span>"+getTimeMd()+"</span>\n" +
                "\t\t\t\t\t\t\t\t\t<span>"+getTimeHM()+"</span>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>";
        return style;
    }

    /**
     * 带作品方式
     * @return
     */
    public String getStyle2(){
        String style = "<!-- 带作品方式 -->\n" +
                "\t\t<div class=\"FZuO _3ccW\">\n" +
                "\t\t\t<div class=\"_2HT4\" target=\"\">\n" +
                "\t\t\t\t<div class=\"_3L67 _2sR0\">\n" +
                "\t\t\t\t\t<a class=\"\" target=\"\">\n" +
                "\t\t\t\t\t\t<div class=\"JTA4 _3gEz undefined\">\n" +
                "\t\t\t\t\t\t\t<div class=\"_3llO _1Gim _1LNE _2G4A hUPH\">\n" +
                "\t\t\t\t\t\t\t\t<img src=\"\" alt=\"\" width=\"100%\" height=\"100%\">\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</a>\n" +
                "\t\t\t\t\t<div class=\"_2MmI\">\n" +
                "\t\t\t\t\t\t<div class=\"qRWk\">\n" +
                "\t\t\t\t\t\t\t<span>你的作品 \n" +
                "\t\t\t\t\t\t\t\t<a class=\"_1L-I\" target=\"\" href=\"/photos/986002e1-27d7-4527-8be9-70506a0dd3b5\">沙漠风景</a>\n" +
                "\t\t\t\t\t\t\t\t 入围精选，作品真的很棒！\n" +
                "\t\t\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"_3_RH\">\n" +
                "\t\t\t\t\t\t\t<div class=\"_1GWY\">\n" +
                "\t\t\t\t\t\t\t\t<div class=\"_3fuh\">\n" +
                "\t\t\t\t\t\t\t\t\t<span>11-14</span>\n" +
                "\t\t\t\t\t\t\t\t\t<span>11:23</span>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"_1nyy\">\n" +
                "\t\t\t\t\t<a class=\"\" target=\"\" href=\"/photos/986002e1-27d7-4527-8be9-70506a0dd3b5\">\n" +
                "\t\t\t\t\t\t<div class=\"_3Yam _1Gim _1LNE _2G4A hUPH _2xhE\">\n" +
                "\t\t\t\t\t\t\t<img src=\"https://cdn-hz.skypixel.com/uploads/cn_files/photo/image/db0ad8ca-76d2-4ce6-b643-fd196d337d70.jpg@!550\" width=\"100%\" height=\"100%\">\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</a>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>";
        return style;
    }
}
