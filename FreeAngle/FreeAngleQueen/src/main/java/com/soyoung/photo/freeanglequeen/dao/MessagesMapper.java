package com.soyoung.photo.freeanglequeen.dao;

import java.util.List;
import java.util.Map;

public interface MessagesMapper {

    //查询所有
    public List<Map<String,Object>> getMessage(Map<String, Object> parmmap);

    //添加
    public int addMessage(Map<String, Object> map);

    //删除信息
    public int delMsg(Integer id);
}
