package com.dtguai.encrypt.config;

import com.dtguai.encrypt.sign.SignAspect;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

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

    /**
     * 数字证书自定义key
     */
    private String key;

    /**
     * 自定义数字证书 过滤值 默认为"token","sign","dataSecret"
     */
    private List<String> ignore = Arrays.asList(SignAspect.TOKEN_HEADER, SignAspect.SIGN_HEADER, SignAspect.DATA_SECRET_HEADER);

}
