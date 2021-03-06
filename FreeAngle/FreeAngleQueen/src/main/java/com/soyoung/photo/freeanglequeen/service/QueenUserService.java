package com.soyoung.photo.freeanglequeen.service;


import com.soyoung.photo.freeanglequeen.entity.QueenUser;

import java.util.List;
import java.util.Map;

public interface QueenUserService {

    /*
    * 范宏伦
    * */
    //修改头像
    public int updQueenUserIdHeadURL(String headURL,Integer id);

    /*
    * 陈海嘉
    * */
    //后台登录查询的方法
    public QueenUser getUserByPwd(QueenUser queenUser);

    //根据人脸照片返回用户的id，查出对象
    public QueenUser getFaceUrlByIdQueenUser(Integer id);

    //查询后台用户分页插件
    public List<Map<String ,Object>> getQueenUserList(Map<String ,Object> parmmap);

    //修改密码
    public int updPwd(Integer id,String pwd);

    //添加用户密码
    public int addQueenUser(QueenUser queenUser);

    //注销此用户
    public int delQueenUser(Integer id);
}
