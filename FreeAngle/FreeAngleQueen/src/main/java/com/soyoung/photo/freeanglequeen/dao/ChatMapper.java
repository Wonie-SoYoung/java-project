package com.soyoung.photo.freeanglequeen.dao;

import java.util.List;
import java.util.Map;

public interface ChatMapper {

    //查询所有
    public List<Map<String,Object>> getChat(Map<String, Object> parmmap);
}
