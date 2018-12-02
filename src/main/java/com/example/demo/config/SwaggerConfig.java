package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    public Docket petApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any()).build();

    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("框架接口的API文档")
                .termsOfServiceUrl("www.baidu.com")
                .version("1.0.0")
                .build();
    }
}
