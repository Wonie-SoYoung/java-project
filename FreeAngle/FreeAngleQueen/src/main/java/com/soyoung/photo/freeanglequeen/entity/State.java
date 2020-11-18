package com.soyoung.photo.freeanglequeen.entity;

public class State {
    private int id;
    private String sName;
    private String sUrl;

    public State() {
    }

    public State(int id, String sName, String sUrl) {
        this.id = id;
        this.sName = sName;
        this.sUrl = sUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }
}
