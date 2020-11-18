package com.soyoung.photo.service;

import com.soyoung.photo.entity.Production;
import com.baomidou.mybatisplus.service.IService;
import com.soyoung.photo.util.FreePage;

import java.util.List;

/**
 * <p>
 * 作品表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public interface ProductionService extends IService<Production> {

    //分页查询作品详细数据
    public FreePage gethomeProListPage(String label, Integer pageSize);

    //分页条件查询作品详细信息
    public FreePage getTermProListPage(String label,String pName,Integer proType,Integer currPage,Integer pageSize);

    //查询作品详情
    public Production getisByPro(Integer pid);

    //根据用户查询对应作品
    public FreePage getProByUserPage(Integer uid,Integer currPage,Integer pageSieze);

    //增加作品
    public Integer insertPro(Production production);

    //根据标签查询相关作品
    public List<Production> getIdIsLikeByPros(String[] labels,Integer pid);

    //搜索作品
    public FreePage getSeekPro(String value,Integer type,Integer currPage,Integer pageSize);

    //删除作品
    public Boolean delProduction(Integer pid);

    //修改作品
    public Boolean updateProduction(Production production);

    //查询关注的用户的作品
    public FreePage getFocusProductionPage(List<Integer> uidList,Integer currPage);

    //查询用户收藏的作品
    public FreePage getCollectProductionPage(Integer uid,Integer currPage);

    //增加观看次数
    public Integer updateviewNum(Integer pid);
}
