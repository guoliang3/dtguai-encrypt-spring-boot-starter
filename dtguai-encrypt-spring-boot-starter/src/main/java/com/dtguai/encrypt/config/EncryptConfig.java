package com.dtguai.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 加密数据基础配置
 *
 * @author guo
 * @date 2021年8月9日13:46:33
 */
@ConfigurationProperties(prefix = "dtguai.encrypt")
@Configuration
@Data
public class EncryptConfig {

    /**
     * 返回对象中需要加密字段的name
     */
    private String resultName = "result";

}
