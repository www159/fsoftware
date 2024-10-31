package com.example.nationaltax.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment)
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("")
                .enable(true)  //是否启用swagger，如果为false，则不能在浏览器中访问
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                //any扫描全部，none都不扫描
                //withClassAnnotation扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation扫描方法上的注解
                .apis(RequestHandlerSelectors.any())
                //过滤路径
                //.paths(PathSelectors.ant("/kuang/**"))
                .build();//创造
    }


    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo()
    {
        //作者信息
        Contact contact = new Contact("ds", "", "");

        return new ApiInfo("订单系统文档",
                "订单系统的接口描述",
                "1.0",
                "",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
