package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Facility;
import com.soyoung.photo.mapper.FacilityMapper;
import com.soyoung.photo.service.FacilityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备类型表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Service
public class FacilityServiceImpl extends ServiceImpl<FacilityMapper, Facility> implements FacilityService {
    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public List<Facility> getFatherFacilityList() {
        return facilityMapper.selectList(
                new EntityWrapper<Facility>()
                .isNull("parentId")
        );
    }

    @Override
    public List<Facility> getisFidThenChildFacilityList(Integer fid) {
        return facilityMapper.selectList(
                new EntityWrapper<Facility>()
                .eq("parentId",fid)
        );
    }
}
