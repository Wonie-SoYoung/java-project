package com.soyoung.photo.freeanglequeen.entity;

public class Report {
    private int rid;
    private int uid;
    private int byuid;
    private int reType;
    private int reTypeCodeId;
    private String explains;
    private int isnot;
    private String createTime;

    private User uuser;
    private User byuUser;

    public Report() {
    }

    public Report(int rid, int uid, int byuid, int reType, int reTypeCodeId, String explains, int isnot, String createTime, User uuser, User byuUser) {
        this.rid = rid;
        this.uid = uid;
        this.byuid = byuid;
        this.reType = reType;
        this.reTypeCodeId = reTypeCodeId;
        this.explains = explains;
        this.isnot = isnot;
        this.createTime = createTime;
        this.uuser = uuser;
        this.byuUser = byuUser;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getByuid() {
        return byuid;
    }

    public void setByuid(int byuid) {
        this.byuid = byuid;
    }

    public int getReType() {
        return reType;
    }

    public void setReType(int reType) {
        this.reType = reType;
    }

    public int getReTypeCodeId() {
        return reTypeCodeId;
    }

    public void setReTypeCodeId(int reTypeCodeId) {
        this.reTypeCodeId = reTypeCodeId;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public int getIsnot() {
        return isnot;
    }

    public void setIsnot(int isnot) {
        this.isnot = isnot;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public User getUuser() {
        return uuser;
    }

    public void setUuser(User uuser) {
        this.uuser = uuser;
    }

    public User getByuUser() {
        return byuUser;
    }

    public void setByuUser(User byuUser) {
        this.byuUser = byuUser;
    }
}
