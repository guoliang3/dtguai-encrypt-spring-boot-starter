package com.dtguai.encrypt.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author guo
 * @date 2020年3月31日17:15:57
 */
@SpringBootConfiguration
@EnableSwagger2WebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("ok").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("secret")
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                // 加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(io.swagger.annotations.ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                //.globalOperationParameters(setHeaderToken())
                .securitySchemes(Collections.singletonList(security()))
                .securityContexts(Collections.singletonList(securityContext()));
    }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("dtguai-encrypt-spring-boot-starter")
                .description("大头怪-加密解密测试")
                .termsOfServiceUrl("https://gitee.com/gouliang/dtguai-encrypt-spring-boot-starter")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("1.0")
                .build();
    }

    @Bean
    SecurityScheme security() {
        return new ApiKey("token", "token", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/test.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("token", authorizationScopes));
    }

}