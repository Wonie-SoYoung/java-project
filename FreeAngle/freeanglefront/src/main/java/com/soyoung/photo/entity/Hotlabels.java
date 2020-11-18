package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 热门标签表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public class Hotlabels implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "hId", type = IdType.AUTO)
    private Integer hId;

    /**
     * 标签名称
     */
    private String hName;

    /**
     * 标签图片地址
     */
    private String hUrl;

    /**
     * 父标签
     */
    private Integer parentId;

    /**
     * 热门(0:否，1:是)
     */
    private Integer ishot;

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }
    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }
    public String gethUrl() {
        return hUrl;
    }

    public void sethUrl(String hUrl) {
        this.hUrl = hUrl;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    @Override
    public String toString() {
        return "Hotlabels{" +
        "hId=" + hId +
        ", hName=" + hName +
        ", hUrl=" + hUrl +
        ", parentId=" + parentId +
        ", ishot=" + ishot +
        "}";
    }
}
