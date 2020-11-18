package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Likes;
import com.soyoung.photo.mapper.LikesMapper;
import com.soyoung.photo.service.LikesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞记录表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-27
 */
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {
    @Autowired
    private LikesMapper likesMapper;

    @Override
    public Integer addProLike(Likes likes) {
        return likesMapper.insert(likes);
    }

    @Override
    public Integer delProLike(Integer pid, Integer uid) {
        return likesMapper.delete(
                new EntityWrapper<Likes>()
                .eq("uid",uid)
                .eq("pid",pid)
        );
    }
}
