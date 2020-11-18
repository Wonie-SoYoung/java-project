package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.SlideShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlideShowMapper {

    //查询所有轮播图信息
    public List<SlideShow> selSlideShowListAll();

    //根据 ID 查询轮播图信息
    public SlideShow selSlideShowListId(@Param("sid") Integer sid);

    //根据 ID 修改轮播图信息
    public Integer updSlideShowIntId(SlideShow slideShow);

    //添加轮播图信息
    public Integer insSlideShowInt(SlideShow slideShow);

    //根据 ID 删除轮播图信息
    public Integer delSlideShowInt(@Param("sid") Integer sid);

    // 查询轮播图的数据条数 （前台轮播图的显示条数不得超过7条）
    public Integer selSlideShowCount();
}
