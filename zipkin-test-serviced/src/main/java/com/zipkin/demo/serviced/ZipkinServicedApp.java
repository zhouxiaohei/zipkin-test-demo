package com.zipkin.demo.serviced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ServicebApp
 * @Author JackZhou
 * @Date 2020/3/16  17:42
 **/
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ZipkinServicedApp {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServicedApp.class, args);
    }
}
