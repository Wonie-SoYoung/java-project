package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Medal;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 作品勋章表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-14
 */
public interface MedalMapper extends BaseMapper<Medal> {

    //根据作品id查询勋章
    @Select("select m.* from productionAndmedal p,medal m where p.mid=m.mId and p.pid=#{pid}")
    public List<Medal> getProIsMedal(Integer pid);

    //删除作品相关勋章
    @Delete("delete from productionAndmedal where pid=#{pid}")
    public Integer delProAllMedal(Integer pid);
}
