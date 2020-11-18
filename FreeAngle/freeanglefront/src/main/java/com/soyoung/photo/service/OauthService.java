package com.soyoung.photo.service;

import com.soyoung.photo.entity.Oauth;
import com.baomidou.mybatisplus.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * <p>
 * 登录表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public interface OauthService extends IService<Oauth> {

    //查询手机号登录并返回用户Id
    public Oauth getPhoneUserId(String phone);

    //根据手机号和密码进行登录
    public Oauth getPhoneUserBypwd(String phone,String password);

    //添加登录方式
    public Integer addOauth(Oauth oauth);

    //qq登录查询
    public Oauth getQQOauth(String openId);

    //修改密码
    public Integer updatepwd(Integer id,String pwd);

    //查询是否包含手机号
    public Boolean getIsPhone(String phone);
}
