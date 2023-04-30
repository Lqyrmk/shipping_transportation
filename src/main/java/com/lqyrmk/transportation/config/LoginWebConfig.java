package com.lqyrmk.transportation.config;

import com.lqyrmk.transportation.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 登录配置类
 * @Author YuanmingLiu
 * @Date 2023/4/30 18:46
 */
@Configuration
public class LoginWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/register",
                        "/bootstrap/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**");
    }

}
