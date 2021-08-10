package com.dtguai.encrypt.util.security;

import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.exception.DecryptDtguaiException;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import com.dtguai.encrypt.util.CheckUtils;
import com.dtguai.encrypt.util.ISecurity;
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
public class AesUtil implements ISecurity {

    private static final String AES = "AES";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;
    private static final int KEY_LENGTH = 16;


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

        if (passwordBytes.length != KEY_LENGTH && passwordBytes.length != KEY_LENGTH << 1) {
            log.error("aes钥匙长度为16或32,passwordBytes.length:{}", passwordBytes.length);
            throw new DecryptDtguaiException("aes钥匙长度为16或32");
        }

        return new SecretKeySpec(passwordBytes, AES);
    }

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

        SecretKey key = getKey(CheckUtils.checkAndGetKey(config.getAesKey(), password, "AES-KEY加密"));

        byte[] iv = new byte[GCM_IV_LENGTH];

        new SecureRandom().nextBytes(iv);

        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
        byte[] ciphertext;
        try {
            Cipher cipher = Cipher.getInstance(config.getAesCipherAlgorithm());
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
     * 解密
     *
     * @param content  内容
     * @param password 注解中传入的key 可为null或空字符
     * @param config   yml配置类
     * @return String
     */
    @Override
    public String decrypt(String content, String password, EncryptBodyConfig config) {

        SecretKey key = getKey(CheckUtils.checkAndGetKey(config.getAesKey(), password, "AES-KEY解密"));

        byte[] decoded = Base64.getDecoder().decode(content);

        byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

        GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);

        byte[] ciphertext;
        try {
            Cipher cipher = Cipher.getInstance(config.getAesCipherAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            ciphertext = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            log.error("aes解密异常,e:{}", e.getMessage());
            throw new DecryptDtguaiException("aes解密异常");
        }
        return new String(ciphertext, StandardCharsets.UTF_8);
    }


}
