package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.soyoung.photo.entity.Slideshow;
import com.soyoung.photo.mapper.SlideshowMapper;
import com.soyoung.photo.service.SlideshowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 轮播图表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-12
 */
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowMapper, Slideshow> implements SlideshowService {
    @Autowired
    private SlideshowMapper slideshowMapper;

    @Override
    public List<Slideshow> getSlideshowList() {
        return slideshowMapper.selectPage(new Page<>(0,7),null);
    }
}
