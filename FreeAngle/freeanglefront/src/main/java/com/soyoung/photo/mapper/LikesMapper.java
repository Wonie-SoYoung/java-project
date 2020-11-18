package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Likes;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soyoung.photo.entity.Production;
import com.soyoung.photo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 点赞记录表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-27
 */
public interface LikesMapper extends BaseMapper<Likes> {

    //根据作品id查询点赞人
    @Select("select * from likes where pid=#{pid}")
    public List<Likes> getProByIdIsLikes(Integer pid);

    //根据作品id删除点赞
    @Delete("delete from likes where pid=#{pid}")
    public Integer delProByLikes(Integer pid);
}
