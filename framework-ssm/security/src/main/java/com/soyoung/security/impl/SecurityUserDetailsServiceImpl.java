package com.soyoung.security.impl;

import com.soyoung.common.utils.MyExceptionUtil;
import com.soyoung.security.base.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserDetailService的实现类
 * 这个@Primary表示这个类所继承的接口有多个实现类，当不知道引入哪个的时候，优先使用@Primary所注解的类
 */
@Primary
@Service("SecurityUserDetailsServiceImpl")
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        // 获取访问路径
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String servletPath = request.getServletPath();

        SecurityUserDetails securityUserDetails;
        List<String> menuList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        throw MyExceptionUtil.mxe(openId + "用户不存在");
    }
}
