package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
public class Messages implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
     * 通知人
     */
    private Integer uid;

    @TableField(exist = false)
    private User user;

    /**
     * 被通知人
     */
    private Integer byuid;

    /**
     * 消息类型外键(点赞、评论、关注、收藏、通知、私信)
     */
    private Integer mType;

    /**
     * 消息外键
     */
    private Integer keyid;

    /**
     * 作品外键
     */
    private Integer prokeyid;

    @TableField(exist = false)
    private Likes likes;

    @TableField(exist = false)
    private Comment comment;

    @TableField(exist = false)
    private Attention attention;

    @TableField(exist = false)
    private Collect collect;

    @TableField(exist = false)
    private Notice notice;

    @TableField(exist = false)
    private Chat chat;

    @TableField(exist = false)
    private Production production;

    @TableField(exist = false)
    private Queenuser queenuser;

    /**
     * 是否已读(0:否、1:是)
     */
    private Integer isNot;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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
    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }
    public Integer getKeyid() {
        return keyid;
    }

    public Queenuser getQueenuser() {
        return queenuser;
    }

    public void setQueenuser(Queenuser queenuser) {
        this.queenuser = queenuser;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }
    public Integer getIsNot() {
        return isNot;
    }

    public void setIsNot(Integer isNot) {
        this.isNot = isNot;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Attention getAttention() {
        return attention;
    }

    public Integer getProkeyid() {
        return prokeyid;
    }

    public void setProkeyid(Integer prokeyid) {
        this.prokeyid = prokeyid;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public void setAttention(Attention attention) {
        this.attention = attention;
    }

    public Collect getCollect() {
        return collect;
    }

    public void setCollect(Collect collect) {
        this.collect = collect;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Messages{" +
        "mid=" + mid +
        ", uid=" + uid +
        ", byuid=" + byuid +
        ", mType=" + mType +
        ", keyid=" + keyid +
        ", isNot=" + isNot +
        ", createTime=" + createTime +
        "}";
    }

    public Messages(Integer uid, Integer byuid, Integer mType, Integer keyid) {
        this.uid = uid;
        this.byuid = byuid;
        this.mType = mType;
        this.keyid = keyid;
    }

    public Messages(Integer uid, Integer byuid, Integer mType, Integer keyid,Integer prokeyid) {
        this.uid = uid;
        this.byuid = byuid;
        this.mType = mType;
        this.keyid = keyid;
        this.prokeyid=prokeyid;
    }

    public Messages() {
    }
}
