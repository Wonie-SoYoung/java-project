package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.MedalMapper;
import com.soyoung.photo.freeanglequeen.entity.Medal;
import com.soyoung.photo.freeanglequeen.service.MedalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MedalServiceImpl implements MedalService {

    @Resource
    public MedalMapper medalMapper;

    /*
    * 范宏伦
    * */
    @Override
    public List<Medal> selMedalList() {
        return medalMapper.selMedalList();
    }

    @Override
    public Medal selMedalMid(Integer mid) {
        return medalMapper.selMedalMid(mid);
    }

    @Override
    public Integer insMedal(Medal medal) {
        return medalMapper.insMedal(medal);
    }

    @Override
    public Integer updMedalMid(Medal medal) {
        return medalMapper.updMedalMid(medal);
    }

    /*
    * 陈海嘉
    * */
    @Override
    public List<Medal> getMdealList() {
        return medalMapper.getMdealList();
    }
}
