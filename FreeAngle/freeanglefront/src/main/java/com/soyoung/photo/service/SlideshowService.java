package com.soyoung.photo.service;

import com.soyoung.photo.entity.Slideshow;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 轮播图表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-12
 */
public interface SlideshowService extends IService<Slideshow> {

    //查询前台主页所需的轮播广告
    public List<Slideshow> getSlideshowList();
}
