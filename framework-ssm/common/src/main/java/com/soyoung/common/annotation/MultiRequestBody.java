package com.soyoung.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @Author: 梅晓寒
 * @Date: 2020/8/7 19:35
 */
// 表示该注解用于什么地方，PARAMETER：参数声明
@Target(ElementType.PARAMETER)
// 表示该注解可以保存的范围，RUNTIME：运行期：即此注解可以在运行时保留，可以通过反、反射获得
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiRequestBody {

    /**
     * 是否必须出现的参数
     */
    boolean required() default true;

    /**
     * 当value的值或者参数名不匹配时，是否允许解析最外层属性到该对象
     */
    boolean parseAllFields() default true;

    /**
     * 解析时用到的JSON的key
     */
    String value() default "";
}
