package com.soyoung.photo.freeanglequeen.entity;

public class Facility {
    private int id;
    private String fName;
    private int parentId;
    private int minuteId;

    public Facility() {
    }

    public Facility(int id, String fName, int parentId, int minuteId) {
        this.id = id;
        this.fName = fName;
        this.parentId = parentId;
        this.minuteId = minuteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getMinuteId() {
        return minuteId;
    }

    public void setMinuteId(int minuteId) {
        this.minuteId = minuteId;
    }
}
