package com.soyoung.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.soyoung")
@MapperScan(basePackages = "com.soyoung.*.dao")
@ServletComponentScan(basePackages = {"com.soyoung.**.config"})
@EnableTransactionManagement
@EnableSwagger2
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    /**
     * war包启动配置项
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }


    /**
     *  解决在方法中使用(ServletRequestAttributes) RequestContextHolder.getRequestAttributes()空指针问题
     *
     * @return
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }


    /**
     * 解决服务器上If your application is running on WebSphere Application Server you may be able to resolve this problem by setting
     * com.ibm.ws.webcontainer.invokeFlushAfterService to false的问题
     */
    WebApplication() {
        this.setRegisterErrorPageFilter(false);
    }
}
