package com.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: foodie-dev
 * @description: Swagger配置文件
 * @author: wty
 * @create: 2020-02-13 17:04
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    // 配置Swagger2的核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型位swagger2
                .apiInfo(apiInfo())  // 用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hospital.controller"))  // 指定controller扫描包
                .paths(PathSelectors.any())  // 所有controller包
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("医院项目接口文档")  // 文档标题
                .contact(new Contact("吴天宇","","3085229110@qq.com"))  // 联系人信息
                .description("医院项目接口文档") // 详细信息
                .version("1.0.1")  // 版本信息
                .termsOfServiceUrl("") // 网站地址
                .build();
    }

}
