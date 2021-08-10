package com.dtguai.encrypt.util;

import com.dtguai.encrypt.config.EncryptBodyConfig;

/**
 * 加密解密接口
 *
 * @author guoliang
 * @date 2021年4月27日21:40:21
 */
public interface ISecurity {

    /**
     * 加密
     *
     * @param content  内容
     * @param password 注解中传入的key 可为null或空字符
     * @param config   yml配置类
     * @return String
     */
    String encrypt(String content, String password, EncryptBodyConfig config);

    /**
     * 解密
     *
     * @param content  内容
     * @param password 注解中传入的key 可为null或空字符
     * @param config   yml配置类
     * @return String
     */
    String decrypt(String content, String password, EncryptBodyConfig config);
}
