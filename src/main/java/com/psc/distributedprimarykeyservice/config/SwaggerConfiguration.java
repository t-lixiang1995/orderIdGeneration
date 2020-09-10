package com.psc.distributedprimarykeyservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lixiang
 * @date 2020/9/10 10:51
 * @desc
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    @Order(value = 1)
    public Docket groupRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.psc.distributedprimarykeyservice"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("分布式ID生成服务接口")
                .description("<div style='font-size:14px;color:red;'>分布式ID生成服务接口在接线接口</div>")
                .termsOfServiceUrl("http://www.group.com/")
                //联系人
                .contact("lixiang")
                .version("1.0")
                .build();
    }
}