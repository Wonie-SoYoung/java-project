package com.soyoung.photo.freeanglequeen.entity;

import java.util.Date;

public class Messages {
    private Integer mid;
    private Integer uid;
    private Integer byuid;
    private Integer mType;
    private Integer keyid;
    private Integer isNot;
    private Integer prokeyid;
    private Date createTime;

    public Messages() {
    }

    public Messages(Integer mid, Integer uid, Integer byuid, Integer mType, Integer keyid, Integer isNot, Integer prokeyid, Date createTime) {
        this.mid = mid;
        this.uid = uid;
        this.byuid = byuid;
        this.mType = mType;
        this.keyid = keyid;
        this.isNot = isNot;
        this.prokeyid = prokeyid;
        this.createTime = createTime;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getByuid() {
        return byuid;
    }

    public void setByuid(Integer byuid) {
        this.byuid = byuid;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public Integer getIsNot() {
        return isNot;
    }

    public void setIsNot(Integer isNot) {
        this.isNot = isNot;
    }

    public Integer getProkeyid() {
        return prokeyid;
    }

    public void setProkeyid(Integer prokeyid) {
        this.prokeyid = prokeyid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
