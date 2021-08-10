package com.dtguai.encrypt.util.security.sm;

import cn.hutool.crypto.SmUtil;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import com.dtguai.encrypt.util.ISecurity;
import lombok.extern.slf4j.Slf4j;

/**
 * sm3 加密解密工具类
 *
 * @author guo
 * @date 2021年3月15日18:28:42
 */
@Slf4j
public class Sm3Util implements ISecurity {


    /**
     * 加密
     *
     * @param content  内容
     * @param password 注解中传入的key 可为null或空字符
     * @param config   yml配置类
     * @return String
     */
    @Override
    public String encrypt(String content, String password, EncryptBodyConfig config) {
        return SmUtil.sm3(content);
    }

    /**
     * 解密
     *
     * @param content  内容
     * @param password 注解中传入的key 可为null或空字符
     * @param config   yml配置类
     * @return String
     */
    @Override
    public String decrypt(String content, String password, EncryptBodyConfig config) {
        log.error("SM3消息摘要加密,可以用MD5作为对比理解,无法解密");
        throw new EncryptDtguaiException("SM3消息摘要加密,可以用MD5作为对比理解,无法解密");
    }
}
