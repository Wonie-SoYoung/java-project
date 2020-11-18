package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Medal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedalMapper {

    /*
    * 范宏伦
    * */
    //查询所有勋章信息
    public List<Medal> selMedalList();

    //根据 mid 查询勋章信息
    public Medal selMedalMid(@Param("mid") Integer mid);

    //添加勋章信息
    public Integer insMedal(Medal medal);

    //修改勋章信息
    public Integer updMedalMid(Medal medal);

    /*
    * 陈海嘉
    * */
    //查询全部
    public List<Medal> getMdealList();


}
