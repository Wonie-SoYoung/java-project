package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 点赞记录表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-27
 */
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "lid", type = IdType.AUTO)
    private Integer lid;

    /**
     * 用户外键
     */
    private Integer uid;

    /**
     * 作品外键
     */
    private Integer pid;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Likes{" +
        "lid=" + lid +
        ", uid=" + uid +
        ", pid=" + pid +
        ", createTime=" + createTime +
        "}";
    }
}
