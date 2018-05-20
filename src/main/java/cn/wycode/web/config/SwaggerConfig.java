package cn.wycode.web.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger相关配置
 * Created by wayne on 2017/10/23.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket generateDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(paths()) // and by paths
                .build()
                .apiInfo(apiInfo());
    }


    //Here is an example where we select any api that matches one of these paths
    private Predicate<String> paths() {
        return input -> input.matches(".api.*");
    }


    private ApiInfo apiInfo() {
        return new ApiInfo("王郁的API文档",
                "提供App测试及独立APP研发",
                "2.0",
                "http://wycode.cn",
                new Contact("王郁","wycode.cn","wangyu0503@gmail.com"),
                "wycode.cn All Right Reserved",
                "http://wycode.cn",
                new ArrayList<>());
    }
}
