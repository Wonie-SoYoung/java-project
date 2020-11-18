package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户账户
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 简介
     */
    private String intro;

    /**
     * 头像URL
     */
    private String headURL;

    /**
     * 背景图片URL
     */
    private String backgroundURL;

    /**
     * 声望
     */
    private Integer prestige;

    /**
     * 国家外键
     */
    private Integer stateid;

    //国家外键
    @TableField(exist =false)
    private State state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 登录次数
     */
    private Integer logins;

    /**
     * 是否推荐
     */
    private Integer ifrecommend;

    /**
     * 是否作品达人
     */
    private Integer ifexpert;

    /**
     * 封号时间
     */
    private Integer seal;

    //粉丝列表
    @TableField(exist = false)
    private List<Attention> fansList;
    //关注列表
    @TableField(exist = false)
    private List<Attention> focusList;
    //作品列表
    @TableField(exist = false)
    private List<Production> productionList;

    public List<Production> getProductionList() {
        return productionList;
    }

    public void setProductionList(List<Production> productionList) {
        this.productionList = productionList;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Attention> getFansList() {
        return fansList;
    }

    public void setFansList(List<Attention> fansList) {
        this.fansList = fansList;
    }

    public List<Attention> getFocusList() {
        return focusList;
    }

    public void setFocusList(List<Attention> focusList) {
        this.focusList = focusList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }
    public String getBackgroundURL() {
        return backgroundURL;
    }

    public void setBackgroundURL(String backgroundURL) {
        this.backgroundURL = backgroundURL;
    }
    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }
    public Integer getStateid() {
        return stateid;
    }

    public void setStateid(Integer stateid) {
        this.stateid = stateid;
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
    public Integer getLogins() {
        return logins;
    }

    public void setLogins(Integer logins) {
        this.logins = logins;
    }
    public Integer getIfrecommend() {
        return ifrecommend;
    }

    public void setIfrecommend(Integer ifrecommend) {
        this.ifrecommend = ifrecommend;
    }
    public Integer getIfexpert() {
        return ifexpert;
    }

    public void setIfexpert(Integer ifexpert) {
        this.ifexpert = ifexpert;
    }

    public Integer getSeal() {
        return seal;
    }

    public void setSeal(Integer seal) {
        this.seal = seal;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", userName=" + userName +
        ", sex=" + sex +
        ", intro=" + intro +
        ", headURL=" + headURL +
        ", backgroundURL=" + backgroundURL +
        ", prestige=" + prestige +
        ", stateid=" + stateid +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", logins=" + logins +
        ", ifrecommend=" + ifrecommend +
        ", ifexpert=" + ifexpert +
        "}";
    }
}
