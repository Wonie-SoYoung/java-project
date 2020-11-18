package com.soyoung.photo.freeanglequeen.service.impl;


import com.soyoung.photo.freeanglequeen.dao.QueenUserMapper;
import com.soyoung.photo.freeanglequeen.entity.QueenUser;
import com.soyoung.photo.freeanglequeen.service.QueenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueenUserServiceImpl implements QueenUserService {

    @Autowired
    private QueenUserMapper queenUserMapper;

    @Override
    public int updQueenUserIdHeadURL(String headURL, Integer id) {
        try {
            QueenUser queenUser = new QueenUser();
            queenUser.setHeadURL(headURL);
            queenUser.setId(id);
            return queenUserMapper.updQueenUserIdHeadURL(queenUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /*
    * 陈海嘉
    * */
    @Override
    public QueenUser getUserByPwd(QueenUser queenUser) {
        return queenUserMapper.getUserByPwd(queenUser);
    }

    @Override
    public QueenUser getFaceUrlByIdQueenUser(Integer id) {
        return queenUserMapper.getFaceUrlByIdQueenUser(id);
    }

    @Override
    public List<Map<String, Object>> getQueenUserList(Map<String, Object> parmmap) {
        return queenUserMapper.getQueenUserList(parmmap);
    }

    @Override
    public int updPwd(Integer id, String pwd) {
        return queenUserMapper.updPwd(id,pwd);
    }

    @Override
    public int addQueenUser(QueenUser queenUser) {
        return queenUserMapper.addQueenUser(queenUser);
    }

    @Override
    public int delQueenUser(Integer id) {
        return queenUserMapper.delQueenUser(id);
    }
}
