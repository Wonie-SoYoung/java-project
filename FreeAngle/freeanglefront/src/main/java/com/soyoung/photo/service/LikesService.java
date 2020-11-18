package com.soyoung.photo.service;

import com.soyoung.photo.entity.Likes;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 点赞记录表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-27
 */
public interface LikesService extends IService<Likes> {

    //添加点赞
    public Integer addProLike(Likes likes);

    //删除点赞
    public Integer delProLike(Integer pid,Integer uid);
}
