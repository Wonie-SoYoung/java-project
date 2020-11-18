package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Collect;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soyoung.photo.entity.Production;
import com.soyoung.photo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-28
 */
public interface CollectMapper extends BaseMapper<Collect> {

    //根据作品查询收藏列表
    @Select("select * from collect where pid=#{pid}")
    public List<Collect> getProIsCollectList(Integer pid);

    //根据作品id删除收藏
    @Delete("delete from collect where pid=#{pid}")
    public Integer delProByCollect(Integer pid);
}
