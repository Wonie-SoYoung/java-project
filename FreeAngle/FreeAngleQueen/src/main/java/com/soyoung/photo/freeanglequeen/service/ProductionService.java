package com.soyoung.photo.freeanglequeen.service;

import com.soyoung.photo.freeanglequeen.entity.Production;
import com.soyoung.photo.freeanglequeen.util.Page;

import java.util.List;
import java.util.Map;

public interface ProductionService {

    /*
    * 范宏伦
    * */
    //根据 ID 查询作品信息
    public Production selProductionId(Integer id);

    //根据 ID 删除作品信息
    public Integer delProductionId(Integer id);

    /*
    * 陈海嘉
    * */
    //查询商品信息列表
    public Page getProductionList(Map<String, Object> production);

    //按ID查询
    public Map<String,Object> getProductionById(Integer id);

    //首页查询，按每天的时间，获取当天注册的用户
    public Page everyDayProductionList(Map<String ,Object> parmmap);

    //用户统计，统计每年的增加的用户数量
    public List<Map<String,Object>> getProductionByCreatetime();

    //授勋操作，在多对多表中添加一条记录
    public int shouXun(Map<String,Object> map);

    //按照作品类型，进行查询
    public List<Map<String,Object>> getProductionByType(Map<String,Object> map);
}
