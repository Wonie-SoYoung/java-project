package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Facility;
import org.apache.ibatis.annotations.Param;

public interface FacilityMapper {

    //根据 ID 查询所有设备信息
    public Facility selFacilityID(@Param("id") Integer id);
}
