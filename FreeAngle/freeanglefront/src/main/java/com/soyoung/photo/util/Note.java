package com.soyoung.photo.util;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用于短信接口调用
 */
public class Note {

    //发送短信
    public static Map<String,String> getCode(String phone){
        String number=getNumber();
        StringBuffer sb=new StringBuffer();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={
                new NameValuePair("Uid", "养乐多牛奶"),
                new NameValuePair("Key", "d41d8cd98f00b204e980"),
                new NameValuePair("smsMob",phone),
                new NameValuePair("smsText","您正在进行自由角度短信验证,验证码:"+number+"如果不是你本人操作,请立马冻结您的账号。防止财务损失!")
        };
        post.setRequestBody(data);
        try {
            client.executeMethod(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers){
            System.out.println(h.toString());
        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.releaseConnection();
        System.out.println(result);
        if(result!=null||result!=""){
            if(result.equals("1")){
                sb.append("{\"errey\":\"OK\"}");
            }else{
                sb.append("{\"errey\":\"ONE\"}");
            }
        }else{
            sb.append("{\"errey\":\"ONE\"}");
        }
        System.out.println(sb.toString());
        Map<String,String> stringMap=new HashMap<>();
        stringMap.put("result",result);
        stringMap.put("note",number);
        return stringMap;
    }


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
