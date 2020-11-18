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
 * 评论表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-29
 */
public class Comment implements Serializable {

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
     * 评论作品外键
     */
    private Integer pid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复外键
     */
    private Integer replyid;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 评论用户
     * @return
     */
    @TableField(exist = false)
    private User user;

    /**
     * 回复实体
     * @return
     */
    @TableField(exist = false)
    private Comment replcomment;

    public Comment getReplcomment() {
        return replcomment;
    }

    public void setReplcomment(Comment replcomment) {
        this.replcomment = replcomment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
        "cid=" + cid +
        ", uid=" + uid +
        ", pid=" + pid +
        ", content=" + content +
        ", replyid=" + replyid +
        ", createTime=" + createTime +
        "}";
    }
}
