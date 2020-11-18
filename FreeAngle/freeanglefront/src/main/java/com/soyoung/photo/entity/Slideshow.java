package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 轮播图表
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-12
 */
public class Slideshow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    /**
     * 图片名称
     */
    private String picName;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 图片描述
     */
    private String description;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    @Override
    public String toString() {
        return "Slideshow{" +
        "sid=" + sid +
        ", picName=" + picName +
        ", picUrl=" + picUrl +
        ", description=" + description +
        "}";
    }
}
