package com.soyoung.core.service.impl;

import com.soyoung.core.entity.Goods;
import com.soyoung.core.dao.GoodsDao;
import com.soyoung.core.service.GoodsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
*  业务逻辑接口的实现类
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
}
