package com.jiebao.jpmp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
      private  WebSecurityConfig webSecurityConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        registry.addInterceptor(webSecurityConfig).addPathPatterns("/**")
                // excludePathPatterns 用户排除拦截
                .excludePathPatterns( "/login", "/login.html","/","/layui/**","/libs/**","tomcat.keystore","/wx**","/Wx**","/DetailCommodity","/listPicture","/Linkman");
    }

}
