package com.soyoung.photo.service;

import com.soyoung.photo.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.soyoung.photo.util.FreePage;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public interface UserService extends IService<User> {

    //根据id查询用户
    public User getUserById(Integer id);

    //分页查询用户列表
    public FreePage getUserList(Integer type, Integer currPage);

    //根据id查询用户详情
    public User getUserInById(Integer uid);

    //搜索用户列表
    public FreePage getSeekUser(String value,Integer currPage,Integer pageSize);

    //添加用户
    public Integer addUser(User user);

    //修改背景
    public Integer updateUserIshiapkURL(User user);

    //根据作品id查询用户
    public User getProIsIdByUser(Integer pid);

    //修改用户
    public Boolean updateUser(User user);

    //登录次数
    public Integer updateLogins(Integer uid);
}
