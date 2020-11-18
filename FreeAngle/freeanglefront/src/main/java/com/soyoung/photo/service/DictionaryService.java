package com.soyoung.photo.service;

import com.soyoung.photo.entity.Dictionary;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public interface DictionaryService extends IService<Dictionary> {

    //查询全部字典
    public List<Dictionary> getTypeList();
}
