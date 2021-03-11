package com.dtguai.encrypt.enums;

/**
 * <p>解密方式</p>
 *
 * @author guo
 * @date 2021年3月10日11:24:57
 */
public enum DecryptBodyMethod {

    /**
     * 选择DES解密方式
     */
    DES,

    /**
     * 选择AES解密方式
     */
    AES,

    /**
     * 选择RSA解密方式
     */
    RSA,

    /**
     * 选择SM1解密方式
     */
    SM1,

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
    ;
    /**
     * 超时 0为默认值 不设置时间
     * TIME_OUT = 60L * 1000L 时间为 60秒
     */
    public static final long TIME_OUT = 0;


}
