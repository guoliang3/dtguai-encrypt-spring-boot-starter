package com.dtguai.encrypt.util.security;

import cn.hutool.crypto.digest.DigestUtil;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import com.dtguai.encrypt.util.ISecurity;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>MD5加密工具类</p>
 *
 * @author guo
 * @date 2019年4月16日14:11:20
 */
@Slf4j
public class Md5Util implements ISecurity {

    /**
     * MD5加密-32位小写
     *
     * @param content  内容
     * @param password 注解中传入的key 可为null或空字符
     * @param config   yml配置类
     * @return String
     */
    @Override
    public String encrypt(String content, String password, EncryptBodyConfig config) {
        return DigestUtil.md5Hex(content);
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
        log.error("MD5消息摘要加密,无法解密");
        throw new EncryptDtguaiException("MD5消息摘要加密,无法解密");
    }
}
