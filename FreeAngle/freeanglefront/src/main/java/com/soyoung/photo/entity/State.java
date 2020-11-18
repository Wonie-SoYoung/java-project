package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 国家表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 国家地区名称
     */
    private String sName;

    /**
     * 国旗
     */
    private String sUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSUrl() {
        return sUrl;
    }

    public void setSUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    @Override
    public String toString() {
        return "State{" +
        "id=" + id +
        ", sName=" + sName +
        ", sUrl=" + sUrl +
        "}";
    }
}
