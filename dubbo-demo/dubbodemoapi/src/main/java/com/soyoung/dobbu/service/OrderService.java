package com.soyoung.dobbu.service;

import com.soyoung.dobbu.bean.UserAddress;

import java.util.List;

/**
 * 消费者
 */
public interface OrderService {

    /**
     * 初始化订单
     * @param userId
     */
    public List<UserAddress> initOrder(String userId);
}
