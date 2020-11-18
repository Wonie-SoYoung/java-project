package com.soyoung.photo.freeanglequeen.service;

import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.entity.Report;

public interface ReportService {

    //根据分页查询举报信息
    public PageInfo<Report> selReportListAll(Integer reType);

    //根据 rid 修改isnot
    public Integer updReportRid(Integer isnot,Integer rid);
}
