package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 消息表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
public interface MessagesMapper extends BaseMapper<Messages> {

    //查询当前用户的点赞消息
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "keyid",property = "likes",javaType = Likes.class,
                    one = @One(select = "com.soyoung.photo.mapper.LikesMapper.selectById")),
            @Result(column = "prokeyid",property = "production",javaType = Production.class,
                    one = @One(select = "com.soyoung.photo.mapper.ProductionMapper.selectById"))
    })
    @Select("select * from messages where byuid=#{uid} and mType=14 order by createTime desc limit #{currPage},#{pageSize}")
    public List<Messages> getLikeMessagesList(@Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询当前用户的评论消息
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "keyid",property = "comment",javaType = Comment.class,
                    one = @One(select = "com.soyoung.photo.mapper.CommentMapper.selectById")),
            @Result(column = "prokeyid",property = "production",javaType = Production.class,
                    one = @One(select = "com.soyoung.photo.mapper.ProductionMapper.selectById"))
    })
    @Select("select * from messages where byuid=#{uid} and mType=15 order by createTime desc limit #{currPage},#{pageSize}")
    public List<Messages> getCommentMessagesList(@Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询当前用户的关注消息
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "keyid",property = "attention",javaType = Attention.class,
                    one = @One(select = "com.soyoung.photo.mapper.AttentionMapper.selectById"))
    })
    @Select("select * from messages where byuid=#{uid} and mType=16 order by createTime desc limit #{currPage},#{pageSize}")
    public List<Messages> getAttentionMessagesList(@Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询当前用户的收藏消息
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "keyid",property = "collect",javaType = Collect.class,
                    one = @One(select = "com.soyoung.photo.mapper.CollectMapper.selectById")),
            @Result(column = "prokeyid",property = "production",javaType = Production.class,
                    one = @One(select = "com.soyoung.photo.mapper.ProductionMapper.selectById"))
    })
    @Select("select * from messages where byuid=#{uid} and mType=17 order by createTime desc limit #{currPage},#{pageSize}")
    public List<Messages> getCollectMessagesList(@Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询当前用户的通知消息
    @Results({
            @Result(column = "uid",property = "queenuser",javaType = Queenuser.class,
                    one = @One(select = "com.soyoung.photo.mapper.QueenuserMapper.selectById")),
            @Result(column = "keyid",property = "notice",javaType = Notice.class,
                    one = @One(select = "com.soyoung.photo.mapper.NoticeMapper.selectById"))
    })
    @Select("select * from messages where byuid=#{uid} and mType=18 order by createTime desc limit #{currPage},#{pageSize}")
    public List<Messages> getNoticeMessagesList(@Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询当前用户的私信消息
    @Results({
            @Result(column = "asuid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "keyid",property = "chat",javaType = Chat.class,
                    one = @One(select = "com.soyoung.photo.mapper.ChatMapper.selectById"))
    })
    @Select("select m.*,m.uid as asuid from (select * from messages ORDER BY createTime desc) m where m.byuid=#{uid} and m.mType=19 group by m.uid limit #{currPage},#{pageSize}")
    public List<Messages> getChatMessagesList(@Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //修改已读消息
    @Update("update messages set isNot=1 where mType=#{type} and byuid=#{uid}")
    public Integer updateMessageYes(@Param("type") Integer type,@Param("uid") Integer uid);
}
