package com.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mybatis通用mapper所在包
@MapperScan(basePackages = "com.hospital.mapper")
// 开启定时任务
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}