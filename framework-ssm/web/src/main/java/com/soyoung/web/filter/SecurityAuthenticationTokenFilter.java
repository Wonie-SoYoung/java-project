package com.soyoung.web.filter;

import cn.hutool.core.util.StrUtil;
import com.soyoung.common.base.DataBaseEnum;
import com.soyoung.security.utils.IpUtil;
import com.soyoung.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token的校验
 * 从http头的Authorization项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @Author: 梅晓寒
 * @Date: 2020/5/19 12:34
 *
 */
@Component
public class SecurityAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataBaseEnum dataBaseEnum;

    /**
     * 该拦截器主要的功能是，拦截请求后，判断是否携带token，如果未携带token则不予通过。
     *
     * @param httpServletRequest  http请求
     * @param httpServletResponse http响应
     * @param filterChain         拦截器
     * @throws ServletException 异常信息
     * @throws IOException      异常信息
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //解决跨域问题
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        // 获取request中token
        String token = httpServletRequest.getHeader(dataBaseEnum.header);

        // 判断token不是为空且以Bearer+空格开头
        if (!StrUtil.hasBlank(token) && token.startsWith(dataBaseEnum.tokenStart)) {
            // 将Bearer+空格截取掉
            token = token.replace(dataBaseEnum.tokenStart, "");
            // 从token中获取用户openid
            String info = JwtTokenUtil.getUsernameFromToken(token);

            if (!StrUtil.hasBlank(info) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 通过工号获取用户的信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(info);

                // 验证token和用户信息是否匹配、token是否有效、ip是否一致
                if (JwtTokenUtil.validateToken(token, userDetails, IpUtil.getIpAddr(httpServletRequest))) {

                    // 构造UsernamePasswordAuthenticationToken对象
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // 绑定到当前request中，在后面的请求中就可以获取用户信息
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
