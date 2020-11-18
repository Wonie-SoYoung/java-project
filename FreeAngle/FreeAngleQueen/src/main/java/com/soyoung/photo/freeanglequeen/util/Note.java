package com.soyoung.photo.freeanglequeen.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用于短信接口调用
 */
public class Note {


    //生成验证码
    public static String getNumber(){
        Random random = new Random();
        String number="";
        for (int i=0;i<4;i++)
        {
            number+=random.nextInt(10);
        }
        return number;
    }
}
