package com.dtguai.encrypt.util.security.sm;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.util.CheckUtils;
import com.dtguai.encrypt.util.ISecurity;
import lombok.extern.slf4j.Slf4j;

/**
 * sm2 加密解密工具类
 *
 * @author guo
 * @date 2021年3月15日18:28:42
 */
@Slf4j
public class Sm2Util implements ISecurity {


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
        String key = CheckUtils.checkAndGetKey(config.getSm2PubKey(), password, "SM2-KEY-对方公钥-加密");
        return SmUtil.sm2(null, key).encryptBcd(content, KeyType.PublicKey);
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
        String key = CheckUtils.checkAndGetKey(config.getSm2PirKey(), password, "SM2-KEY解密");
        return StrUtil.utf8Str(
                SmUtil.sm2(key, null).decryptFromBcd(content, KeyType.PrivateKey)
        );
    }
}
