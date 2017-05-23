package com.lujiahao.rest.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Swagger会根据这个配置类的具体实现来生成我们相应的api文档，比如：过滤指定接口
 *
 * @author lujiahao
 * @version 1.0
 * @date 2017-05-16 12:35
 */
@Configuration
@EnableSwagger
@EnableWebMvc
@ComponentScan("com.lujiahao.rest.controller")
public class SwaggerConfig {
    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*?");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring MVC Demo",
                "Spring MVC 简单示例，依赖于 MySQL 数据库，只有简单的登陆功能。",
                "开发者: Albert Chen",
                "albert.chen.dao@gmail.com",
                "MIT License",
                "/LICENSE");
        return apiInfo;
    }
}
