package com.dtguai.encrypt.annotation.encrypt;


import com.dtguai.encrypt.enums.SHAEncryptType;

import java.lang.annotation.*;

/**
 * @author guo
 * @date 2019年4月16日14:09:02
 * @see EncryptBody
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SHAEncryptBody {

    SHAEncryptType value() default SHAEncryptType.SHA256;

}
