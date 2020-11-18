package com.soyoung.photo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 私聊表
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-05
 */
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 人员外键
     */
    private Integer cozeid;

    //用户实体
    @TableField(exist = false)
    private User cozeUser;

    /**
     * 人员外键
     */
    private Integer talkid;

    //用户实体
    @TableField(exist = false)
    private User talkUser;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public Integer getCozeid() {
        return cozeid;
    }

    public void setCozeid(Integer cozeid) {
        this.cozeid = cozeid;
    }
    public Integer getTalkid() {
        return talkid;
    }

    public void setTalkid(Integer talkid) {
        this.talkid = talkid;
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

    public User getCozeUser() {
        return cozeUser;
    }

    public void setCozeUser(User cozeUser) {
        this.cozeUser = cozeUser;
    }

    public User getTalkUser() {
        return talkUser;
    }

    public void setTalkUser(User talkUser) {
        this.talkUser = talkUser;
    }

    @Override
    public String toString() {
        return "Chat{" +
        "cid=" + cid +
        ", cozeid=" + cozeid +
        ", talkid=" + talkid +
        ", content=" + content +
        ", createTime=" + createTime +
        "}";
    }
}
