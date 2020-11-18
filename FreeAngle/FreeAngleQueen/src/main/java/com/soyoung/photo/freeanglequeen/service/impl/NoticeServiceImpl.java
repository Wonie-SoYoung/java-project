package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.NoticeMapper;
import com.soyoung.photo.freeanglequeen.entity.Notice;
import com.soyoung.photo.freeanglequeen.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public List<Map<String, Object>> getNotice(Map<String,Object> parmap) {
        return noticeMapper.getNotice(parmap);
    }

    @Override
    public int addNotice(Map<String , Object> map) {
        return noticeMapper.addNotice(map);
    }

    @Override
    public int UpdMessage(Map<String, Object> map) {
        return noticeMapper.UpdMessage(map);
    }

    @Override
    public Notice getNoticeEntity(Integer nid) {
        return noticeMapper.getNoticeEntity(nid);
    }

    @Override
    public int delNotice(Integer id) {
        return noticeMapper.delNotice(id);
    }
}
