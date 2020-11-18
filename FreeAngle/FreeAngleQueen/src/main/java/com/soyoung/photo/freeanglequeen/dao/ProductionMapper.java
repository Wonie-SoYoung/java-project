package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Production;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ProductionMapper {

    //根据 ID 查询作品信息
    public Production selProductionId(@Param("id") Integer id);

    //根据 ID 删除作品信息
    public Integer delProductionId(@Param("id") Integer id);

    /*
    * 陈海嘉
    * */
    //查询数据库总页数
    public int getProductionSum(Map<String,Object> parmMaps);

    //查询数据库总页数
    public int getProductionSumHome(Map<String,Object> parmMaps);

    //查询商品信息列表
    public List<Map<String,Object>> getProductionList(Map<String,Object> production);

    //按ID查询
    public Map<String,Object> getProductionById(Integer id);

    //首页查询，按每天的时间，获取当天注册的用户
    public  List<Map<String ,Object>> everyDayProductionList(Map<String ,Object> parmmap);

    //用户统计，统计每年的增加的用户数量
    public List<Map<String,Object>> getProductionByCreatetime();

    //授勋操作，在多对多表中添加一条记录
    public int shouXun(Map<String,Object> map);

    //按照作品类型，进行查询
    public List<Map<String,Object>> getProductionByType(Map<String,Object> map);
}
