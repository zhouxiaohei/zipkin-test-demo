package com.zipkin.demo.serverb.feign.hystrix;

import com.cloud.demo.common.WebResponse;
import com.cloud.demo.common.bean.Person;
import com.zipkin.demo.serverb.feign.ServiceaFallbackService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceaFallbackFactory
 * @Author JackZhou
 * @Date 2020/3/18  16:59
 **/
@Component
@Slf4j
public class ServiceaFallbackFactory implements FallbackFactory<ServiceaFallbackService> {

    @Override
    public ServiceaFallbackService create(Throwable throwable) {
        return new ServiceaFallbackService() {
            @Override
            public WebResponse<Person> getById(String id) {
                log.info("出现异常打印：", throwable);
                return null;
            }

            @Override
            public WebResponse<Person> save(Person person) {
                return null;
            }

            @Override
            public WebResponse<Person> delete(String id) {
                return null;
            }
        };
    }
}
