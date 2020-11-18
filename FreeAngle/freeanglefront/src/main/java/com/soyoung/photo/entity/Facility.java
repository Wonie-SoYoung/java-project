package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 设备类型表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备名称
     */
    private String fName;

    /**
     * 父标签
     */
    private Integer parentId;

    /**
     * 详细外键
     */
    private Integer minuteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getMinuteId() {
        return minuteId;
    }

    public void setMinuteId(Integer minuteId) {
        this.minuteId = minuteId;
    }

    @Override
    public String toString() {
        return "Facility{" +
        "id=" + id +
        ", fName=" + fName +
        ", parentId=" + parentId +
        ", minuteId=" + minuteId +
        "}";
    }
}
