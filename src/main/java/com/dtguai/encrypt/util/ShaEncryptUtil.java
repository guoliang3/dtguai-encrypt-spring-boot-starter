

package com.dtguai.encrypt.util;


import com.dtguai.encrypt.enums.SHAEncryptType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>SHA加密工具类</p>
 *
 * @author guo
 * @date  2019年4月16日14:11:20
 */
public class ShaEncryptUtil {


    /**
     * SHA加密公共方法
     *
     * @param string 目标字符串
     * @param type   加密类型 {@link SHAEncryptType}
     */
    public static String encrypt(String string, SHAEncryptType type) {
        if (string == null || "".equals(string.trim())) {
            return "";
        }
        if (type == null) {
            type = SHAEncryptType.SHA256;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance(type.getValue());
            byte[] bytes = md5.digest((string).getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
