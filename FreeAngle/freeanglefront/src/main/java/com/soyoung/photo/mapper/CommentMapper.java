package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Comment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soyoung.photo.entity.Production;
import com.soyoung.photo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-29
 */
public interface CommentMapper extends BaseMapper<Comment> {

    //根据作品查询评论数量
    @Select("select count(1) from comment where pid=#{pid}")
    public Integer getProIsCommentNum(Integer pid);

    //查询作品下的评论
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById")),
            @Result(column = "replyid",property = "replcomment",javaType = Comment.class,
                    one = @One(select = "com.soyoung.photo.mapper.CommentMapper.getIsreplyIdByComment"))
    })
    @Select("select * from comment where pid=#{pid} order by createTime desc limit #{currPage},#{pageSize}")
    public List<Comment> getProIsComment(@Param("pid") Integer pid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //根据回复外键查询评论
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.selectById"))
    })
    @Select("select * from comment where cid=#{cid}")
    public Comment getIsreplyIdByComment(Integer cid);
}
