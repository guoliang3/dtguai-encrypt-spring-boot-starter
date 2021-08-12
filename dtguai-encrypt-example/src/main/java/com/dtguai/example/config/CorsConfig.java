package com.dtguai.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求
 *
 * @author guo
 * @date 2019年1月23日15:53:19
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //可以被跨域的路径
        registry.addMapping("/**")
                //允许的请求header访问，可以自定义设置任意请求头信息，如："dtguai-server-token"
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "token")
                //允许所有的请求域名访问跨域资源可以固定单条/多条  "http://www.dtguai.com"，只有dtguai可以访问跨域资源。
                .allowedOriginPatterns("*")
                //浏览器是否应该同时发送凭据
                .allowCredentials(true)
                //允许请求方法跨域
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }
}