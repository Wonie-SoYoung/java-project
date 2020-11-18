package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.FacilityMapper;
import com.soyoung.photo.freeanglequeen.entity.Facility;
import com.soyoung.photo.freeanglequeen.service.FacilityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Resource
    public FacilityMapper facilityMapper;

    @Override
    public Facility selFacilityID(Integer id) {
        return facilityMapper.selFacilityID(id);
    }
}
