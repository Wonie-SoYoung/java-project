package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 作品勋章表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-14
 */
public class Medal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "mId", type = IdType.AUTO)
    private Integer mId;

    /**
     * 勋章URL
     */
    private String mUrl;

    /**
     * 勋章描述
     */
    private String describes;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    //数量
    @TableField(exist = false)
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }
    public String getMUrl() {
        return mUrl;
    }

    public void setMUrl(String mUrl) {
        this.mUrl = mUrl;
    }
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Medal{" +
        "mId=" + mId +
        ", mUrl=" + mUrl +
        ", describes=" + describes +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
