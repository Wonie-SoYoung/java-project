package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /*
    * 范宏伦
    * */

    @Select("select " +
            "id,userName,sex,intro,headURL,backgroundURL,prestige,stateid,createTime,updateTime,logins,ifrecommend,ifexpert" +
            " from user " +
            " where id=#{uid}")
    public User selUserListId(@Param("uid") Integer uid);

    //根据 ID 查询用户信息
    public User selUserId(@Param("id") Integer id);

    //查询封号天数不为零的用户
    public List<User> selUserSealList();
    //修改封号天数
    public int updUserId(@Param("seal") Integer seal,@Param("id") Integer id);


    /*
    * 陈海嘉
    * */
    //查询用户的总条数
    public int  getUserSum();

    //查询用户的总条数
    public int  getUserPageSum(Map<String,Object> parmMap);
    //按ID查询
    public Map<String ,Object> getUserById(Integer id);

    //按ID查询,程序外键的表示，不查询对应的字段
    public Map<String ,Object> getUserId(Integer id);

    //首页查询，按每天的时间，获取当天注册的用户
    public List<Map<String ,Object>> everyDayUser(Map<String ,Object> parmmap);

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
    public int fengHao(@Param("seal") Integer seal, @Param("id") Integer id);

}
