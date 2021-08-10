package com.dtguai.encrypt.annotation;

import java.lang.annotation.*;

/**
 * 数字证书
 * @author guo
 * @date 2019年2月14日15:28:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sign {
}
