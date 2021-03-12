package com.dtguai.encrypt.util.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * <p>DES加密处理工具类</p>
 *
 * @author guo
 * @date 2021年3月11日19:52:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DesEncryptUtil {

    private static final String DES = "DES";

    /**
     * DES加密
     *
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String encrypt(String content, String password) {
        return des(content, password, Cipher.ENCRYPT_MODE);
    }


    /**
     * DES解密
     *
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String decrypt(String content, String password) {
        return des(content, password, Cipher.DECRYPT_MODE);
    }


    /**
     * DES加密/解密公共方法
     *
     * @param content  字符串内容
     * @param password 密钥
     * @param type     加密：{@link Cipher#ENCRYPT_MODE}，解密：{@link Cipher#DECRYPT_MODE}
     */
    private static String des(String content, String password, int type) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

            //算法名称/加密模式/填充方式
            //DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(type, keyFactory.generateSecret(desKey), random);

            return DesEncryptUtil.encryptMode(content, type, cipher);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptMode(String content, int type, Cipher cipher) throws BadPaddingException, IllegalBlockSizeException {

        if (type == Cipher.ENCRYPT_MODE) {

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            return Hex2Util.parseByte2HexStr(cipher.doFinal(byteContent));

        } else {

            byte[] byteContent = Hex2Util.parseHexStr2Byte(content);

            if (null == byteContent) {
                log.error("加解密转换数据为null请注意byteContent:null");
                byteContent = new byte[0];
            }

            return new String(cipher.doFinal(byteContent));
        }


    }
}
