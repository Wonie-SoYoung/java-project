package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-28
 */
public class Collect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 人员外键
     */
    private Integer uid;


    /**
     * 收藏作品外键
     */
    private Integer pid;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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
        return "Collect{" +
        "cid=" + cid +
        ", uid=" + uid +
        ", pid=" + pid +
        ", createTime=" + createTime +
        "}";
    }
}
