package com.soyoung.photo.service;

import com.soyoung.photo.entity.State;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 国家表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public interface StateService extends IService<State> {

    //查询所有国家
    public List<State> getStateList();
}
