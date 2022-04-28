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
 * @version 1.1.3
 * @date 2022年4月28日09:17:19
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

    /**
     * 所需要加签的数据对象key
     */
    private String resultName = "result";

    /**
     * 返回对象加签字段,默认sign
     */
    private String signName = "sign";
}
