package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Messages;
import com.soyoung.photo.mapper.MessagesMapper;
import com.soyoung.photo.service.MessagesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import com.soyoung.photo.util.FreePage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {
    @Autowired
    private MessagesMapper messagesMapper;

    @Override
    public Boolean addMessages(Messages messages) {
        Boolean isNot=false;
        if(messagesMapper.insert(messages)>0){
            isNot=true;
        }
        return isNot;
    }

    @Override
    public Map<Integer, Integer> getMessageAllNum(Integer uid) {
        Map<Integer,Integer> messageMap=new HashMap<>();
        //获取点赞消息未读数量
        messageMap.put(Dict.NEWSTYPELIKES,messagesMapper.selectCount(
                new EntityWrapper<Messages>()
                .eq("byuid",uid)
                .eq("mType", Dict.NEWSTYPELIKES)
                .eq("isNot",0)
        ));
        //获取评论消息未读数量
        messageMap.put(Dict.NEWSTYPECOMMENT,messagesMapper.selectCount(
                new EntityWrapper<Messages>()
                .eq("byuid",uid)
                .eq("mType", Dict.NEWSTYPECOMMENT)
                .eq("isNot",0)
        ));
        //获取关注消息未读数量
        messageMap.put(Dict.NEWSTYPEATTENTION,messagesMapper.selectCount(
                new EntityWrapper<Messages>()
                .eq("byuid",uid)
                .eq("mType", Dict.NEWSTYPEATTENTION)
                .eq("isNot",0)
        ));
        //获取收藏消息未读数量
        messageMap.put(Dict.NEWSTYPECOLLECT,messagesMapper.selectCount(
                new EntityWrapper<Messages>()
                .eq("byuid",uid)
                .eq("mType", Dict.NEWSTYPECOLLECT)
                .eq("isNot",0)
        ));
        //获取通知消息未读数量
        messageMap.put(Dict.NEWSTYPEINFORM,messagesMapper.selectCount(
                new EntityWrapper<Messages>()
                .eq("byuid",uid)
                .eq("mType", Dict.NEWSTYPEINFORM)
                .eq("isNot",0)
        ));
        //获取私信消息未读数量
        messageMap.put(Dict.NEWSTYPEPRIVATE,messagesMapper.selectCount(
                new EntityWrapper<Messages>()
                .eq("byuid",uid)
                .eq("mType", Dict.NEWSTYPEPRIVATE)
                .eq("isNot",0)
        ));
        return messageMap;
    }

    @Override
    public FreePage getTypeMessagesList(Integer type, Integer uid,Integer currPage) {
        FreePage freePage=new FreePage();
        freePage.setPageSize(Dict.NEWSPAGESIZE);
        freePage.setCurrPageNo(currPage);
        //定义EntityWrapper
        EntityWrapper<Messages> entityWrapper=new EntityWrapper<Messages>();
        entityWrapper.eq("byuid",uid);
        if(type==Dict.NEWSTYPELIKES){
            entityWrapper.eq("mType",Dict.NEWSTYPELIKES);
            freePage.setList(messagesMapper.getLikeMessagesList(uid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        }else if(type==Dict.NEWSTYPECOMMENT){
            entityWrapper.eq("mType",Dict.NEWSTYPECOMMENT);
            freePage.setList(messagesMapper.getCommentMessagesList(uid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        }else if(type==Dict.NEWSTYPEATTENTION){
            entityWrapper.eq("mType",Dict.NEWSTYPEATTENTION);
            freePage.setList(messagesMapper.getAttentionMessagesList(uid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        }else if(type==Dict.NEWSTYPECOLLECT){
            entityWrapper.eq("mType",Dict.NEWSTYPECOLLECT);
            freePage.setList(messagesMapper.getCollectMessagesList(uid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        }else if(type==Dict.NEWSTYPEINFORM){
            entityWrapper.eq("mType",Dict.NEWSTYPEINFORM);
            freePage.setList(messagesMapper.getNoticeMessagesList(uid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        }else if(type==Dict.NEWSTYPEPRIVATE){
            entityWrapper.eq("mType",Dict.NEWSTYPEPRIVATE);
            freePage.setList(messagesMapper.getChatMessagesList(uid,(currPage-1)*freePage.getPageSize(),freePage.getPageSize()));
        }
        freePage.setTotalCount(messagesMapper.selectCount(entityWrapper));
        return freePage;
    }

    @Override
    public Integer updateMessageYes(Integer type, Integer uid) {
        return messagesMapper.updateMessageYes(type,uid);
    }
}
