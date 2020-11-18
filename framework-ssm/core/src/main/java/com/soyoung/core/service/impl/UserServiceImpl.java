package com.soyoung.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soyoung.core.entity.User;
import com.soyoung.core.dao.UserDao;
import com.soyoung.core.service.UserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
*  业务逻辑接口的实现类
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public IPage<User> listUser(Integer page, Integer size) {
        return userDao.listUser(new Page<>(page,size));
    }
}
