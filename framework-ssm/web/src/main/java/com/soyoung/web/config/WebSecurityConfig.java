package com.soyoung.web.config;

import com.soyoung.web.filter.SecurityAuthenticationEntryPoint;
import com.soyoung.web.filter.SecurityAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Security的核心配置类
 *
 * @Param:
 * @Author: 梅晓寒
 * @Date: 2020/5/19 12:31
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityAuthenticationEntryPoint unauthorizedHandler;

    private final AccessDeniedHandler accessDeniedHandler;

    private final UserDetailsService userDetailsService;

    private final SecurityAuthenticationTokenFilter securityAuthenticationTokenFilter;


    @Autowired
    public WebSecurityConfig(SecurityAuthenticationEntryPoint unauthorizedHandler,
                             @Qualifier("SecurityAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
                             @Qualifier("SecurityUserDetailsServiceImpl") UserDetailsService userDetailsService,
                             SecurityAuthenticationTokenFilter securityAuthenticationTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
        this.securityAuthenticationTokenFilter = securityAuthenticationTokenFilter;
    }


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }



    /**
     * 这必须手动注入spring管理，否则之后使用的时候找不到，
     * 报错信息：Field authenticate in com.ywh.security.service.impl.SysUserServiceImpl required a bean of type
     * 'org.springframework.security.authentication.AuthenticationManager' that could not be found.
     *
     * @return AuthenticationManager
     * @throws Exception 异常信息
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * 配置如何通过拦截器保护我们的请求，哪些能通过哪些不能通过,允许对特定的http请求基于安全考虑进行配置
     *
     * @param httpSecurity http
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 由于使用的是JWT，我们这里不需要csrf
                .cors().and().csrf().disable().authorizeRequests()
                // 登录
                .antMatchers("/nclub/auth/login").permitAll()
                //放行资源
                .antMatchers("/nclub/statics/**").permitAll()
                .antMatchers("/soyoung/**").permitAll()
                // 放行swagger接口页面
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/druid/login.html","/druid/index.html").permitAll()
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity.addFilterBefore(securityAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 这块是配置跨域请求的
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();

        // 让Spring security放行所有preflight request预检请求
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

    }


    /**
     * 这块是配置跨域请求的
     *
     * @return Cors过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.addAllowedOrigin("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", cors);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


    /**
     * 密码加密
     *
     * @return 返回加密后的密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
