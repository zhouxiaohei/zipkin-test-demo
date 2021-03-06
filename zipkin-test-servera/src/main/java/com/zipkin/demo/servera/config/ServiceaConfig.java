package com.zipkin.demo.servera.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ClassName ServiceaConfig
 * @Author JackZhou
 * @Date 2020/3/16  17:37
 **/
@Configuration
public class ServiceaConfig {


    @Value("${spring.swagger.package:com.cloud.demo}")
    private String swaggerPackage;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("cloud-service-a")
                .description("这是一个简单的 Swagger API 演示")
                .version("1.0.0-SNAPSHOT")
                .build();
    }

}
