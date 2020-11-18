package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    //查询所有
    public List<Map<String,Object>> getNotice(Map<String, Object> parmap);

    //添加信息
    public int addNotice(Map<String, Object> map);

    //修改信息
    public int UpdMessage(Map<String, Object> map);

    //返回对象的查询
    public Notice getNoticeEntity(Integer nid);

    //删除通知
    public int delNotice(Integer id);
}

