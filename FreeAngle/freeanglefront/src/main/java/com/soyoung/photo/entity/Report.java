package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 举报表
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-02
 */
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    /**
     * 举报人
     */
    private Integer uid;

    /**
     * 被举报人
     */
    private Integer byuid;

    /**
     * 举报类型字典包外键
     */
    private Integer reType;

    /**
     * 举报值外键
     */
    private Integer reTypeCodeId;

    /**
     * 举报原因
     */
    private String explains;

    /**
     * 管理员是否审核(1、已审核，0、未审核)
     */
    private Integer isnot;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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
    public Integer getReType() {
        return reType;
    }

    public void setReType(Integer reType) {
        this.reType = reType;
    }
    public Integer getReTypeCodeId() {
        return reTypeCodeId;
    }

    public void setReTypeCodeId(Integer reTypeCodeId) {
        this.reTypeCodeId = reTypeCodeId;
    }
    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }
    public Integer getIsnot() {
        return isnot;
    }

    public void setIsnot(Integer isnot) {
        this.isnot = isnot;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Report{" +
        "rid=" + rid +
        ", uid=" + uid +
        ", byuid=" + byuid +
        ", reType=" + reType +
        ", reTypeCodeId=" + reTypeCodeId +
        ", explains=" + explains +
        ", isnot=" + isnot +
        ", createTime=" + createTime +
        "}";
    }
}
