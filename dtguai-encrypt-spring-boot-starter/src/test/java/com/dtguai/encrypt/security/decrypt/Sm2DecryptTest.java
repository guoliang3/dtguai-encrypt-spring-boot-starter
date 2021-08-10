package com.dtguai.encrypt.security.decrypt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.dtguai.encrypt.TestStart;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.security.encrypt.RsaEncryptTest;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.KeyPair;
import java.util.Base64;

/**
 * 解密
 *
 * @author guo
 * @date 2021年4月22日14:38:48
 */
@SpringBootTest(classes = TestStart.class)
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
public class Sm2DecryptTest {

    @Autowired
    private EncryptBodyConfig config;

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

        log.info("原数据===== :{}", DATA_JSON);
        //加密
        String jiami = EncryptBodyMethod.SM2.getISecurity().encrypt(DATA_JSON, publicKeys, config);
        log.info("公钥加密sm2 :{}", jiami);
        //解密
        String jiemi = EncryptBodyMethod.SM2.getISecurity().decrypt(jiami, privateKeys, config);
        log.info("私钥解密sm2 :{}", jiemi);
        Assert.assertEquals(DATA_JSON, jiemi);
    }

}
