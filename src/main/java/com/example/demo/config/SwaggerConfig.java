package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any()).build()
                .globalOperationParameters(headerParamter());

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("框架接口的API文档")
                .termsOfServiceUrl("www.baidu.com")
                .version("1.0.0")
                .build();
    }


    private List<Parameter> headerParamter() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("ticket").description("user ticket")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameters.add(ticketPar.build());
        return parameters;
    }
}
