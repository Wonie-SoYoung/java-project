package com.soyoung.photo.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.soyoung.photo.util.convert.TransApi;

/**
 * 使用百度API翻译
 */
public class Translate {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20191107000353954";
    private static final String SECURITY_KEY = "40C8dADfkRSiXoIQJnvP";

    public static String gainresult(String content,String type){
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String str = api.getTransResult(content, "auto", type);
        //输出结果，即json字段
        JsonObject jsonObj = (JsonObject)new JsonParser().parse(str);
        //解析json字段
        String res = jsonObj.get("trans_result").toString();
        //获取json字段中的 result字段，因为result字段本身即是一个json数组字段，所以要进一步解析
        JsonArray js = new JsonParser().parse(res).getAsJsonArray();
        //解析json数组字段
        jsonObj = (JsonObject)js.get(0);
        // result数组中只有一个元素，所以直接取第一个元素
        return jsonObj.get("dst").getAsString();
    }
}
