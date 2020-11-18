package com.soyoung.photo.freeanglequeen.entity;

import java.util.Date;

/**
 * 后台用户类
 */
public class QueenUser {
    private Integer id; //后台用户id
    private String userName; //后台用户名称
    private String password; //后台密码
    private String phone;
    private String email;
    private char sex; //管理人员姓名
    private String headURL; //头像地址
    private Integer ustart;
    private Date createTime;//创建日期
    private Date updateTime;//修改日期
    private String describe;
    private Integer delFlag;

    public QueenUser(Integer id, String userName, String password, String phone, String email, char sex, String headURL, Integer ustart, Date createTime, Date updateTime, Integer delFlag,String describe) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.headURL = headURL;
        this.ustart = ustart;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.describe=describe;
        this.delFlag = delFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public QueenUser() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUstart() {
        return ustart;
    }

    public void setUstart(Integer ustart) {
        this.ustart = ustart;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
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
}
