package com.dtguai.encrypt.enums;

/**
 * <p>解密方式</p>
 *
 * @author guo
 * @date 2019年4月16日14:09:02
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
    ;
    /**
     * 超时 0为默认值 不设置时间
     * TIME_OUT = 60L * 1000L 时间为 60秒
     */
    public static final long TIME_OUT = 0;


}
