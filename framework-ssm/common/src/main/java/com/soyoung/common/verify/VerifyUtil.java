package com.soyoung.common.verify;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 数据校验工具类
 *
 * @ClassName VerifyUtil
 * @Description TODO
 * @Author 梅晓寒
 * @Date 2020/4/16 11:13
 * @Version 1.0
 **/
public class VerifyUtil {

    /**
     * 数据校验错误信息输出
     *
     * @Param: [bindingResult]
     * @Author: 梅晓寒
     * @Date: 2020/4/16 11:17
     * @Return: com.cpic.ddzsms.common.entity.Result
     */
    public static String getValidateError(BindingResult bindingResult){
        StringBuffer sb=new StringBuffer();
        for(FieldError error : bindingResult.getFieldErrors()){
            sb.append(error.getDefaultMessage()+",");
        }
        return sb.toString();
    }
}
