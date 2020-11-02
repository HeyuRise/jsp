package com.heyu.jsp.config;

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
 * swagger配置类
 *
 * @author heyu
 * @date 2018-09-01
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket addUp() {
        return baseDocket("接口", "com.heyu.jsp.control");
    }

    /**
     * 生成文档
     * @param groupName      文档名称
     * @param basePackage    包名
     * @return
     */
    private Docket baseDocket(String groupName, String basePackage){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .contact(new Contact("孙贺宇","","shy19940918@live.com"))
                .version("1.0")
                .build();
    }
}