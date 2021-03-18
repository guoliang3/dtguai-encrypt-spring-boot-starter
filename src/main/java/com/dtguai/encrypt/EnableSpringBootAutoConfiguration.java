package com.dtguai.encrypt;

import com.dtguai.encrypt.advice.DecryptRequestBodyAdvice;
import com.dtguai.encrypt.advice.EncryptResponseBodyAdvice;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.config.HttpConverterConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述
 *
 * @author guo
 * @date 2021/3/18 15:54
 */

@Configuration
@Import({EncryptBodyConfig.class,
        HttpConverterConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
public class EnableSpringBootAutoConfiguration {
}
