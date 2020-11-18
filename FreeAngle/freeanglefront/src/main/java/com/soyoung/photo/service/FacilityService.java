package com.soyoung.photo.service;

import com.soyoung.photo.entity.Facility;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 设备类型表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public interface FacilityService extends IService<Facility> {

    //查询父节点设备
    public List<Facility> getFatherFacilityList();

    //根据父节点id查询子节点
    public List<Facility> getisFidThenChildFacilityList(Integer fid);
}
