package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.soyoung.photo.entity.Hotlabels;
import com.soyoung.photo.mapper.HotlabelsMapper;
import com.soyoung.photo.service.HotlabelsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 热门标签表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
@Service
public class HotlabelsServiceImpl extends ServiceImpl<HotlabelsMapper, Hotlabels> implements HotlabelsService {
    @Autowired
    private HotlabelsMapper hotlabelsMapper;

    @Override
    public List<Hotlabels> getHotHomeList(Integer pageSize) {
        return hotlabelsMapper.selectPage(new Page<Hotlabels>(0,pageSize),
            new EntityWrapper<Hotlabels>()
                .eq("ishot","1")
        );
    }

    @Override
    public List<Hotlabels> getDadByLabels() {
        return hotlabelsMapper.selectList(
                new EntityWrapper<Hotlabels>()
                .isNull("parentId")
        );
    }

    @Override
    public FreePage getSonByLables(Integer hid,Integer currPage) {
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.HOTLABELSIZE);
        freePage.setTotalCount(hotlabelsMapper.selectCount(new EntityWrapper<Hotlabels>().eq("parentId",hid)));
        freePage.setList(hotlabelsMapper.getProByLabels(hid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        return freePage;
    }

    @Override
    public List<Hotlabels> getSuggestLabelsList() {
        return hotlabelsMapper.getSuggestLabelsList();
    }
}
