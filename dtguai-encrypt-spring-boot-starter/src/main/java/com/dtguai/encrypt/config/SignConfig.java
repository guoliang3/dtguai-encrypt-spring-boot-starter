package com.dtguai.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 数字证书配置
 *
 * @author guo
 * @date 2021/3/12 16:55
 */
@ConfigurationProperties(prefix = "dtguai.sign")
@Configuration
@Data
public class SignConfig {

    private String key;

}
