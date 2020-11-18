package com.soyoung.web.filter;

import com.soyoung.common.base.BaseEnum;
import com.soyoung.common.entity.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 登陆状态下，权限不足执行该方法
 *
 * @Param:
 * @Author: 梅晓寒
 * @Date: 2020/5/19 12:32
 *
 */
@Component("SecurityAuthenticationAccessDeniedHandler")
public class SecurityAuthenticationAccessDeniedHandler implements AccessDeniedHandler, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.sendError(BaseEnum.NO_OPERATION_AUTHORITY.getCode(), BaseEnum.NO_OPERATION_AUTHORITY.getMsg());
        // 如果设置了error信息，则以下代码不会执行
        PrintWriter printWriter = response.getWriter();
        Result result = Result.errorJson(BaseEnum.NO_OPERATION_AUTHORITY.getCode(), BaseEnum.NO_OPERATION_AUTHORITY.getMsg());
        printWriter.write(result.toString());
        printWriter.flush();
    }
}
