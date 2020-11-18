package com.soyoung.photo.service;

import com.soyoung.photo.entity.Report;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 举报表 服务类
 * </p>
 *
 * @author SoYoung
 * @since 2019-12-02
 */
public interface ReportService extends IService<Report> {

    //添加举报
    public Integer addReport(Report report);
}
