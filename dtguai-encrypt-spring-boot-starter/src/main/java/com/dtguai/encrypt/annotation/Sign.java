package com.dtguai.encrypt.annotation;

import java.lang.annotation.*;

/**
 * 数字证书
 * @author guo
 * @date 2022年4月19日18:03:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sign {

    /**
     * 数字证书自定义key
     */
     String key() default "";


}
