package com.soyoung.dobbu.service;

import com.soyoung.dobbu.bean.UserAddress;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 根据用户id返回收货地址
     * @param userId
     * @return
     */
    public List<UserAddress> getUserAddressList(String userId);
}
