package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "nid", type = IdType.AUTO)
    private Integer nid;

    /**
     * 管理员账户
     */
    private Integer aid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 标签样式
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }
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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Notice{" +
        "nid=" + nid +
        ", aid=" + aid +
        ", uid=" + uid +
        ", content=" + content +
        ", createTime=" + createTime +
        "}";
    }
}
