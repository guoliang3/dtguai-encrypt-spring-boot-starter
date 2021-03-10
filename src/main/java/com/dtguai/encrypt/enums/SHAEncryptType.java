package com.dtguai.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>SHA加密类型</p>
 *
 * @author guo
 * @date 2019年4月16日14:10:18
 */
@AllArgsConstructor
public enum SHAEncryptType {

    /**
     * SHA224
     */
    SHA224("sha-224"),
    /**
     * SHA256
     */
    SHA256("sha-256"),
    /**
     * SHA384
     */
    SHA384("sha-384"),
    /**
     * SHA512
     */
    SHA512("sha-512"),
    ;

    @Getter
    @Setter
    String value;


}
