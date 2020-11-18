package com.soyoung.photo.freeanglequeen.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.dao.HotlabelsMapper;
import com.soyoung.photo.freeanglequeen.entity.Hotlabels;
import com.soyoung.photo.freeanglequeen.service.HotlabelsService;
import com.soyoung.photo.freeanglequeen.util.Translate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.soyoung.photo.freeanglequeen.util.Constants.ENGLISHLANGUGE;

@Service
public class HotlabelsServiceImpl implements HotlabelsService {

    @Resource
    public HotlabelsMapper labelTableMapper;


    @Override
    public List<Hotlabels> selLabelTableListHId() {
        List<Hotlabels> list = labelTableMapper.selLabelTableListHId();
        return list;
    }

    @Override
    public List<Hotlabels> selLabelTableListParentId(Integer parentId) {
        List<Hotlabels> list = labelTableMapper.selLabelTableListParentId(parentId);
        return list;
    }

    @Override
    public PageInfo<Hotlabels> selLabelTableListAll(Integer parentId, Integer hId) {
        List<Hotlabels> list = labelTableMapper.selLabelTableListAll(parentId,hId);
        PageInfo<Hotlabels> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Hotlabels selHotlabelsHid(Integer hId) {
        return labelTableMapper.selHotlabelsHid(hId);
    }

    @Override
    public Integer insHotlabels(Hotlabels hotlabels) {
        String finehName = Translate.gainresult(hotlabels.gethName(), ENGLISHLANGUGE);
        hotlabels.setFinehName(finehName);
        return labelTableMapper.insHotlabels(hotlabels);
    }

    @Override
    public Integer updHotlabelsHid(Hotlabels hotlabels) {
        String finehName = Translate.gainresult(hotlabels.gethName(), ENGLISHLANGUGE);
        hotlabels.setFinehName(finehName);
        return labelTableMapper.updHotlabelsHid(hotlabels);
    }

    @Override
    public Integer selHotlabelsIshot() {
        return labelTableMapper.selHotlabelsIshot();
    }

    @Override
    public Integer delHotlabelsHid(Integer hId) {
        return labelTableMapper.delHotlabelsHid(hId);
    }

    @Override
    public List<Hotlabels> getcity() {
        return null;
    }

    @Override
    public Page<Hotlabels> pageCity() {
        return null;
    }
}
