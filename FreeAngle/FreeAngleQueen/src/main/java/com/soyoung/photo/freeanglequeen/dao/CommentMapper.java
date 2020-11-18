package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Comment;
import com.soyoung.photo.freeanglequeen.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentMapper {

    //根据评论表 pid 查询出评论表中的评论信息
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
            one = @One(select = "com.soyoung.photo.freeanglequeen.dao.UserMapper.selUserListId"))
    })
    @Select("select cid,uid,pid,content,replyid,createTime from comment " +
            "where pid = #{pid}")
    public List<Comment> selCommentListPid(@Param("pid") Integer pid);

    //根据举报表外键 reTypeCodeId 查询评论表中的评论信息
    @Results({
            @Result(column = "uid",property = "user",javaType = User.class,
            one = @One(select = "com.soyoung.photo.freeanglequeen.dao.UserMapper.selUserListId"))
    })
    @Select("select cid,uid,pid,content,replyid,createTime from comment " +
            "where cid = #{reTypeCodeId}")
    public Comment selCommentReTypeCodeId(@Param("reTypeCodeId") Integer reTypeCodeId);

    //根据评论表 pid 查询出评论表中的评论信息
    @Select("select cid,uid,pid,content,replyid,createTime from comment " +
            "where pid = #{pid}")
    public List<Comment> selCommentsListPid(@Param("pid") Integer pid);

    //根据 cid 删除评论信息
    public Integer delCommentCid(@Param("cid") Integer cid);

}
