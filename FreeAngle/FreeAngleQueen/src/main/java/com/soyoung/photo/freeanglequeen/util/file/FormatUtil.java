package com.soyoung.photo.freeanglequeen.util.file;

import java.text.DecimalFormat;

/**
 * 格式化工具类
 */
public class FormatUtil {

    //整数相除 保留一位小数
    public static String division(long a ,long b){
        String result = "";
        float num =(float)a/b;
        DecimalFormat df = new DecimalFormat("0.0");
        result = df.format(num);
        return result;
    }
}
