package com.dtguai.encrypt.annotation.decrypt;


import com.dtguai.encrypt.enums.DecryptBodyMethod;

import java.lang.annotation.*;

/**
 * <p>解密含有{@link org.springframework.web.bind.annotation.RequestBody}注解的参数请求数据，可用于整个控制类或者某个控制器上</p>
 *
 * @author guo
 * @date 2021年3月10日11:31:03
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptBody {

    /**
     * 解密方式 默认为aes
     * @return DecryptBodyMethod
     */
    DecryptBodyMethod value() default DecryptBodyMethod.AES;

    String otherKey() default "";

    /**
     * 数据超时时间
     */
    long timeOut() default DecryptBodyMethod.TIME_OUT;

}
