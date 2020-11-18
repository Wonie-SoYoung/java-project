package com.soyoung.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.soyoung.core.entity.User;

/**
*  业务逻辑的接口类
*/
public interface UserService {

    IPage<User> listUser(Integer page,Integer size);
}
