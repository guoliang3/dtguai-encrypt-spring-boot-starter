package com.dtguai.encrypt.annotation.decrypt;


import com.dtguai.encrypt.enums.DecryptBodyMethod;

import java.lang.annotation.*;

/**
 * @author guo
 * @date 2019年6月21日17:11:20
 * @see DecryptBody
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DESDecryptBody {

    String otherKey() default "";

    /**
     * 数据超时时间
     */
    long timeOut() default DecryptBodyMethod.TIME_OUT;
}
