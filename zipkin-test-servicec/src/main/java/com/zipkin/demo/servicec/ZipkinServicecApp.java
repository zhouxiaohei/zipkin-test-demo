package com.zipkin.demo.servicec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName ServicecApp
 * @Author JackZhou
 * @Date 2020/3/16  17:40
 **/
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ZipkinServicecApp {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServicecApp.class, args);
    }
}
