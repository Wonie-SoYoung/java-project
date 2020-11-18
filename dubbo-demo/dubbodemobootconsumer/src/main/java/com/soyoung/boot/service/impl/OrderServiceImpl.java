package com.soyoung.boot.service.impl;

import com.soyoung.dobbu.bean.UserAddress;
import com.soyoung.dobbu.service.OrderService;
import com.soyoung.dobbu.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 1）、导入dubbo依赖（2.6.2）、操作zookeeper的客户端（curator）
 * 2）、配置服务提供者
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 */
@Service
public class OrderServiceImpl implements OrderService {

    //@Autowired
    @Reference
    private UserService userService;

    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id" + userId);
        List<UserAddress> userAddresses = userService.getUserAddressList(userId);
        return userAddresses;
    }
}
