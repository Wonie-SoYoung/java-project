package com.soyoung.photo.mapper;

import com.soyoung.photo.entity.Hotlabels;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 热门标签表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public interface HotlabelsMapper extends BaseMapper<Hotlabels> {

    //查询标签页所需数据
    @Select("select h.*,(select p.hiapkURL from production p where p.label like concat('%',h.hName,'%') or p.label like concat('%',h.finehName,'%') order by p.createTime,(select count(1) from likes where pid=p.id) desc limit 0,1) as proUrl01,\n" +
            "(select p.hiapkURL from production p where p.label like concat('%',h.hName,'%') or p.label like concat('%',h.finehName,'%') order by p.createTime,(select count(1) from likes where pid=p.id) desc limit 1,1) as proUrl02,\n" +
            "(select p.hiapkURL from production p where p.label like concat('%',h.hName,'%') or p.label like concat('%',h.finehName,'%') order by p.createTime,(select count(1) from likes where pid=p.id) desc limit 2,1) as proUrl03\n" +
            "from hotlabels h where h.parentId=#{hid} limit #{currPage},#{pageSize}")
    public List<Map<String,Object>> getProByLabels(@Param("hid") Integer hid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询上传作品给出的建议标签
    @Select("select h.* from hotlabels h where parentId is not null order by (select count(1) from production where label like concat('%',h.hName,'%') or label like concat('%',h.finehName,'%')) desc limit 0,6")
    public List<Hotlabels> getSuggestLabelsList();
}
