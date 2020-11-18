package com.soyoung.photo.freeanglequeen.service;

import com.soyoung.photo.freeanglequeen.entity.User;
import com.soyoung.photo.freeanglequeen.util.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    /*
    * 范宏伦
    * */
    //根据 ID 查询用户信息
    public User selUserId(Integer id);
    //查询封号天数不为零的用户
    public List<User> selUserSealList();
    //修改封号天数
    public int updUserId(Integer seal,Integer id);

    /*
    * 陈海嘉
    * */
    //查询用户的数量
    public int getUserSum();

    //按ID查询
    public Map<String ,Object> getUserById(Integer id);

    //按ID查询,程序外键的表示，不查询对应的字段
    public Map<String ,Object> getUserId(Integer id);

    //首页查询，按每天的时间，获取当天注册的用户
    public Page everyDayUser(Map<String,Object> map);

    //分页插件
    public List<Map<String ,Object>> everyDayUserPageHelper(Map<String ,Object> parmmap);

    //用户统计，统计每年的增加的用户数量
    public List<Map<String,Object>> getUserByCreatetime();

    //查询全部，插入消息的时候使用
    public List<Map<String,Object>> getUserList();

    //推荐用户
    public int tuiJian(Map<String ,Object> map);

    //赋予达人
    public int daRen(Map<String ,Object> map);

    //封号处理
    public int fengHao(Integer seal,Integer id);
}
