package com.dtguai.encrypt;

import com.dtguai.encrypt.advice.DecryptRequestBodyAdvice;
import com.dtguai.encrypt.advice.EncryptResponseBodyAdvice;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.config.HttpConverterConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 配置启动类
 *
 * @author guo
 * @date 2021年3月3日14:44:38
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EncryptBodyConfig.class,
        HttpConverterConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
public @interface EnableDtguaiEncrypt {
}
