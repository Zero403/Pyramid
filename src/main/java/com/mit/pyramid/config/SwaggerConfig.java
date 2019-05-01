package com.mit.pyramid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {
    public ApiInfo createApi(){
        return new ApiInfoBuilder().title("创客系统").description("打造真正的裂变用户平台").contact(new Contact("Core","10.8.159.28","123@123")).build();
    }

    /**
     * basepackage 生成api所在包
     * @return
     */
    @Bean
    public Docket createDoc(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createApi()).select().apis(RequestHandlerSelectors.basePackage("com.mit.pyramid.controller" )).build();
    }
}
