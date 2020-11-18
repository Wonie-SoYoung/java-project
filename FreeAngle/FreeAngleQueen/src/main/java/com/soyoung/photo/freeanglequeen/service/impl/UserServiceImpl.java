package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.UserMapper;
import com.soyoung.photo.freeanglequeen.entity.User;
import com.soyoung.photo.freeanglequeen.service.StateService;
import com.soyoung.photo.freeanglequeen.service.UserService;
import com.soyoung.photo.freeanglequeen.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;
    @Resource
    public StateService stateService;

    /*
    * 范宏伦
    * */
    @Override
    public User selUserId(Integer id) {
        User user = userMapper.selUserId(id);
        user.setState(stateService.selStateId(user.getStateid()));
        return user;
    }

    @Override
    public List<User> selUserSealList() {
        return userMapper.selUserSealList();
    }

    @Override
    public int updUserId(Integer seal, Integer id) {
        return userMapper.updUserId(seal,id);
    }


    /*
    * 陈海嘉
    * */
    @Override
    public int getUserSum() {
        return userMapper.getUserSum();
    }

    @Override
    public Map<String, Object> getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Map<String, Object> getUserId(Integer id) {
        return userMapper.getUserId(id);
    }

    @Override
    public Page everyDayUser(Map<String, Object> parmMap) {
        Page p=new Page();
        p.setCurrPage((int) parmMap.get("currentPage"));
        p.setTotalCount(userMapper.getUserPageSum(parmMap));
        parmMap.put("pageSize",p.getPageSize());
        parmMap.put("currentPage",((int)parmMap.get("currentPage")-1)*p.getPageSize());
        p.setLists(userMapper.everyDayUser(parmMap));
        return p;
    }

    @Override
    public List<Map<String, Object>> everyDayUserPageHelper(Map<String, Object> parmmap) {
        return userMapper.everyDayUserPageHelper(parmmap);
    }

    @Override
    public List<Map<String, Object>> getUserByCreatetime() {
        return userMapper.getUserByCreatetime();
    }

    @Override
    public List<Map<String, Object>> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public int tuiJian(Map<String, Object> map) {
        return userMapper.tuiJian(map);
    }

    @Override
    public int daRen(Map<String, Object> map) {
        return userMapper.daRen(map);
    }

    @Override
    public int fengHao(Integer seal, Integer id) {
        return userMapper.fengHao(seal,id);
    }
}
