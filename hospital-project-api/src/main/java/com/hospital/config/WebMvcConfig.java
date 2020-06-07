package com.hospital.config;

import com.hospital.interceptor.UserTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: hospital-project
 * @description:
 * @author: wty
 * @create: 2020-06-06 23:33
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public UserTokenInterceptor userTokenInterceptor(){
        return new UserTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("passport/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
