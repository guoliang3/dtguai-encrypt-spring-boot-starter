package com.dtguai.encrypt.util.security.rsa;

import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 初始化rsa key
 *
 * @author guo
 * @date 2021/4/28 11:40
 */
@AllArgsConstructor
@Slf4j
@Component
public class InitKey {


    private final EncryptBodyConfig config;

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "pub";

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY = "pri";

    /**
     * 非对称密钥算法
     */
    private static final byte[] DEF_RSA_KEY = "xiaoFuLoveXiaoQiu".getBytes();

    /**
     * 初始化密钥对
     *
     * @return Map 密钥初始化
     */
    public Map<String, Object> initKey() {
        //实例化密钥生成器
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(RsaUtil.KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            log.error("rsa初始化密钥对异常", e);
            throw new EncryptDtguaiException("rsa初始化密钥对异常");
        }
        //初始化密钥生成器
        SecureRandom secureRandom = new SecureRandom(
                Optional.ofNullable(config.getRsaKey())
                        .map(String::getBytes)
                        .orElse(DEF_RSA_KEY)
        );
        keyPairGenerator.initialize(RsaUtil.KEY_SIZE, secureRandom);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //甲方私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //将密钥存储在map中
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;

    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥map
     * @return byte[] 私钥
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥map
     * @return byte[] 公钥
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }
}
