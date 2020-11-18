package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Dictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DictionaryMapper {

    //根据举报类型查询字典信息:REPORT_TYPE举报类型
    public List<Dictionary> selDictionaryListTypeCode(@Param("typeCode") String typeCode);

    //根据 ID 查询字典信息
    public Dictionary selDictionaryID(@Param("id") Integer id);

    //查询作品的类型
    public List<Dictionary> getDictionaryList();

    //查询消息类型
    public List<Dictionary> getMsg();

}
