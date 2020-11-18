package com.soyoung.photo.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    //把List<Map>分成List<List<Map<String,Object>>>
    public static List<List<Map<String,Object>>> getMapDivision(List<Map<String,Object>> maps){
        List<List<Map<String,Object>>> lists=new ArrayList<>();
        //所需数据
        boolean isNot=true;
        List<Map<String,Object>> list1=new ArrayList<>();
        List<Map<String,Object>> list2=new ArrayList<>();
        List<Map<String,Object>> list3=new ArrayList<>();
        Iterator<Map<String,Object>> iter=maps.iterator();
        while (isNot){
            if(iter.hasNext()){
                list1.add(iter.next());
            }else{
                isNot=false;
            }
            if(iter.hasNext()){
                list2.add(iter.next());
            }else{
                isNot=false;
            }
            if(iter.hasNext()){
                list3.add(iter.next());
            }else{
                isNot=false;
            }
        }
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        return lists;
    }
}
