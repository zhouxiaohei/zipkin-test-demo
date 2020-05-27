package com.zipkin.demo.serviced.demo;

import com.cloud.demo.common.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @ClassName TestAspect
 * @Author JackZhou
 * @Date 2020/5/25  10:43
 **/
//@Component
//@Aspect
@Slf4j
public class TestAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}

    @Around("requestMapping()")
    public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("--切面开始--");
        Object result = proceedingJoinPoint.proceed();
        try {
            // 返回值为空的直接跳过
            if(null!= result && result instanceof WebResponse){
                // 结果转换
                WebResponse webResponse = (WebResponse) result;
                log.info("--切面执行结束------{}", webResponse.getCode());
            }
            // 针对这一块，使用try catch ， 保证不会影响程序的正常运行
        }catch (Exception ignored){
            log.error("执行切面报错", ignored);
        }
        return result;
    }
}
