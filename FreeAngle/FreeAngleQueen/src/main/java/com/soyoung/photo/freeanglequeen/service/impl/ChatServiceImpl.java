package com.soyoung.photo.freeanglequeen.service.impl;


import com.soyoung.photo.freeanglequeen.dao.ChatMapper;
import com.soyoung.photo.freeanglequeen.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;
    @Override
    public List<Map<String, Object>> getChat(Map<String, Object> parmmap) {
        return chatMapper.getChat(parmmap);
    }
}
