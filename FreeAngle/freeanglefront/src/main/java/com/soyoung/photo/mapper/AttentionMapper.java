package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Attention;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soyoung.photo.entity.State;
import com.soyoung.photo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 关注表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-13
 */
public interface AttentionMapper extends BaseMapper<Attention> {

    //根据摄影师id查询粉丝列表
    @Select("select * from attention where byuid=#{id}")
    public List<Attention> getfansList(Integer id);

    //根据摄影师id查询关注列表
    @Select("select * from attention where uid=#{id}")
    public List<Attention> getfocusList(Integer id);

    //分页查询粉丝详细列表
    @Results({
        @Result(column = "u_id", property = "productionList", javaType = List.class,
                many = @Many(select = "com.soyoung.photo.mapper.ProductionMapper.getuidInByPro")),
        @Result(column = "oneid",property = "fansList",javaType = List.class,
                many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfansList")),
        @Result(column = "twoid",property = "focusList",javaType = List.class,
                many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfocusList")),
        @Result(column = "sid",property = "state",javaType = State.class,
                many = @Many(select = "com.soyoung.photo.mapper.StateMapper.selectById"))
    })
    @Select("select u.*,u.id as u_id,u.id as oneid,u.id as twoid,u.id as sid from user u,attention a where a.uid=u.id and a.byuid=#{id} order by a.createTime desc limit #{currPage},#{pageSize}")
    public List<User> getfansDetailsList(@Param("id") Integer id,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //分页查询关注详细列表
    @Results({
            @Result(column = "u_id", property = "productionList", javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.ProductionMapper.getuidInByPro")),
            @Result(column = "oneid",property = "fansList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfansList")),
            @Result(column = "twoid",property = "focusList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfocusList")),
            @Result(column = "sid",property = "state",javaType = State.class,
                    many = @Many(select = "com.soyoung.photo.mapper.StateMapper.selectById"))
    })
    @Select("select u.*,u.id as u_id,u.id as oneid,u.id as twoid,u.id as sid from user u,attention a where a.byuid=u.id and a.uid=#{id} order by a.createTime desc limit #{currPage},#{pageSize}")
    public List<User> getfocusDetailsList(@Param("id") Integer id,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);
}
