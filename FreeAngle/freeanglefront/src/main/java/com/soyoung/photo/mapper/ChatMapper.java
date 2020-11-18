package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Chat;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soyoung.photo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 私聊表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-05
 */
public interface ChatMapper extends BaseMapper<Chat> {

    //查询两位用户的聊天记录
    @Results({
            @Result(column = "cozeid",property = "cozeUser",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "talkid",property = "talkUser",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById"))
    })
    @Select("select * from chat where cozeid=#{auid} and talkid=#{buid} or cozeid=#{buid} and talkid=#{auid} order by createTime")
    public List<Chat> getUserisUserorChat(@Param("auid") Integer auid,@Param("buid") Integer buid);
}
