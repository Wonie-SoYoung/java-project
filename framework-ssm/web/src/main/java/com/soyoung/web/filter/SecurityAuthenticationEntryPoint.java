package com.soyoung.web.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 验证为未登陆状态会进入此方法，认证错误
 *
 * @Author: 梅晓寒
 * @Date: 2020/5/19 12:35
 *
 */
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
       JSONObject jsonObject = new JSONObject();
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        jsonObject.put("msg","token过期或者失效");
        jsonObject.put("code",401);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

        // 如果设置了error信息，则以下代码不会执行
//        PrintWriter printWriter = response.getWriter();
//        Result result = Result.errorJson(BaseEnum.USER_NOTLOGGENIN.getCode(), BaseEnum.USER_NOTLOGGENIN.getMsg());
//        printWriter.write(result.toString());
//        printWriter.flush();
    }
}
