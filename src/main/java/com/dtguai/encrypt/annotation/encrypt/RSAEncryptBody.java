package com.dtguai.encrypt.annotation.encrypt;

import java.lang.annotation.*;

/**
 * 非对称加密
 *
 * @author guo
 * @date 2019年4月16日14:09:02
 * @see EncryptBody
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RSAEncryptBody {

    String otherKey() default "";

}
