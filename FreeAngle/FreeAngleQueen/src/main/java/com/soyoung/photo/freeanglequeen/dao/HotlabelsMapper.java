package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Hotlabels;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotlabelsMapper {

    /*
    * 范宏伦
    * */
    //一级标签查询
    public List<Hotlabels> selLabelTableListHId();

    //二级标签查询
    public List<Hotlabels> selLabelTableListParentId(@Param("parentId")Integer parentId);

    //查询全部标签
    public List<Hotlabels> selLabelTableListAll(@Param("parentId")Integer parentId,
                                                @Param("hId") Integer hId);

    //根据 hId 查询标签信息
    public Hotlabels selHotlabelsHid(@Param("hId") Integer hId);

    //添加标签信息
    public Integer insHotlabels(Hotlabels hotlabels);

    //根据 hId 修改标签信息
    public Integer updHotlabelsHid(Hotlabels hotlabels);

    //查询热门标签的数据条数 （前台热门标签的显示条数不得超过24条）
    public Integer selHotlabelsIshot();

    //根据 hId 删除标签
    public Integer delHotlabelsHid(@Param("hId") Integer hId);

}
