package com.soyoung.photo.freeanglequeen.entity;

import java.util.Date;

public class Notice {
    private Integer nid;
    private Integer aid;
    private Integer uid;
    private String content;
    private Date createTime;

    public Notice(Integer nid, Integer aid, Integer uid, String content, Date createTime) {
        this.nid = nid;
        this.aid = aid;
        this.uid = uid;
        this.content = content;
        this.createTime = createTime;
    }

    public Notice() {
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
