package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-11
 */
public class Queenuser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像URL
     */
    private String headURL;

    /**
     * 用户状态（0正常 1停用）
     */
    private Integer ustart;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 描述

     */
    private String describe;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }
    public Integer getUstart() {
        return ustart;
    }

    public void setUstart(Integer ustart) {
        this.ustart = ustart;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Queenuser{" +
        "id=" + id +
        ", userName=" + userName +
        ", password=" + password +
        ", phone=" + phone +
        ", email=" + email +
        ", sex=" + sex +
        ", headURL=" + headURL +
        ", ustart=" + ustart +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", describe=" + describe +
        ", delFlag=" + delFlag +
        "}";
    }
}
