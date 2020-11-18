package com.soyoung.photo.service;

import com.soyoung.photo.entity.Attention;
import com.baomidou.mybatisplus.service.IService;
import com.soyoung.photo.util.FreePage;

import java.util.List;

/**
 * <p>
 * 关注表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-13
 */
public interface AttentionService extends IService<Attention> {

    //添加关注
    public Integer addAttention(Attention attention);

    //删除关注
    public Integer delAttention(Integer uid,Integer byuid);

    //分页查询粉丝
    public FreePage selFansPageList(Integer id,Integer currPage);

    //分页查询关注
    public FreePage selFocusPageList(Integer id,Integer currPage);

    //查询全部关注
    public List<Attention> getAllIsUidList(Integer uid);
}
