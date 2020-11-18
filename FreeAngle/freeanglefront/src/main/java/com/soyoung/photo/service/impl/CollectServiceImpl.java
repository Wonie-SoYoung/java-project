package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Collect;
import com.soyoung.photo.mapper.CollectMapper;
import com.soyoung.photo.service.CollectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-28
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Integer addUserIsCollect(Collect collect) {
        return collectMapper.insert(collect);
    }

    @Override
    public Integer delUserIsCollect(Integer uid, Integer pid) {
        return collectMapper.delete(
                new EntityWrapper<Collect>()
                .eq("uid",uid)
                .eq("pid",pid)
        );
    }
}
