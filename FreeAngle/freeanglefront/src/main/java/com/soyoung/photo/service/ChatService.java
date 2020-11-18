package com.soyoung.photo.service;

import com.soyoung.photo.entity.Chat;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 私聊表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-05
 */
public interface ChatService extends IService<Chat> {

    //查询两位聊天记录
    public List<Chat> getUserisUserorChat(Integer auid,Integer buid);

    //添加消息
    public Integer addChat(Chat chat);
}
