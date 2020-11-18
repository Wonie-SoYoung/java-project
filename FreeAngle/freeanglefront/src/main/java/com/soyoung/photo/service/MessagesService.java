package com.soyoung.photo.service;

import com.soyoung.photo.entity.Messages;
import com.baomidou.mybatisplus.service.IService;
import com.soyoung.photo.util.FreePage;
import java.util.Map;

/**
 * <p>
 * 消息表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-06
 */
public interface MessagesService extends IService<Messages> {

    //添加消息
    public Boolean addMessages(Messages messages);

    //根据类型返回未读消息数量
    public Map<Integer,Integer> getMessageAllNum(Integer uid);

    //根据类型查询当前用户的消息
    public FreePage getTypeMessagesList(Integer type, Integer uid,Integer currPage);

    //修改已读
    public Integer updateMessageYes(Integer type,Integer uid);
}
