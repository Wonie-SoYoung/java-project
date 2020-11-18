package com.soyoung.photo.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.soyoung.photo.entity.Facility;
import com.soyoung.photo.entity.Production;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.soyoung.photo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 作品表 Mapper 接口
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-06
 */
public interface ProductionMapper extends BaseMapper<Production> {

    //查询作品视图
    @Select("<script>select * from proview" +
            " where p_label like CONCAT('%',#{label},'%') and p_privacyId=9" +
            "<if test='engstr!=null'> or p_label like CONCAT('%',#{engstr},'%') and p_privacyId=9</if>" +
            " limit 0,#{pageSize}" +
            "</script>")
    public List<Map<String,Object>> gethomeProList(
            @Param("label") String label,@Param("engstr") String engstr,@Param("pageSize") Integer pageSize);

    //查询作品可根据（标签、名称、类型）
    @Select("<script>" +
            "select * from proview" +
            " where 1=1" +
            "<if test='label!=null'> and p_label like CONCAT('%',#{label},'%') and p_privacyId=9</if>" +
            "<if test='engstr!=null'> or p_label like CONCAT('%',#{engstr},'%') and p_privacyId=9</if>" +
            "<if test='pName!=null'> and p_pName like CONCAT('%',#{pName},'%') and p_privacyId=9</if>" +
            "<if test='engpName!=null'> or p_pName like CONCAT('%',#{engpName},'%') and p_privacyId=9</if>" +
            "<if test='proType!=null'> and p_protypeId=#{proType} and p_privacyId=9</if>" +
            " limit #{currPage},#{pageSize}" +
            "</script>")
    public List<Map<String,Object>> getTermProList(
            @Param("label") String label,
            @Param("engstr") String engstr,
            @Param("pName") String pName,
            @Param("engpName") String engpName,
            @Param("proType") Integer proType,
            @Param("currPage") Integer currPage,
            @Param("pageSize") Integer pageSize
    );

    //查询作品详情
    @Results({
            @Result(column = "likeid",property = "likesList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.LikesMapper.getProByIdIsLikes")),
            @Result(column = "collectid",property = "collectList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.CollectMapper.getProIsCollectList")),
            @Result(column = "medalid",property = "modelList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.MedalMapper.getProIsMedal")),
            @Result(column = "commentid",property = "commentNum",javaType = Integer.class,
                    one = @One(select = "com.soyoung.photo.mapper.CommentMapper.getProIsCommentNum")),
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.mapper.UserMapper.getUserInById")),
            @Result(column = "facilityId",property = "facility",javaType = Facility.class,
                    one = @One(select = "com.soyoung.photo.mapper.FacilityMapper.selectById")),
    })
    @Select("select p.*,p.id as likeid,p.id as collectid,p.id as medalid,p.id as commentid from production p where p.id=#{pid}")
    public Production getisPro(Integer pid);

    //查看创作人下的作品
    @Results({
            @Result(column = "likeid",property = "likesList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.LikesMapper.getProByIdIsLikes")),
            @Result(column = "p_id",property = "modelList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.MedalMapper.getProIsMedal"))
    })
    @Select("<script>" +
            "select p.*,p.id as likeid,p.id as p_id" +
            " from production p where p.uid=#{uid} order by p.createTime desc" +
            "<if test='currPage!=null and pageSize!=null'> limit #{currPage},#{pageSize}</if>" +
            "</script>")
    public List<Production> getuidByPro(@Param("uid") Integer uid,@Param("currPage")Integer currPage,@Param("pageSize")Integer pageSize);

    //查看创作人下的作品
    @Results({
            @Result(column = "likeid",property = "likesList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.LikesMapper.getProByIdIsLikes")),
            @Result(column = "p_id",property = "modelList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.MedalMapper.getProIsMedal"))
    })
    @Select("select p.*,p.id as likeid,p.id as p_id" +
            " from production p where p.uid=#{uid} and p.privacyId=9 order by p.createTime desc")
    public List<Production> getuidInByPro(@Param("uid") Integer uid);

    //查询相关的作品
    @Results({
            @Result(column = "likeid",property = "likesList",javaType = List.class,
                    many = @Many(select = "com.soyoung.photo.mapper.LikesMapper.getProByIdIsLikes")),
            @Result(column = "facilityId",property = "facility",javaType = Facility.class,
                    one = @One(select = "com.soyoung.photo.mapper.FacilityMapper.selectById"))
    })
    @Select("<script>" +
            "select p.*,p.id as likeid from production p where 1=1 and p.id!=#{pid} and p.privacyId=9" +
            "<if test='labels!=null'>" +
            "<foreach collection='labels' item='label'>" +
            " or p.label like CONCAT('%',#{label},'%') and p.id!=#{pid} and p.privacyId=9" +
            "</foreach>" +
            "</if>" +
            "order by p.createTime,p.viewNum desc limit 0,6" +
            "</script>")
    public List<Production> getIdIsLikeByPros(@Param("labels") List<String> labels,@Param("pid")Integer pid);

    //进行搜索作品
    @Select("<script>select * from proview" +
            " where p_label like CONCAT('%',#{value},'%') and p_protypeId=#{type} and p_privacyId=9" +
            "<if test='finevalue!=null'> or p_label like CONCAT('%',#{finevalue},'%') and p_protypeId=#{type} and p_privacyId=9</if>" +
            " or p_pName like CONCAT('%',#{value},'%') and p_protypeId=#{type} and p_privacyId=9" +
            "<if test='finevalue!=null'> or p_pName like CONCAT('%',#{finevalue},'%') and p_protypeId=#{type} and p_privacyId=9</if>" +
            " or p_describes like CONCAT('%',#{value},'%') and p_protypeId=#{type} and p_privacyId=9" +
            "<if test='finevalue!=null'> or p_describes like CONCAT('%',#{finevalue},'%') and p_protypeId=#{type} and p_privacyId=9</if>" +
            " limit #{currPage},#{pageSize}" +
            "</script>")
    public List<Map<String,Object>> getHuntPro(@Param("value")String value,@Param("finevalue")String finevalue,@Param("type")Integer type,
                                               @Param("currPage")Integer currPage,@Param("pageSize")Integer pageSize);

    //查询关注的作品
    @Select("<script>" +
            "select p.* from (select * from proview order by p_createTime desc) p where 1=1 " +
            "<foreach collection='uidList' item='uid' separator=',' close=')' open='and p.u_id in('>" +
            "#{uid}" +
            "</foreach>" +
            " and p.p_privacyId=9 " +
            "GROUP BY p.p_id limit #{currPage},#{pageSize}" +
            "</script>")
    public List<Map<String,Object>> getFocusProductionList(
            @Param("uidList") List<Integer> uidList,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //查询收藏的作品
    @Select("select p.* from collect c,proview p where c.pid=p.p_id and c.uid=#{uid} order by c.createTime desc limit #{currPage},#{pageSize}")
    public List<Map<String,Object>> getCollectProductionList(
            @Param("uid") Integer uid,@Param("currPage") Integer currPage,@Param("pageSize") Integer pageSize);

    //修改观看次数
    @Update("update production set viewNum=viewNum+1 where id=#{pid}")
    public Integer updateNumBer(Integer pid);
}
