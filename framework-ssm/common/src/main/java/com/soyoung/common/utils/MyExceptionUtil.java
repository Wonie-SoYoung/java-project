package com.soyoung.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.soyoung.common.exception.MyException;

/**
 * 异常工具类
 */
public class MyExceptionUtil {

    public MyExceptionUtil() {
    }

    public static MyException mxe(String msg, Throwable t, Object... params) {
        return new MyException(StringUtils.format(msg, params), t);
    }

    public static MyException mxe(String msg, Object... params) {
        return new MyException(StringUtils.format(msg, params));
    }

    public static MyException mxe(Throwable t) {
        return new MyException(t);
    }

}
