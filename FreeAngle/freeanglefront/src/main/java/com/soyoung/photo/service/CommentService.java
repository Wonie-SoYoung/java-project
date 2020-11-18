package com.soyoung.photo.service;

import com.soyoung.photo.entity.Comment;
import com.baomidou.mybatisplus.service.IService;
import com.soyoung.photo.util.FreePage;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-29
 */
public interface CommentService extends IService<Comment> {

    //根据作品查询评论
    public FreePage getProIsCommentPage(Integer pid,Integer currPage);

    //添加评论
    public Integer addComment(Comment comment);

    //删除评论
    public Integer delComment(Integer cid);
}
