package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 关注表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-13
 */
public class Attention implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    /**
     * 关注人外键
     */
    private Integer uid;

    /**
     * 被关注人外键
     */
    private Integer byuid;

    /**
     * 创建时间
     */
    private Date createTime;

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
    public Integer getByuid() {
        return byuid;
    }

    public void setByuid(Integer byuid) {
        this.byuid = byuid;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Attention{" +
        "aid=" + aid +
        ", uid=" + uid +
        ", byuid=" + byuid +
        ", createTime=" + createTime +
        "}";
    }
}
