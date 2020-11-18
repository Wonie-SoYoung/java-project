package com.soyoung.photo.freeanglequeen.service.impl;

import com.github.pagehelper.PageInfo;
import com.soyoung.photo.freeanglequeen.dao.ReportMapper;
import com.soyoung.photo.freeanglequeen.entity.Report;
import com.soyoung.photo.freeanglequeen.service.ReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    public ReportMapper reportMapper;

    @Override
    public PageInfo<Report> selReportListAll(Integer reType) {
        List<Report> list = reportMapper.selReportListAll(reType);
        PageInfo<Report> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer updReportRid(Integer isnot, Integer rid) {
        return reportMapper.updReportRid(isnot,rid);
    }
}
