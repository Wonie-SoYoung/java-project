package com.soyoung.common.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: framework
 * @description: 全局配置项
 * @author: 梅晓寒
 * @create: 2020-08-07 19:48
 **/
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class DataBaseEnum {

    //全局日志输出
    public Boolean outFlag;

    /*
     * jwt所需数据
     */
    //请求密钥
    public String secret;
    //Token过期时间
    public Long expiration;
    //Header请求头字段，用于存放Token
    public String header;
    //Token前缀
    public String tokenStart;
    //Token刷新时间
    public Long refresh;

}
