package com.soyoung.photo.service.impl;

import com.soyoung.photo.entity.Notice;
import com.soyoung.photo.mapper.NoticeMapper;
import com.soyoung.photo.service.NoticeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
