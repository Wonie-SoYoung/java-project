package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 登录表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public class Oauth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户外键
     */
    private Integer uid;

    /**
     * 登录方式外键
     */
    private Integer oatype;

    /**
     * 唯一id （qq号、微信、手机号、微博号）
     */
    private String uuid;

    /**
     * 密码
     */
    private String password;

    /**
     * accessToken
     */
    private String accessToken;

    /**
     * 日期
     */
    private Integer expiredTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Integer expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getOatype() {
        return oatype;
    }

    public void setOatype(Integer oatype) {
        this.oatype = oatype;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Oauth{" +
        "id=" + id +
        ", uid=" + uid +
        ", oatype=" + oatype +
        ", uuid=" + uuid +
        ", password=" + password +
        "}";
    }
}
