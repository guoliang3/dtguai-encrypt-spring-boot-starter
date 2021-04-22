package com.dtguai.encrypt.security.decrypt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.util.Base64;

/**
 * 解密
 *
 * @author guo
 * @date 2021年4月22日14:38:48
 */
@Slf4j
public class Sm2DecryptTest {

    public static final String DATA_JSON = "{" +
            "  \"createTime\": \"2021-03-15 10:34:57\"," +
            "  \"id\": 0," +
            "  \"imei\": 11111," +
            "  \"mobile\": 13811889989," +
            "  \"name\": \"克隆人\"," +
            "  \"password\": 123456," +
            "  \"type\": 0," +
            "  \"timestamp\": " + System.currentTimeMillis() +
            "}";

    /**
     * 使用自定义密钥对加密或解密
     */
    @Test
    public void sm2z() {

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
        String privateKeys = Base64.getEncoder().encodeToString(privateKey);
        String publicKeys = Base64.getEncoder().encodeToString(publicKey);
        log.info("sm2--生成秘钥---privateKeys:{}", privateKeys);
        log.info("sm2--生成秘钥---publicKeys:{}", publicKeys);

        SM2 sm2Pub = SmUtil.sm2(null, publicKeys);
        SM2 sm2Pri = SmUtil.sm2(privateKeys, null);

        // 公钥加密，私钥解密
        String encryptStr = sm2Pub.encryptBcd(DATA_JSON, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2Pri.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        log.info("原数据===== :{}", DATA_JSON);
        log.info("公钥加密sm2 :{}", encryptStr);
        log.info("私钥解密sm2 :{}", decryptStr);
        Assert.assertEquals(DATA_JSON, decryptStr);
    }

}
