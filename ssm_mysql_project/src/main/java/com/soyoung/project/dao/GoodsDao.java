package com.soyoung.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soyoung.project.entity.GoodsDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
*  Dao接口
*/
@Repository
public interface GoodsDao extends BaseMapper<GoodsDO> {

    /**
     * 条件查询物品
     *
     * @Param: [param]
     * @Return: java.util.List<com.soyoung.project.entity.GoodsDO>
     * @Author: 梅晓寒
     * @Date: 2020/11/12 9:14
     */
    List<GoodsDO> listGoodsCondition(@Param("param") Map<String,String> param);

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
    Integer deleteGoods(@Param("id") Integer id);
}
