package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Attention;
import com.soyoung.photo.mapper.AttentionMapper;
import com.soyoung.photo.service.AttentionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 关注表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-13
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {
    @Autowired
    private AttentionMapper attentionMapper;

    @Override
    public Integer addAttention(Attention attention) {
        return attentionMapper.insert(attention);
    }

    @Override
    public Integer delAttention(Integer uid, Integer byuid) {
        return attentionMapper.delete(
                new EntityWrapper<Attention>()
                .eq("uid",uid)
                .eq("byuid",byuid)
        );
    }

    @Override
    public FreePage selFansPageList(Integer id, Integer currPage) {
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.HUNTUSERPAGESIZE);
        freePage.setList(attentionMapper.getfansDetailsList(id,currPage,freePage.getPageSize()));
        return freePage;
    }

    @Override
    public FreePage selFocusPageList(Integer id, Integer currPage) {
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.HUNTUSERPAGESIZE);
        freePage.setList(attentionMapper.getfocusDetailsList(id,currPage,freePage.getPageSize()));
        return freePage;
    }

    @Override
    public List<Attention> getAllIsUidList(Integer uid) {
        return attentionMapper.selectList(
                new EntityWrapper<Attention>()
                .eq("uid",uid)
        );
    }
}
