package com.soyoung.photo.freeanglequeen.entity;

public class Medal {
    private int mid;
    private String murl;
    private String describes;
    private String createTime;
    private String updateTime;

    public Medal() {
    }

    public Medal(int mid, String murl, String describes, String createTime, String updateTime) {
        this.mid = mid;
        this.murl = murl;
        this.describes = describes;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
