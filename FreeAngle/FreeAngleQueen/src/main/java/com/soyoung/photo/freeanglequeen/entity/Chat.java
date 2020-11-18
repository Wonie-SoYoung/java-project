package com.soyoung.photo.freeanglequeen.entity;

public class Chat {
    private String cid;
    private String cozeid;
    private String talkid;
    private String content;
    private String createTime;

    public Chat() {
    }

    public Chat(String cid, String cozeid, String talkid, String content, String createTime) {
        this.cid = cid;
        this.cozeid = cozeid;
        this.talkid = talkid;
        this.content = content;
        this.createTime = createTime;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCozeid() {
        return cozeid;
    }

    public void setCozeid(String cozeid) {
        this.cozeid = cozeid;
    }

    public String getTalkid() {
        return talkid;
    }

    public void setTalkid(String talkid) {
        this.talkid = talkid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
