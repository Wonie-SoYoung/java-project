package com.soyoung.photo.service.impl;

import com.soyoung.photo.entity.Chat;
import com.soyoung.photo.mapper.ChatMapper;
import com.soyoung.photo.service.ChatService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 私聊表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-05
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Autowired
    private ChatMapper chatMapper;

    @Override
    public List<Chat> getUserisUserorChat(Integer auid, Integer buid) {
        return chatMapper.getUserisUserorChat(auid,buid);
    }

    @Override
    public Integer addChat(Chat chat) {
        return chatMapper.insert(chat);
    }
}
