package com.soyoung.project.service.impl;

import com.soyoung.project.dao.GoodsDao;
import com.soyoung.project.entity.GoodsDO;
import com.soyoung.project.service.GoodsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*  业务逻辑接口的实现类
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsDO> implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<GoodsDO> listGoodsCondition(Map<String,String> param) {
        return goodsDao.listGoodsCondition(param);
    }

    @Override
    public Integer insertGoods(GoodsDO goodsDO) {
        return goodsDao.insertGoods(goodsDO);
    }

    @Override
    public Integer updateGoods(GoodsDO goodsDO) {
        return goodsDao.updateGoods(goodsDO);
    }

    @Override
    public Integer deleteGoods(Integer id) {
        return goodsDao.deleteGoods(id);
    }

    @Override
    public GoodsDO getGoods(Integer id) {
        GoodsDO goodsDO = goodsDao.selectById(id);
        return goodsDO;
    }
}
