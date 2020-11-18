package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.StateMapper;
import com.soyoung.photo.freeanglequeen.entity.State;
import com.soyoung.photo.freeanglequeen.service.StateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StateServiceImpl implements StateService {

    @Resource
    public StateMapper stateMapper;

    /*
    * 范宏伦
    * */
    @Override
    public State selStateId(Integer id) {
        return stateMapper.selStateId(id);
    }

    /*
    * 陈海嘉
    * */
    @Override
    public List<State> getStateList() {
        return stateMapper.getStateList();
    }
}
