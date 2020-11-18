package com.soyoung.common.utils;

import com.soyoung.common.base.DataBaseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 输出打印工具类
 *
 */
@Configuration
public class Log {
    @Autowired
    private DataBaseEnum dataBaseEnum;

    @Autowired
    private static Log log;

    private static Logger logger = LoggerFactory.getLogger("System-Output");

    @PostConstruct
    public synchronized void init() {
        log = this;
        log.dataBaseEnum = this.dataBaseEnum;
    }

    public static void info(Object obj) {
        if(log.dataBaseEnum.outFlag) {
            logger.info(obj.toString());
        }
    }

    public static void info(Object obj, Exception e) {
        if(log.dataBaseEnum.outFlag) {
            logger.info(obj.toString(),e);
        }
    }

    public static void warn(Object obj){
        if(log.dataBaseEnum.outFlag) {
            logger.warn(obj.toString());
        }
    }

    public static void warn(Object obj, Exception e){
        if(log.dataBaseEnum.outFlag) {
            logger.warn(obj.toString(),e);
        }
    }

    public static void error(Object obj){
        if(log.dataBaseEnum.outFlag) {
            logger.error(obj.toString());
        }
    }

    public static void error(Object obj, Exception e){
        if(log.dataBaseEnum.outFlag) {
            logger.error(obj.toString(),e);
        }
    }
}
