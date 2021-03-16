package com.dtguai.encrypt.util.security;

import com.dtguai.encrypt.exception.DecryptDtguaiException;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/**
 * <p>AES加密处理工具类</p>
 *
 * @author guo
 * @date 2021年3月15日18:28:42
 */
@Slf4j
@UtilityClass
public class AesEncryptUtil {

    private static final String AES = "AES";

    private static final int GCM_IV_LENGTH = 12;

    private static final int GCM_TAG_LENGTH = 16;


    /**
     * AES加密
     *
     * @param content         字符串内容
     * @param password        密钥
     * @param cipherAlgorithm 加密及填充方式
     */
    public static String encrypt(String content, String password, String cipherAlgorithm) {

        SecretKey key = getKey(password);

        byte[] iv = new byte[GCM_IV_LENGTH];

        new SecureRandom().nextBytes(iv);

        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
        byte[] ciphertext;
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            ciphertext = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            log.error("aes加密异常,e:{}", e.getMessage());
            throw new EncryptDtguaiException("aes加密异常");
        }
        byte[] encrypted = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, encrypted, 0, iv.length);
        System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

        return Base64.getEncoder().encodeToString(encrypted);
    }


    /**
     * AES解密
     *
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String decrypt(String content, String password, String cipherAlgorithm) {

        SecretKey key = getKey(password);

        byte[] decoded = Base64.getDecoder().decode(content);

        byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);

        byte[] ciphertext;
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            ciphertext = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            log.error("aes解密异常,e:{}", e.getMessage());
            throw new DecryptDtguaiException("aes解密异常");
        }
        return new String(ciphertext, StandardCharsets.UTF_8);
    }

    /**
     * 根据 pwd 返回key
     *
     * @param password 密码
     * @return key
     */
    public static SecretKey getKey(String password) {

        byte[] passwordBytes = Optional.ofNullable(password)
                .map(String::getBytes)
                .orElseThrow(() -> new DecryptDtguaiException("aes加解密getKey异常password:{}" + password));

        if (passwordBytes.length != 16 && passwordBytes.length != 32) {
            log.error("aes钥匙长度为16或32,passwordBytes.length:{}", passwordBytes.length);
            throw new DecryptDtguaiException("aes钥匙长度为16或32");
        }

        return new SecretKeySpec(passwordBytes, AES);
    }

}
