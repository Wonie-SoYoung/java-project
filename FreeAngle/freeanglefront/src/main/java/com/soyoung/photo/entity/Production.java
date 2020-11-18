package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import org.springframework.ui.Model;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 作品表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public class Production implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 作品名称
     */
    private String pName;

    /**
     * 作品URL
     */
    private String proURL;

    //作品URL分割列表
    @TableField(exist = false)
    private String[] proURLs;

    /**
     * 展示壁纸
     */
    private String hiapkURL;

    /**
     * 用户外键
     */
    private Integer uid;

    //用户详情
    @TableField(exist = false)
    private User user;

    /**
     * 作品类型外键
     */
    private Integer protypeId;

    /**
     * 描述
     */
    private String describes;

    /**
     * 标签
     */
    private String label;

    //标签分割列表
    @TableField(exist = false)
    private String[] labels;

    /**
     * 时长
     */
    private String duration;

    /**
     * 设备外键
     */
    private Integer facilityId;

    //设备详情
    @TableField(exist = false)
    private Facility facility;

    /**
     * 位置
     */
    private String location;

    /**
     * 版权外键
     */
    private Integer copyrightId;

    /**
     * 隐私外键
     */
    private Integer privacyId;

    /**
     * 观看次数
     */
    private Integer viewNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     *  点赞列表
     */
    @TableField(exist = false)
    private List<Likes> likesList;

    /**
     * 收藏列表
     */
    @TableField(exist = false)
    private List<Collect> collectList;

    /**
     * 作品勋章列表
     */
    @TableField(exist = false)
    private List<Medal> modelList;

    /**
     * 评论数量
     * @return
     */
    @TableField(exist = false)
    private Integer commentNum;

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public List<Medal> getModelList() {
        return modelList;
    }

    public void setModelList(List<Medal> modelList) {
        this.modelList = modelList;
    }

    public String[] getProURLs() {
        return proURLs;
    }

    public void setProURLs(String[] proURLs) {
        this.proURLs = proURLs;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getHiapkURL() {
        return hiapkURL;
    }

    public void setHiapkURL(String hiapkURL) {
        this.hiapkURL = hiapkURL;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public List<Likes> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Likes> likesList) {
        this.likesList = likesList;
    }

    public List<Collect> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<Collect> collectList) {
        this.collectList = collectList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }
    public String getProURL() {
        return proURL;
    }

    public void setProURL(String proURL) {
        this.proURL = proURL;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getProtypeId() {
        return protypeId;
    }

    public void setProtypeId(Integer protypeId) {
        this.protypeId = protypeId;
    }
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Integer getCopyrightId() {
        return copyrightId;
    }

    public void setCopyrightId(Integer copyrightId) {
        this.copyrightId = copyrightId;
    }
    public Integer getPrivacyId() {
        return privacyId;
    }

    public void setPrivacyId(Integer privacyId) {
        this.privacyId = privacyId;
    }
    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
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
        return "Production{" +
        "id=" + id +
        ", pName=" + pName +
        ", proURL=" + proURL +
        ", uid=" + uid +
        ", protypeId=" + protypeId +
        ", describes=" + describes +
        ", label=" + label +
        ", duration=" + duration +
        ", facilityId=" + facilityId +
        ", location=" + location +
        ", copyrightId=" + copyrightId +
        ", privacyId=" + privacyId +
        ", viewNum=" + viewNum +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
