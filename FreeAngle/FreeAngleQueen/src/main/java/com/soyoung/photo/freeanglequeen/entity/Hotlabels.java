package com.soyoung.photo.freeanglequeen.entity;

public class Hotlabels {
    private int hId;
    private String hName;
    private String finehName;
    private String hUrl;
    private int parentId;
    private int ishot;

    public Hotlabels() {
    }

    public Hotlabels(int hId, String hName, String finehName, String hUrl, int parentId, int ishot) {
        this.hId = hId;
        this.hName = hName;
        this.finehName = finehName;
        this.hUrl = hUrl;
        this.parentId = parentId;
        this.ishot = ishot;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getFinehName() {
        return finehName;
    }

    public void setFinehName(String finehName) {
        this.finehName = finehName;
    }

    public String gethUrl() {
        return hUrl;
    }

    public void sethUrl(String hUrl) {
        this.hUrl = hUrl;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getIshot() {
        return ishot;
    }

    public void setIshot(int ishot) {
        this.ishot = ishot;
    }
}
