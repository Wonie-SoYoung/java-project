package com.soyoung.common.base;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局常量值类
 */
@Configuration
public class BaseConstants {

    // 分页的总条数
    public static final String TOTAL = "total";

    // 分页的数据
    public static final String ROWS = "rows";

    // 分页的总页数
    public static final String TOTAL_PAGE = "totalpage";

    //本机配置的IP
    public static final String IP_ADRESS = "127.0.0.1";

    //默认时间格式
    public static final String DATA_FORMAT = "yyyy-MM-dd";

    // 上传图片路径集合
    public static final Map<String, String> ossmap;

    // 静态代码块，将前缀与对应的OSS文件目录放入静态map中
    static {
        ossmap = new HashMap<>();
        // ----------------------系统----------------------
        // 公司logo 实例
        //ossmap.put("logo", "Sys_Source/logo");
    }
}
