package com.soyoung.photo.freeanglequeen.service;

import com.soyoung.photo.freeanglequeen.entity.State;

import java.util.List;

public interface StateService {

    /*
    * 范宏伦
    * */
    //根据 ID 查询国家信息
    public State selStateId(Integer id);

    /*
    * 陈海嘉
    * */
    //查询所有国家
    public List<State> getStateList();
}
