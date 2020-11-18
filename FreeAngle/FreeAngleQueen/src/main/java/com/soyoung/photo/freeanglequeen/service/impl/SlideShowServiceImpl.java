package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.SlideShowMapper;
import com.soyoung.photo.freeanglequeen.entity.SlideShow;
import com.soyoung.photo.freeanglequeen.service.SlideShowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SlideShowServiceImpl implements SlideShowService {

    @Resource
    public SlideShowMapper slideShowMapper;

    @Override
    public List<SlideShow> selSlideShowListAll() {
        List<SlideShow> list = slideShowMapper.selSlideShowListAll();
        return list;
    }

    @Override
    public SlideShow selSlideShowListId(Integer sid) {
        SlideShow slideShow = slideShowMapper.selSlideShowListId(sid);
        return slideShow;
    }

    @Override
    public Integer updSlideShowIntId(SlideShow slideShow) {
        Integer i = slideShowMapper.updSlideShowIntId(slideShow);
        return i;
    }

    @Override
    public Integer insSlideShowInt(SlideShow slideShow) {
        Integer i = slideShowMapper.insSlideShowInt(slideShow);
        return i;
    }

    @Override
    public Integer delSlideShowInt(Integer sid) {
        Integer i = slideShowMapper.delSlideShowInt(sid);
        return i;
    }

    @Override
    public Integer selSlideShowCount() {
        Integer i = slideShowMapper.selSlideShowCount();
        return i;
    }
}
