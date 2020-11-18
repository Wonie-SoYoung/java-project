package com.soyoung.photo.freeanglequeen.service;

import com.soyoung.photo.freeanglequeen.entity.Dictionary;

import java.util.List;

public interface DictionaryService {

    /*
    * 范宏伦
    * */
    //根据举报类型查询字典信息:REPORT_TYPE举报类型
    public List<Dictionary> selDictionaryListTypeCode(String typeCode);

    //根据 ID 查询字典信息
    public Dictionary selDictionaryID(Integer id);

    /*
    * 陈海嘉
    **/
    //查询作品的类型
    public List<Dictionary> getDictionaryList();

    //查询消息类型
    public List<Dictionary> getMsg();

}
