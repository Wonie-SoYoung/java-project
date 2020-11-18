package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Oauth;
import com.soyoung.photo.mapper.OauthMapper;
import com.soyoung.photo.service.OauthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 登录表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
@Service
public class OauthServiceImpl extends ServiceImpl<OauthMapper, Oauth> implements OauthService {
    @Autowired
    private OauthMapper oauthMapper;

    @Override
    public Oauth getPhoneUserId(String phone) {
        Oauth oauth=new Oauth();
        oauth.setUuid(phone);
        return oauthMapper.selectOne(oauth);
    }

    @Override
    public Oauth getPhoneUserBypwd(String phone, String password) {
        Oauth oauth=new Oauth();
        oauth.setUuid(phone);
        oauth.setPassword(password);
        return oauthMapper.selectOne(oauth);
    }

    @Override
    public Integer addOauth(Oauth oauth) {
        return oauthMapper.insert(oauth);
    }

    @Override
    public Oauth getQQOauth(String openId) {
        Oauth oauth=null;
        List<Oauth> oauthList=oauthMapper.selectList(new EntityWrapper<Oauth>().eq("uuid",openId));
        if(oauthList!=null && oauthList.size()>0){
            oauth=oauthList.get(0);
        }
        return oauth;
    }

    @Override
    public Integer updatepwd(Integer id, String pwd) {
        return oauthMapper.updatepwd(id,pwd);
    }

    @Override
    public Boolean getIsPhone(String phone) {
        Boolean isNot=false;
        if(oauthMapper.getIsPhoneOnOauth(phone)!=null){
            isNot=true;
        }
        return isNot;
    }
}
