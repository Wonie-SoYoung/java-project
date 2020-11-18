package com.soyoung.photo.service.impl;

import com.soyoung.photo.entity.Report;
import com.soyoung.photo.mapper.ReportMapper;
import com.soyoung.photo.service.ReportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 举报表 服务实现类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-02
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Integer addReport(Report report) {
        return reportMapper.insert(report);
    }
}
