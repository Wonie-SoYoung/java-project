package com.soyoung.photo.service;

import com.soyoung.photo.entity.Hotlabels;
import com.baomidou.mybatisplus.service.IService;
import com.soyoung.photo.util.FreePage;

import java.util.List;

/**
 * <p>
 * 热门标签表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public interface HotlabelsService extends IService<Hotlabels> {

    //查询热门标签
    public List<Hotlabels> getHotHomeList(Integer pageSize);

    //查询根节点
    public List<Hotlabels> getDadByLabels();

    //根据父节点查询子节点
    public FreePage getSonByLables(Integer hid,Integer currPage);

    //查询上传作品给出的建议标签
    public List<Hotlabels> getSuggestLabelsList();
}
