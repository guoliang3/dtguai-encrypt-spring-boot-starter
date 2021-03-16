package com.dtguai.encrypt.security.decrypt;

import com.dtguai.encrypt.security.encrypt.RsaEncryptTest;
import com.dtguai.encrypt.util.security.RsaEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * 解密
 *
 * @author guo
 * @date 2021年3月16日17:06:21
 */
@Slf4j
public class RsaDecryptTest {


    public static final String rep = "{\"code\":200,\"msg\":\"成功\",\"result\":[]}";

    /**
     * RSA私钥
     */
    public static final String RSA_PIR_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCi7oI1cAnv5YPHPbu2NkDqgGlEWNVM6na6pL0xZoS1Uv0KmWTHNW5gaFmnOxiqjU9wxrwCSPMCpGDpUz/pDY/46mRUO+acdf29F0ERZIdLAm+H8Jk6Z+3QwB9YLm/YQm56RSqJL/Vw4ejVzx2OHh6TJmCeAl2gnKQ5UFgynBJN88RImtodAt2DTrFWCZ7WYcIjzAemYTNcoZQliM7Jt4CSh5acUer1opG2m3nVRaE4GEO6ZScyHndzjLwLKSbLrMvCXSEbpqH9NLGdY6wL7gSVWU6fhUYSZQS2CjDMXOMQvy+NQZ4qEcGfLJ6NjmPPfvO3Vf8aTi+0Dndm1vB2m0hLAgMBAAECggEBAJx4ZT+ge0qRpf4/wPd6HtmVvIJQtXeKa79O2gqJI8VnD2+QZIITRA7956t8S3HvB22MzZ18JTRSXGd29ZeA2NT5dKSYah20Cpv/qxNi8bMNgcKRQvYePPsOpotu/SY4lhSCPBlHn3Rq38oFf6KuNjqmzn6wUu4dwHDyQeFIlSOZ/0MdcIzHfkroogVFuo9G7jTXb/HJA84wWC/EG41GqIE0Zu/8u+gkDQsNggt0rU3ntPinudwp9sySmBY/7oK1+IpucM9nA9j7N5MmyXK3lLaWSGD4cGS5m4gy+cUnPVPNFMAhMcAzVFWv9mGTdsOzeGheCoLy9D3+IwgNUC68gmECgYEA3YjJWAWVzI/kspjGKBzT0L1f3t8nju5H14d+xvAGyN/FkD7+NEzkSE0lNCh4gK3YbCQeYKItC9T2VgPWIfkksXbKjKKZ8gMvMM3hCf1y7KUcghXwGNNRcLO+DpzduUQ2g122mPLsvjsOpRX1ByasBD7wQ3rf3XKY+XrXJJ0BY3kCgYEAvEe15j2QDOoMAfxNdlzf5Z8d34JwnsfNYdzpz4zzvAjnXSe6FawabU6jgaJUH8oMSYIIpYjf3HUu0CNTKYRHezz9nXiFgNFwmRmkmjEqDAMIjVkN3rsaP3A7ST24gT7xVcua3BRIITv2KVwYZYWZN5yvjeqXoD3zg9XSYsUntOMCgYBzwvSjgFeky1RQVzDE6TtuCmc8iROcxrrXzz/aKLcC5JvSIninT77CYT2docBGTZGYAM+240fytNf8XojYu94GtfJlxtn28t8H+60qkTwqmKTq/Re3gUU/RU19SU87bn+l6aRvDBHV3fprHawqjnS4y0K1oFG24Bk71Irz4O9G0QKBgCA+x9BeysTrcJMGet3CmjXhQmR9GnyXC32vL/vzz+psO/OgUiZUC1KdHTOecXngSKpuMrzm7C/9gb7zzdJWbUeV8nl9op+lPvt5gM9HjEPyYQyUK+Pxd1VWC1FH2MmJ1hsze5olfxTyB/6dXzBD6TG1C/vqKX2wHnx9qkJwMZSxAoGBAIo9aDm/Y1+I3wjm+rM2Tk43a56y1UdE6EAJ0fFv8cknxPOPnDvUtNFqDEzIbv3QLeGZkZyfgVGHnc4f2PZ//k+3okmxRF08uVAQIX8V5yrvbA2u0DkTW05gfajAoYjHa8PRL1UeSt9YRVC6h3jT3Kc/GMjoSXLMTmOwAFUDM4ep";

    /**
     * 私钥解密
     */
    @Test
    public void aesPubEncrypt() {
        log.warn("原始的json数据为:{}", RsaEncryptTest.dataJson);
        log.warn("原始的json数据长度:{}", RsaEncryptTest.dataJson.length());

        //公钥加密
        byte[] jiami = RsaEncryptUtil.encryptByPublicKey(RsaEncryptTest.dataJson.getBytes(), Base64.decodeBase64(RsaEncryptTest.RSA_PUB_KEY));
        log.warn("公钥加密数据长度为:{}", jiami.length);
        log.warn("公钥加密数据为:{}", Base64.encodeBase64String(jiami));


        //私钥解密
        long startTime = System.currentTimeMillis();
        String jiemi = RsaEncryptUtil.decrypt(Base64.encodeBase64String(jiami), RSA_PIR_KEY);
        long endTime = System.currentTimeMillis();
        System.out.println("私钥解密执行时间：" + (endTime - startTime) + "毫秒");
        System.out.println("私钥解密数据:" + jiemi);
        Assert.assertNotNull(jiemi);
    }

    /**
     * 公钥解密
     */
    @Test
    public void aesPirEncrypt() {
        log.warn("原始的json数据为:{}", RsaEncryptTest.dataJson);
        log.warn("原始的json数据长度为:{}", RsaEncryptTest.dataJson.length());
        //私钥加密
        String jiami = RsaEncryptUtil.encrypt(RsaEncryptTest.dataJson, RsaDecryptTest.RSA_PIR_KEY);
        log.warn("私钥加密数据:{}", jiami);
        log.warn("私钥加密数据长度:{}", jiami.length());

        //公钥解密
        long startTime = System.currentTimeMillis();
        byte[] decode = RsaEncryptUtil.decryptByPublicKey(Base64.decodeBase64(jiami), Base64.decodeBase64(RsaEncryptTest.RSA_PUB_KEY));
        long endTime = System.currentTimeMillis();
        System.out.println("公钥解密执行时间：" + (endTime - startTime) + "毫秒");
        System.out.println("公钥解密后的数据：" + new String(decode));
        Assert.assertNotNull(decode);

    }

}
