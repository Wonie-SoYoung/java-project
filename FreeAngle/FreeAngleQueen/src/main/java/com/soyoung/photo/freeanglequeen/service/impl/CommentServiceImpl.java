package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.CommentMapper;
import com.soyoung.photo.freeanglequeen.dao.UserMapper;
import com.soyoung.photo.freeanglequeen.entity.Comment;
import com.soyoung.photo.freeanglequeen.entity.State;
import com.soyoung.photo.freeanglequeen.entity.User;
import com.soyoung.photo.freeanglequeen.service.CommentService;
import com.soyoung.photo.freeanglequeen.service.StateService;
import com.soyoung.photo.freeanglequeen.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    public CommentMapper commentMapper;
    @Resource
    public UserMapper userMapper;
    @Resource
    public StateService stateService;


    @Override
    public List<Comment> selCommentListPid(Integer pid) {
        List<Comment> comment = commentMapper.selCommentListPid(pid);
        return comment;
    }

    @Override
    public Comment selCommentReTypeCodeId(Integer reTypeCodeId) {
        Comment comment = commentMapper.selCommentReTypeCodeId(reTypeCodeId);
        User user = comment.getUser();
        user.setState(stateService.selStateId(user.getStateid()));
        comment.setUser(user);
        return comment;
    }

    @Override
    public List<Comment> selCommentsListPid(Integer pid) {
        return commentMapper.selCommentsListPid(pid);
    }

    @Override
    public Integer delCommentCid(Integer cid) {
        return commentMapper.delCommentCid(cid);
    }
}
