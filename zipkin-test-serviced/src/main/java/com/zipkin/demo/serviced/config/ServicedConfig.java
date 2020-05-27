package com.zipkin.demo.serviced.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @ClassName ServicebConfig
 * @Author JackZhou
 * @Date 2020/3/16  17:37
 **/
@Configuration
public class ServicedConfig {

    @Value("${spring.swagger.package:com.zipkin.demo}")
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
        return new ApiInfoBuilder().title("cloud-service-d")
                .description("这是一个简单的 Swagger API 演示")
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().addProperty("hibernate.validator.fail_fast", "true").buildValidatorFactory();
        return validatorFactory.getValidator();
    }

        /**
         * 注册第三方过滤器
         * 功能与spring mvc中通过配置web.xml相同
         * @return
         */
//        @Bean
//        public FilterRegistrationBean thirdFilter(){
//            TestFilter testFilter = new TestFilter();
//            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
//
//            filterRegistrationBean.setFilter(testFilter);
//            List<String > urls = new ArrayList<>();
//            // 匹配所有请求路径
//            urls.add("/*");
//            filterRegistrationBean.setUrlPatterns(urls);
//
//            return filterRegistrationBean;
//        }


//        @Configuration
//        public class WebConfig implements WebMvcConfigurer {
//            @Autowired
//            private TestInterceptor testInterceptor;
//
//            @Override
//            public void addInterceptors(InterceptorRegistry registry){
//                registry.addInterceptor(testInterceptor);
//            }
//        }
}
