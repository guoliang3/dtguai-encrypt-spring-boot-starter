package com.dtguai.encrypt.enums;

import com.dtguai.encrypt.util.ISecurity;
import com.dtguai.encrypt.util.security.AesUtil;
import com.dtguai.encrypt.util.security.DesUtil;
import com.dtguai.encrypt.util.security.rsa.RsaUtil;
import com.dtguai.encrypt.util.security.sm.Sm2Util;
import com.dtguai.encrypt.util.security.sm.Sm4Util;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>解密方式</p>
 *
 * @author guo
 * @date 2021年4月21日16:08:37
 */
@AllArgsConstructor
@Getter
public enum DecryptBodyMethod {

    /**
     * 选择DES解密方式
     */
    DES(new DesUtil()),

    /**
     * 选择AES解密方式
     */
    AES(new AesUtil()),

    /**
     * 选择RSA解密方式
     */
    RSA(new RsaUtil()),

    /**
     * 选择SM2解密方式
     */
    SM2(new Sm2Util()),

    /**
     * 选择SM4解密方式
     */
    SM4(new Sm4Util()),
    ;

    ISecurity iSecurity;


    /**
     * 超时 0为默认值 不设置时间
     * TIME_OUT = 60L * 1000L 时间为 60秒
     */
    public static final long TIME_OUT = 0;

}
