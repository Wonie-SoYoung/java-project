package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.User;
import com.soyoung.photo.mapper.UserMapper;
import com.soyoung.photo.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import com.soyoung.photo.util.Translate;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public FreePage getUserList(Integer type, Integer currPage) {
        EntityWrapper entityWrapper=new EntityWrapper();
        if(type==1){
            entityWrapper.eq("ifrecommend","1");
        }else if(type==2){
            entityWrapper.eq("ifexpert","1");
        }else if(type==3){
            entityWrapper.orderDesc(Arrays.asList(new String[]{"createTime"}));
        }
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.CAMERAMANLISTSIZE);
        freePage.setTotalCount(userMapper.selectCount(entityWrapper));
        freePage.setList(userMapper.getUserList(type,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        return freePage;
    }

    @Override
    public User getUserInById(Integer uid) {
        return userMapper.getUserInById(uid);
    }

    @Override
    public FreePage getSeekUser(String value, Integer currPage, Integer pageSize) {
        String finevalue= Translate.gainresult(value,Dict.ENGLISHLANGUGE);
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(pageSize);
        try{
            freePage.setList(userMapper.getHuntPro(value,finevalue,currPage,pageSize));
        }catch (Exception e){
            e.printStackTrace();
        }
        return freePage;
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer updateUserIshiapkURL(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public User getProIsIdByUser(Integer pid) {
        return userMapper.getProIsIdByUser(pid);
    }

    @Override
    public Boolean updateUser(User user) {
        Boolean isNot=false;
        if(userMapper.updateUser(user)>0){
            isNot=true;
        }
        return isNot;
    }

    @Override
    @Async
    public Integer updateLogins(Integer uid) {
        return userMapper.updateLogins(uid);
    }
}
