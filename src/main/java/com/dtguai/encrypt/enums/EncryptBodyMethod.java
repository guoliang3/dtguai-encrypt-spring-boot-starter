package com.dtguai.encrypt.enums;

/**
 * <p>加密方式</p>
 *
 * @author guo
 * @date 2021年4月21日16:08:32
 */
public enum EncryptBodyMethod {
    /**
     * MD5
     */
    MD5,

    /**
     * DES
     */
    DES,

    /**
     * AES
     */
    AES,

    /**
     * SHA
     */
    SHA,

    /**
     * RSA
     */
    RSA,

    /**
     * 选择SM2解密方式
     */
    SM2,

    /**
     * 选择SM3解密方式
     */
    SM3,

    /**
     * 选择SM4解密方式
     */
    SM4,
}
