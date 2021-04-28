package com.dtguai.encrypt.security.encrypt;


import com.dtguai.encrypt.TestStart;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.security.decrypt.RsaDecryptTest;
import com.dtguai.encrypt.util.security.rsa.InitKey;
import com.dtguai.encrypt.util.security.rsa.RsaUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * 加密
 *
 * @author guo
 * @date 2021年3月16日17:08:38
 */
@SpringBootTest(classes = TestStart.class)
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
public class RsaEncryptTest {

    @Autowired
    private InitKey initKey;

    @Autowired
    private EncryptBodyConfig config;

    public static final String dataJson = "{" +
            "  \"mobile\": 13811889989," +
            "  \"password\": 123456," +
            "  \"timestamp\": " + System.currentTimeMillis() +
            "}";

    /**
     * RSA公钥
     */
    public static final String RSA_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAou6CNXAJ7+WDxz27tjZA6oBpRFjVTOp2uqS9MWaEtVL9CplkxzVuYGhZpzsYqo1PcMa8AkjzAqRg6VM/6Q2P+OpkVDvmnHX9vRdBEWSHSwJvh/CZOmft0MAfWC5v2EJuekUqiS/1cOHo1c8djh4ekyZgngJdoJykOVBYMpwSTfPESJraHQLdg06xVgme1mHCI8wHpmEzXKGUJYjOybeAkoeWnFHq9aKRtpt51UWhOBhDumUnMh53c4y8Cykmy6zLwl0hG6ah/TSxnWOsC+4ElVlOn4VGEmUEtgowzFzjEL8vjUGeKhHBnyyejY5jz37zt1X/Gk4vtA53ZtbwdptISwIDAQAB";

    /**
     * 初始化公私钥
     */
    @Test
    public void rsaKeyInit() {
        Map<String, Object> keyMap = initKey.initKey();
        //公钥
        byte[] publicKey = InitKey.getPublicKey(keyMap);
        //私钥
        byte[] privateKey = InitKey.getPrivateKey(keyMap);

        log.warn("公钥：{}" + Base64.encodeBase64String(publicKey));
        log.warn("私钥：{}" + Base64.encodeBase64String(privateKey));
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
    }

    /**
     * 公钥加密
     */
    @Test
    public void rsaPubEncrypt() {
        log.warn("原始的json数据为:{}", dataJson);
        log.warn("原始的json数据长度为:{}", dataJson.length());
        //公钥加密
        byte[] jiami = RsaUtil.encryptByPublicKey(dataJson.getBytes(), Base64.decodeBase64(RSA_PUB_KEY));
        log.warn("公钥加密数据:{}", Base64.encodeBase64String(jiami));
        log.warn("公钥加密数据长度:{}", jiami.length);
        Assert.assertNotNull(jiami);
    }

    /**
     * 私钥加密
     */
    @Test
    public void rsaPirEncrypt() {
        log.warn("原始的json数据为:{}", dataJson);
        log.warn("原始的json数据长度为:{}", dataJson.length());
        //私钥加密
        String jiami = DecryptBodyMethod.RSA.getISecurity().encrypt(RsaEncryptTest.dataJson, RsaDecryptTest.RSA_PIR_KEY, config);
        log.warn("私钥加密数据:{}", jiami);
        log.warn("私钥加密数据长度:{}", jiami.length());
        Assert.assertNotNull(jiami);
    }

}