package com.soyoung.photo.freeanglequeen.entity;

import java.util.Date;

public class User {
    private int id;
    private String userName;
    private char sex;
    private String intro;
    private String headURL;
    private String backgroundURL;
    private int prestige;
    private int stateid;
    private Date createTime;
    private Date updateTime;
    private int logins;
    private int ifrecommend;
    private int ifexpert;
    private int seal;

    private State state;


    public User() {
    }

    public User(int id, String userName, char sex, String intro, String headURL, String backgroundURL, int prestige, int stateid, Date createTime, Date updateTime, int logins, int ifrecommend, int ifexpert, int seal, State state) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.intro = intro;
        this.headURL = headURL;
        this.backgroundURL = backgroundURL;
        this.prestige = prestige;
        this.stateid = stateid;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.logins = logins;
        this.ifrecommend = ifrecommend;
        this.ifexpert = ifexpert;
        this.seal = seal;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    public String getBackgroundURL() {
        return backgroundURL;
    }

    public void setBackgroundURL(String backgroundURL) {
        this.backgroundURL = backgroundURL;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
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

    public int getLogins() {
        return logins;
    }

    public void setLogins(int logins) {
        this.logins = logins;
    }

    public int getIfrecommend() {
        return ifrecommend;
    }

    public void setIfrecommend(int ifrecommend) {
        this.ifrecommend = ifrecommend;
    }

    public int getIfexpert() {
        return ifexpert;
    }

    public void setIfexpert(int ifexpert) {
        this.ifexpert = ifexpert;
    }

    public int getSeal() {
        return seal;
    }

    public void setSeal(int seal) {
        this.seal = seal;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
