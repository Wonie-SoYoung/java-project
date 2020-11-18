package com.soyoung.photo.service.impl;

import com.soyoung.photo.entity.State;
import com.soyoung.photo.mapper.StateMapper;
import com.soyoung.photo.service.StateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 国家表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Service
public class StateServiceImpl extends ServiceImpl<StateMapper, State> implements StateService {
    @Autowired
    private StateMapper stateMapper;

    @Override
    public List<State> getStateList() {
        return stateMapper.selectList(null);
    }
}
