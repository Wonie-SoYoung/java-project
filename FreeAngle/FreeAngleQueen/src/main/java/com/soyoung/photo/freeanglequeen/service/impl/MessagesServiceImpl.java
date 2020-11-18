package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.MessagesMapper;
import com.soyoung.photo.freeanglequeen.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesMapper messagesMapper;
    @Override
    public List<Map<String, Object>> getMessage(Map<String,Object> parmmap) {
        return messagesMapper.getMessage(parmmap);
    }

    @Override
    public int addMessage(Map<String, Object> map) {
        return messagesMapper.addMessage(map);
    }

    @Override
    public int delMsg(Integer id) {
        return messagesMapper.delMsg(id);
    }

}
