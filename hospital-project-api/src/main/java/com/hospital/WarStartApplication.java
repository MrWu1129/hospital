package com.hospital;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program: hospital-project
 * @description: 打包war，war包启动类
 * @author: wty
 * @create: 2020-05-16 14:17
 */
public class WarStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个springboot启动类
        return builder.sources(Application.class);
    }
}