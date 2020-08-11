package com.czg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: shoppingmall-dev
 * @description: 开启Swagger2配置
 *               访问路径(原生)：http://localhost:8088/swagger-ui.html
 *               bootstrap-ui(插件)：http://localhost:8088/doc.html
 * @author: czg
 * @create: 2020-07-30 15:16
 */
@Configuration //告诉Spring这是个配置文件
@EnableSwagger2 //开启swagger配置
public class Swagger2 {

    //开启swagger核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //指定api类型为swagger2.0
                .apiInfo(apiInfo())                    //用于指定API文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.czg.controller")) //扫描指定要生成文档的包
                .paths(PathSelectors.any()) //com.czg.controller下的所有的controller
                .build();
    }

    /**
     * swagger配置信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("GShopping 电商平台接口")   //API文档页标题
                .contact(new Contact("czg","https://www.cnblogs.com/czgxxwz/","xxxx@163.com"))          //联系方式
                .description("专为GShopping 提供的API文档")     //详细信息
                .version("V0.1")                              //版本号
                .termsOfServiceUrl("https://www.czg.com")     //网址
                .build();
    }
}