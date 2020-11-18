package com.soyoung.photo.freeanglequeen.service;

import com.soyoung.photo.freeanglequeen.entity.Comment;

import java.util.List;

public interface CommentService {

    //根据作品外键 pid 查询出所有的评论信息
    public List<Comment> selCommentListPid(Integer pid);

    //根据举报表外键 reTypeCodeId 查询评论表中的评论信息
    public Comment selCommentReTypeCodeId(Integer reTypeCodeId);

    //根据作品外键 pid 查询出所有的评论信息
    public List<Comment> selCommentsListPid(Integer pid);

    //根据 cid 删除评论信息
    public Integer delCommentCid(Integer cid);

}
