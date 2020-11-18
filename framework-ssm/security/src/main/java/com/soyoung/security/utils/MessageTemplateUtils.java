package com.soyoung.security.utils;

import com.alibaba.fastjson.JSONObject;
import com.soyoung.common.base.DataBaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 消息发送类
 */
@Component
@Configuration
public class MessageTemplateUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private static MessageTemplateUtils messageTemplateUtils;

    @Autowired
    private DataBaseEnum dataBaseEnum;

    /**
     *  日志
     */
    private static Logger logger = LoggerFactory.getLogger(MessageTemplateUtils.class);

    @PostConstruct
    public void init() {
        messageTemplateUtils = this;
        messageTemplateUtils.redisTemplate = this.redisTemplate;
        messageTemplateUtils.dataBaseEnum = this.dataBaseEnum;
    }

    /**
     * 获取AccessToken
     *
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        Object o = messageTemplateUtils.redisTemplate.opsForValue().get("SysytemAccessToken");
        if (o != null) {
            return o.toString();
        }
        //使用时可以手动放开
        /*Map<String, String> weixinMap = messageTemplateUtils.cpicSlotInvokeDao.getWxInfo();
        String appId = weixinMap.get("appid");
        String appsecret = weixinMap.get("appsecrt");*/
        String appId = "";
        String appsecret = "";
        // 访问地址
        String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String request_url = TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appsecret);
        String result = sendGet(request_url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (null != jsonObject) {
            String access_tocken = jsonObject.getString("access_token");
            // 放入缓存中，设置过期时间为90分钟
            messageTemplateUtils.redisTemplate.opsForValue().set("SysytemAccessToken", access_tocken, 90, TimeUnit.MINUTES);
            return access_tocken;
        }
        return null;
    }

    /**
     * 获取JsapiTicket
     */
    public static String getJsapiTicket() throws Exception {
        // 访问地址
        String accessToken = getAccessToken();
        String JsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        String request_url = JsapiTicketUrl.replace("ACCESS_TOKEN", accessToken);
        String result = sendGet(request_url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (null != jsonObject) {
            String ticket = jsonObject.getString("ticket");
            String expires_in = jsonObject.getString("expires_in");
            return ticket;
        }
        return null;
    }

    /**
     * 签名算法
     */
    public static Map<String, String> getSign(String url) throws Exception {
        String jsapiTicket;
        Object o = messageTemplateUtils.redisTemplate.opsForValue().get("jsapiticket");
        if (o != null) {
            jsapiTicket = o.toString();
        } else {
            jsapiTicket = getJsapiTicket();
            // 放入缓存中，设置过期时间为90分钟
            messageTemplateUtils.redisTemplate.opsForValue().set("jsapiticket", jsapiTicket, 90, TimeUnit.MINUTES);
        }

        Map<String, String> ret = new HashMap<>(5);
        String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        String string = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            // 对string字符串进行SHA-1加密处理
            crypt.update(string.getBytes("UTF-8"));
            // 对加密后字符串转成16进制
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonceStr);
        ret.put("signature", signature);
        return ret;
    }

    /**
     * 对加密后字符串转成16进制
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 发送get请求
     */
    public static String sendGet(String urlStr){
        String result = "";
        HttpURLConnection conn = null;
        try{
            URL realUrl = new URL(urlStr);
            logger.debug("微信url->"+urlStr);
            conn = (HttpURLConnection) realUrl.openConnection();
            // 返回结果-字节输入流转换成字符输入流，控制台输出字符
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            logger.error("exception->"+e);
        } finally {
            //关闭连接
            if(conn != null){
                conn.disconnect();
            }
        }
        return result;
    }
}
