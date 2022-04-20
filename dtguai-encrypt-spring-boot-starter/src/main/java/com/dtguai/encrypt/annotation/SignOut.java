package com.dtguai.encrypt.annotation;

import java.lang.annotation.*;

/**
 * Sign加签注解
 * 数字证书输出加签
 *
 * @author guo
 * @date 2022年4月15日16:23:23
 * @version 1.1.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignOut {

    /**
     * 所需要加签的数据对象key
     */
    String resultName() default "";

}
