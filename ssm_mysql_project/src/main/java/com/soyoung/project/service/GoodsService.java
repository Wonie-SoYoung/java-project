package com.soyoung.project.service;

import com.soyoung.project.entity.GoodsDO;

import java.util.List;
import java.util.Map;

/**
*  业务逻辑的接口类
*/
public interface GoodsService {

    /**
     * 条件查询物品
     *
     * @Param: [param]
     * @Return: java.util.List<com.soyoung.project.entity.GoodsDO>
     * @Author: 梅晓寒
     * @Date: 2020/11/12 9:14
     */
    List<GoodsDO> listGoodsCondition(Map<String,String> param);

    /**
     * 增加物品
     *
     * @Param: [goodsDO]
     * @Return: java.lang.Integer
     * @Author: 梅晓寒
     * @Date: 2020/11/12 9:15
     */
    Integer insertGoods(GoodsDO goodsDO);

    /**
     * 修改物品
     *
     * @Param: [goodsDO]
     * @Return: java.lang.Integer
     * @Author: 梅晓寒
     * @Date: 2020/11/12 9:15
     */
    Integer updateGoods(GoodsDO goodsDO);

    /**
     * 删除物品
     *
     * @Param: [id]
     * @Return: java.lang.Integer
     * @Author: 梅晓寒
     * @Date: 2020/11/12 9:16
     */
    Integer deleteGoods(Integer id);

    /**
     * 查询
     *
     * @Param: [id]
     * @Return: com.soyoung.project.entity.GoodsDO
     * @Author: 梅晓寒
     * @Date: 2020/11/12 11:23
     */
    GoodsDO getGoods(Integer id);
}
