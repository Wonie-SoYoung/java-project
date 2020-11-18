package com.soyoung.photo.freeanglequeen.entity;

public class Comment {
    private int cid;
    private int uid;
    private int pid;
    private String content;
    private int replyid;
    private String createTime;

    private User user;

    public Comment() {
    }

    public Comment(int cid, int uid, int pid, String content, int replyid, String createTime, User user) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.content = content;
        this.replyid = replyid;
        this.createTime = createTime;
        this.user = user;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReplyid() {
        return replyid;
    }

    public void setReplyid(int replyid) {
        this.replyid = replyid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
