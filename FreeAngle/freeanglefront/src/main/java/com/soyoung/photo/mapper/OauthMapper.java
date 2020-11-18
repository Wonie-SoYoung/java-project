package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Oauth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 登录表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public interface OauthMapper extends BaseMapper<Oauth> {

    //修改密码
    @Update("update oauth set password=#{pwd} where id=#{id}")
    public Integer updatepwd(@Param("id") Integer id,@Param("pwd") String pwd);

    //查询当前数据是否包含手机
    @Select("select * from oauth where oatype=4 and uuid=#{phone}")
    public Oauth getIsPhoneOnOauth(String phone);
}
