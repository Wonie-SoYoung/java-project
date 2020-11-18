package com.soyoung.photo.freeanglequeen.entity;

import java.util.Date;

public class Production {
    private int id;
    private String pName;
    private String proURL;
    private String hiapkURL;
    private int uid;
    private int protypeId;
    private String describes;
    private String label;
    private String duration;
    private int facilityId;
    private String location;
    private int copyrightId;
    private int privacyId;
    private int viewNum;
    private String createTime;
    private String updateTime;

    private Dictionary dictionaryProtype;
    private Facility facility;
    private Dictionary dictionaryCopyright;
    private Dictionary dictionaryPrivacy;
    private String[] picUrl;

    public Production() {
    }

    public Production(int id, String pName, String proURL, String hiapkURL, int uid, int protypeId, String describes, String label, String duration, int facilityId, String location, int copyrightId, int privacyId, int viewNum, String createTime, String updateTime, Dictionary dictionaryProtype, Facility facility, Dictionary dictionaryCopyright, Dictionary dictionaryPrivacy, String[] picUrl) {
        this.id = id;
        this.pName = pName;
        this.proURL = proURL;
        this.hiapkURL = hiapkURL;
        this.uid = uid;
        this.protypeId = protypeId;
        this.describes = describes;
        this.label = label;
        this.duration = duration;
        this.facilityId = facilityId;
        this.location = location;
        this.copyrightId = copyrightId;
        this.privacyId = privacyId;
        this.viewNum = viewNum;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.dictionaryProtype = dictionaryProtype;
        this.facility = facility;
        this.dictionaryCopyright = dictionaryCopyright;
        this.dictionaryPrivacy = dictionaryPrivacy;
        this.picUrl = picUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getProURL() {
        return proURL;
    }

    public void setProURL(String proURL) {
        this.proURL = proURL;
    }

    public String getHiapkURL() {
        return hiapkURL;
    }

    public void setHiapkURL(String hiapkURL) {
        this.hiapkURL = hiapkURL;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getProtypeId() {
        return protypeId;
    }

    public void setProtypeId(int protypeId) {
        this.protypeId = protypeId;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(int copyrightId) {
        this.copyrightId = copyrightId;
    }

    public int getPrivacyId() {
        return privacyId;
    }

    public void setPrivacyId(int privacyId) {
        this.privacyId = privacyId;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Dictionary getDictionaryProtype() {
        return dictionaryProtype;
    }

    public void setDictionaryProtype(Dictionary dictionaryProtype) {
        this.dictionaryProtype = dictionaryProtype;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Dictionary getDictionaryCopyright() {
        return dictionaryCopyright;
    }

    public void setDictionaryCopyright(Dictionary dictionaryCopyright) {
        this.dictionaryCopyright = dictionaryCopyright;
    }

    public Dictionary getDictionaryPrivacy() {
        return dictionaryPrivacy;
    }

    public void setDictionaryPrivacy(Dictionary dictionaryPrivacy) {
        this.dictionaryPrivacy = dictionaryPrivacy;
    }

    public String[] getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String[] picUrl) {
        this.picUrl = picUrl;
    }
}
