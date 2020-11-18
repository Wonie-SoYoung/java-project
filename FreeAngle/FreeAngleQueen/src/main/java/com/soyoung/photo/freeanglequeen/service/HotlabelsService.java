package com.soyoung.photo.freeanglequeen.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.entity.Hotlabels;

import java.util.List;

public interface HotlabelsService {

    //一级标签查询
    public List<Hotlabels> selLabelTableListHId();

    //二级标签查询
    public List<Hotlabels> selLabelTableListParentId(Integer parentId);

    //根据分页查询全部
    public PageInfo<Hotlabels> selLabelTableListAll(Integer parentId, Integer hId);

    //根据 hId 查询标签信息
    public Hotlabels selHotlabelsHid(Integer hId);

    //添加标签信息
    public Integer insHotlabels(Hotlabels hotlabels);

    //根据 hId 修改标签信息
    public Integer updHotlabelsHid(Hotlabels hotlabels);

    //查询热门标签的数据条数 （前台热门标签的显示条数不得超过24条）
    public Integer selHotlabelsIshot();

    //根据 hId 删除标签
    public Integer delHotlabelsHid(Integer hId);


    //PageHelper实现分页（测试用例）
    public List<Hotlabels> getcity();
    public Page<Hotlabels> pageCity();
}
