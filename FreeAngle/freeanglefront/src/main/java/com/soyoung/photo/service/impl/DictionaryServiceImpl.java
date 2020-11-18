package com.soyoung.photo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.soyoung.photo.entity.Dictionary;
import com.soyoung.photo.mapper.DictionaryMapper;
import com.soyoung.photo.service.DictionaryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soyoung.photo.util.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> getTypeList() {
        return dictionaryMapper.selectList(null);
    }
}
