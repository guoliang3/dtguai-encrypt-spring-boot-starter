package com.dtguai.encrypt.enums;

import com.dtguai.encrypt.util.ISecurity;
import com.dtguai.encrypt.util.security.AesUtil;
import com.dtguai.encrypt.util.security.DesUtil;
import com.dtguai.encrypt.util.security.Md5Util;
import com.dtguai.encrypt.util.security.ShaUtil;
import com.dtguai.encrypt.util.security.rsa.RsaUtil;
import com.dtguai.encrypt.util.security.sm.Sm2Util;
import com.dtguai.encrypt.util.security.sm.Sm3Util;
import com.dtguai.encrypt.util.security.sm.Sm4Util;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>加密方式</p>
 *
 * @author guo
 * @date 2021年4月21日16:08:32
 */
@Getter
@AllArgsConstructor
public enum EncryptBodyMethod {
    /**
     * MD5
     */
    MD5(new Md5Util()),

    /**
     * DES
     */
    DES(new DesUtil()),

    /**
     * AES
     */
    AES(new AesUtil()),

    /**
     * SHA
     */
    SHA(new ShaUtil()),

    /**
     * RSA
     */
    RSA(new RsaUtil()),

    /**
     * 选择SM2解密方式
     */
    SM2(new Sm2Util()),

    /**
     * 选择SM3解密方式
     */
    SM3(new Sm3Util()),

    /**
     * 选择SM4解密方式
     */
    SM4(new Sm4Util()),
    ;

    ISecurity iSecurity;

}
