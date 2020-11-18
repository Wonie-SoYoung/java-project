package com.soyoung.security.utils;

import com.alibaba.fastjson.JSONObject;
import com.soyoung.common.utils.Log;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpClient工具类
 */

public class HttpClientUtils {
 
    /**
     * 以jsonString形式发送HttpPost的Json请求，String形式返回响应结果
     *
     * @param url
     * @param jsonString
     * @return
     */
    public static String sendPostJsonStr(String url, String jsonString) throws IOException {
        if (jsonString == null || jsonString.isEmpty()) {
            return sendPost(url);
        }
        String resp = "";
        StringEntity entityStr = new StringEntity(jsonString,
                ContentType.create("text/plain", "UTF-8"));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entityStr);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } catch (Exception e) {
            Log.error(e.getMessage());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    Log.error(e.getMessage());
                }
            }
        }
        if (resp == null || resp.equals("")) {
            return "";
        }
        return resp;
    }
 
    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url) throws IOException {
        // 1.获得一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 2.生成一个post请求
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            // 3.执行get请求并返回结果
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        // 4.处理结果，这里将结果返回为字符串
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            Log.error(e.getMessage());
        }
        return result;
    }


    /**
     * 发送请求
     * @param Url
     * @param Method
     * @param Output
     * @return
     * @throws Exception
     */
    public static JSONObject HttpsUtil(String Url, String Method, String Output) throws Exception {
        JSONObject jsonObject = null;
        URL conn_url = new URL(Url);
        HttpURLConnection conn = (HttpsURLConnection) conn_url.openConnection();
        conn.setRequestMethod(Method);
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        conn.connect();
        //output获取access_token是不会用到
        if (Output != null) {
            OutputStream outputstream = conn.getOutputStream();
            //字符集，防止出现中文乱码
            outputstream.write(Output.getBytes("UTF-8"));
            outputstream.close();
        }
        //正常返回代码为200
        if (conn.getResponseCode() == 200) {
            InputStream stream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(stream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            stream.close();
            conn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        }
        Log.info(conn.getResponseCode());
        return jsonObject;
    }
}
 
