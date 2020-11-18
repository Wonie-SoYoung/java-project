package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Comment;
import com.soyoung.photo.mapper.CommentMapper;
import com.soyoung.photo.service.CommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-29
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public FreePage getProIsCommentPage(Integer pid, Integer currPage) {
        FreePage freePage=new FreePage();
        freePage.setCurrPageNo(currPage);
        freePage.setPageSize(Dict.COMMENTPAGESIZE);
        freePage.setTotalCount(commentMapper.getProIsCommentNum(pid));
        freePage.setList(commentMapper.getProIsComment(pid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        return freePage;
    }

    @Override
    public Integer addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public Integer delComment(Integer cid) {
        return commentMapper.deleteById(cid);
    }
}
