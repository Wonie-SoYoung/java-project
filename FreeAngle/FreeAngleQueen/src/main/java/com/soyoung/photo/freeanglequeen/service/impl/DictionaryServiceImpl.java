package com.soyoung.photo.freeanglequeen.service.impl;

import com.soyoung.photo.freeanglequeen.dao.DictionaryMapper;
import com.soyoung.photo.freeanglequeen.entity.Dictionary;
import com.soyoung.photo.freeanglequeen.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    public DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> selDictionaryListTypeCode(String typeCode) {
        try {
            return dictionaryMapper.selDictionaryListTypeCode(typeCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Dictionary selDictionaryID(Integer id) {
        return dictionaryMapper.selDictionaryID(id);
    }

    @Override
    public List<Dictionary> getDictionaryList() {
        return dictionaryMapper.getDictionaryList();
    }

    @Override
    public List<Dictionary> getMsg() {
        return dictionaryMapper.getMsg();
    }
}
