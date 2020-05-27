package com.zipkin.demo.serviced.common;


import brave.Tracer;
import com.cloud.demo.common.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
  * @Author JackZhou
  * @Description 统一异常处理类
  * @Date 2019/5/6 17:56
  * @Param 
  * @return 
 **/
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    private static final int CODE = 500;
    private static final String ERROR_MESSAGE = "服务器内部异常";

    @Autowired
    Tracer tracer;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse handleException(Exception e) {
        WebResponse webResponse = new WebResponse(CODE, ERROR_MESSAGE, e);
        if(e instanceof BusinessException){
            handBusinessException((BusinessException)e, webResponse);
        }else if(e instanceof MethodArgumentNotValidException){
            handMethodArgumentNotValidException((MethodArgumentNotValidException)e, webResponse);
        }else if(e instanceof BindException){
            handBindException((BindException)e, webResponse);
        }else{
            log.error("统一捕获服务器内部异常: {}",e.getMessage(), e);
        }
        tracer.currentSpan().tag("error", "业务异常code=" + webResponse.getCode() + ",message：" + webResponse.getMessage());
        return webResponse;
    }

    private void handBusinessException(BusinessException e, WebResponse response){
        log.error("触发业务异常：{}",e.getMessage(), e);
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        if(e.getCause() != null){
            response.setResult(e.getCause());
        }
    }

    private void handMethodArgumentNotValidException(MethodArgumentNotValidException e, WebResponse response){
        String errorMessage = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage = "参数异常：" + error.getField() + "->" + error.getDefaultMessage();
                log.error(errorMessage);
                break;
            }
        }
        log.error("触发参数异常：",errorMessage, e);
        response.setCode(441);
        response.setMessage(errorMessage);
        response.setResult(bindingResult.getAllErrors());
    }

    private void handBindException(BindException e,  WebResponse response){
        String errorMessage = null;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage = "参数异常：" + error.getField() + "->" + error.getDefaultMessage();
                log.error(errorMessage);
                break;
            }
        }
        log.error("触发参数异常：{}",errorMessage, e);
        response.setCode(441);
        response.setMessage(errorMessage);
        response.setResult(bindingResult.getAllErrors());
    }

}
