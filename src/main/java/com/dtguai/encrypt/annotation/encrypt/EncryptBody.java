package com.dtguai.encrypt.annotation.encrypt;


import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.enums.SHAEncryptType;

import java.lang.annotation.*;

/**
 * <p>加密{@link org.springframework.web.bind.annotation.ResponseBody}响应数据，可用于整个控制类或者某个控制器上</p>
 *
 * @author guo
 * @date 2019年4月16日14:09:02
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptBody {

    EncryptBodyMethod value() default EncryptBodyMethod.AES;

    String otherKey() default "";

    SHAEncryptType shaType() default SHAEncryptType.SHA256;

    /**
     * 所需要加密的字段
     */
    String encryptMsgName() default "result";

}
