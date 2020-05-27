package com.zipkin.demo.serviced.common;

import brave.Tracer;
import com.cloud.demo.common.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName BusinessRequestAspect
 * @Author JackZhou
 * @Date 2020/5/25  16:51
 **/

@Component
@Aspect
@Slf4j
public class BusinessRequestAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}

    @Autowired
    Tracer tracer;

    // 针对ControllerAdvice 无效，被抛弃
    @Around("requestMapping()")
    public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        try {
            if(null!= result && result instanceof WebResponse){
                WebResponse webResponse = (WebResponse) result;
                if(webResponse.getCode() != 200){
                    tracer.currentSpan().tag("error", "业务异常code=" + webResponse.getCode() + ",message：" + webResponse.getMessage());
                }
            }
        }catch (Exception ignored){
            log.error("业务请求切面执行报错", ignored);
        }
        return result;
    }
}
