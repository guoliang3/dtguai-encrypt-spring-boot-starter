package com.dtguai.encrypt.util.security.sm;

import cn.hutool.crypto.SmUtil;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.util.CheckUtils;
import com.dtguai.encrypt.util.ISecurity;
import lombok.extern.slf4j.Slf4j;

/**
 * sm4 加密解密工具类
 *
 * @author guo
 * @date 2021年3月15日18:28:42
 */
@Slf4j
public class Sm4Util implements ISecurity {


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

        String key = CheckUtils.checkAndGetKey(config.getSm4Key(), password, "RSA-KEY加密");
        return SmUtil.sm4(key.getBytes()).encryptHex(content);

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

        String key = CheckUtils.checkAndGetKey(config.getSm4Key(), password, "SM4-KEY解密");
        return SmUtil.sm4(key.getBytes()).decryptStr(content);

    }
}
