package com.dtguai.encrypt;

import com.dtguai.encrypt.advice.DecryptRequestBodyAdvice;
import com.dtguai.encrypt.advice.EncryptResponseBodyAdvice;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.config.EncryptConfig;
import com.dtguai.encrypt.config.HttpConverterConfig;
import com.dtguai.encrypt.config.SignConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 配置启动类
 *
 * @author guo
 * @date 2021年8月9日15:33:30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EncryptBodyConfig.class,
        EncryptConfig.class,
        SignConfig.class,
        HttpConverterConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
public @interface EnableDtguaiEncrypt {
}
