package com.zipkin.demo.serviced.controller;

import com.cloud.demo.common.WebResponse;
import com.zipkin.demo.serviced.common.BusinessException;
import com.zipkin.demo.serviced.demo.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName BusinessExDemoController
 * @Author JackZhou
 * @Date 2020/3/18  17:04
 **/
@RestController
@RequestMapping("/demo/serviced/exDemo/")
@Slf4j
@Api(tags = "测试zipkin业务异常收集")
public class BusinessExDemoController {

    @ApiOperation(value = "根据id查询")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebResponse<Person> getById(@PathVariable("id") String id){
        throw new BusinessException(451,"实体不存在");
    }

    @ApiOperation("保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public WebResponse<Person> save(@Valid @RequestBody Person person){
        log.info("保存成功");
        return new WebResponse<>();
    }

}
