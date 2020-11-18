package com.soyoung.photo.freeanglequeen.service;

import java.util.List;
import java.util.Map;

public interface ChatService {

    //查询所有
    public List<Map<String,Object>> getChat(Map<String, Object> parmmap);
}
