package com.soyoung.photo.freeanglequeen.entity;

public class SlideShow {
    private int sid;
    private String picName;
    private String picUrl;
    private String description;
    private int rank;

    public SlideShow() {
    }

    public SlideShow(int sid, String picName, String picUrl, String description, int rank) {
        this.sid = sid;
        this.picName = picName;
        this.picUrl = picUrl;
        this.description = description;
        this.rank = rank;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
