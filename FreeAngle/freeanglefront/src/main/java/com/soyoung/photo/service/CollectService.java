package com.soyoung.photo.service;

import com.soyoung.photo.entity.Collect;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-28
 */
public interface CollectService extends IService<Collect> {

    //添加收藏
    public Integer addUserIsCollect(Collect collect);

    //删除收藏
    public Integer delUserIsCollect(Integer uid,Integer pid);
}
