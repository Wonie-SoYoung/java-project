package com.soyoung.photo.freeanglequeen.dao;

import com.soyoung.photo.freeanglequeen.entity.Report;
import com.soyoung.photo.freeanglequeen.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReportMapper {

    //分页查询所有举报信息
    @Results({
            /*@Result(column = "uid",property = "uuser",javaType = List.class,
                many = @Many(select = "com.soyoung.photo.freeanglequeen.dao.UserMapper.selUserListId")),*/
            @Result(column = "uid",property = "uuser",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.freeanglequeen.dao.UserMapper.selUserListId")),
            @Result(column = "byuid",property = "byuUser",javaType = User.class,
                    one = @One(select = "com.soyoung.photo.freeanglequeen.dao.UserMapper.selUserListId"))

    })
    @Select("<script>" +
            "select " +
            "rid,uid,byuid,reType,reTypeCodeId,explains,isnot,createTime " +
            "from report " +
            "where 1=1" +
            "<if test='reType!=null'> " +
            "and reType=#{reType}" +
            "</if>" +
            "</script>")
    public List<Report> selReportListAll(@Param("reType") Integer reType);

    //根据 rid 修改isnot
    public Integer updReportRid(@Param("isnot") Integer isnot,@Param("rid") Integer rid);

}
