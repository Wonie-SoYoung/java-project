package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.State;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StateMapper {

    //根据 ID 查询国家信息
    public State selStateId(@Param("id") Integer id);

    /*
    * 陈海嘉
    * */
    //查询所有国家
    public List<State> getStateList();

}
