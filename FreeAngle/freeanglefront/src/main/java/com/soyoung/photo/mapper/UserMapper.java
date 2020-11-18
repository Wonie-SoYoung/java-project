package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.State;
import com.soyoung.photo.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public interface UserMapper extends BaseMapper<User> {

    //查询摄影师列表
    @Results({
            @Result(column = "u_id", property = "productionList", javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.ProductionMapper.getuidInByPro")),
            @Result(column = "f_id",property = "fansList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfansList"))
    })
    @Select("<script>" +
            "select *,id as u_id,id as f_id from user where 1=1" +
            "<if test='type==1'> and ifrecommend=1</if>" +
            "<if test='type==2'> and ifexpert=1</if>" +
            "<if test='type==3'> order by createTime desc</if>" +
            " limit #{currPage},#{pageSize}" +
            "</script>")
    public List<User> getUserList(@Param("type") Integer type,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //根据id查询摄影师
    @Results({
            @Result(column = "stateid", property = "state", javaType = State.class,
                    one = @One(select = "com.soyoung.photo.mapper.StateMapper.selectById")),
            @Result(column = "oneid",property = "fansList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfansList")),
            @Result(column = "twoid",property = "focusList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.AttentionMapper.getfocusList")),
    })
    @Select("select *,id as u_id,id as oneid,id as twoid from user where id=#{uid}")
    public User getUserInById(Integer uid);

    //搜索创作人
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
    @Select("<script>" +
            "select *,id as u_id,id as oneid,id as twoid,id as sid from user" +
            " where userName like concat('%',#{value},'%')" +
            "<if test='finevalue!=null'> or userName like concat('%',#{finevalue},'%')</if>" +
            " limit #{currPage},#{pageSize}" +
            "</script>")
    public List<User> getHuntPro(@Param("value") String value,@Param("finevalue") String finevalue,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //根据作品id查询对应用户
    @Select("select u.* from user u,production p where p.uid=u.id and p.id=#{pid}")
    public User getProIsIdByUser(Integer pid);

    //修改用户信息
    @Update("update user set userName=#{userName},sex=#{sex},intro=#{intro},headURL=#{headURL},stateid=#{stateid},updateTime=#{updateTime} where id=#{id}")
    public Integer updateUser(User user);

    //修改用户登录次数
    @Update("update user set logins=logins+1 where id=#{uid}")
    public Integer updateLogins(Integer uid);
}
