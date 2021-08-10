package com.dtguai.encrypt;

import com.dtguai.encrypt.advice.DecryptRequestBodyAdvice;
import com.dtguai.encrypt.advice.EncryptResponseBodyAdvice;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.config.EncryptConfig;
import com.dtguai.encrypt.config.HttpConverterConfig;
import com.dtguai.encrypt.config.SignConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述
 *
 * @author guo
 * @date 2021年8月9日15:34:48
 */

@Configuration
@Import({EncryptBodyConfig.class,
        EncryptConfig.class,
        SignConfig.class,
        HttpConverterConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
public class EnableSpringBootAutoConfiguration {
}
