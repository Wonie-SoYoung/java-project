package com.soyoung.photo.freeanglequeen.entity;

public class AerialPoint {
    private int aid;
    private String picName;
    private String picUrl;
    private String description;

    public AerialPoint() {
    }

    public AerialPoint(int aid, String picName, String picUrl, String description) {
        this.aid = aid;
        this.picName = picName;
        this.picUrl = picUrl;
        this.description = description;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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
}
