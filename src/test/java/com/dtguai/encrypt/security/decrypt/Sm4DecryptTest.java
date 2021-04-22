package com.dtguai.encrypt.security.decrypt;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;


/**
 * 解密
 *
 * @author guo
 * @date 2021年4月22日14:38:53
 */
@Slf4j
public class Sm4DecryptTest {

    public static final String DATA_JSON = "{" +
            "  \"createTime\": \"2021-03-15 10:34:57\"," +
            "  \"id\": 0," +
            "  \"imei\": 11111," +
            "  \"mobile\": 13811889989," +
            "  \"name\": \"克隆人\"," +
            "  \"password\": 123456," +
            "  \"type\": 0," +
            "  \"timestamp\": " + System.currentTimeMillis() +
            "}";

    public static final String SM4_KEY = "xiaoFuLoveXiaoQi";

    public static final String SM4_CIPHER_ALGORITHM = "SM4/ECB/PKCS5Padding";

    /**
     * 对称加密SM4
     */
    @Test
    public void sm4() {
        SymmetricCrypto sm4 = SmUtil.sm4(SM4_KEY.getBytes());
        String encryptHex = sm4.encryptHex(DATA_JSON);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        log.info("sm4--encryptHex:{}", encryptHex);
        log.info("sm4--decryptStr:{}", decryptStr);
        Assert.assertEquals(DATA_JSON, decryptStr);
    }

    /**
     * 对称加密SM4 填充
     */
    @Test
    public void sm41() {
        SymmetricCrypto sm4 = new SymmetricCrypto(SM4_CIPHER_ALGORITHM, SM4_KEY.getBytes());
        String encryptHex = sm4.encryptHex(DATA_JSON);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        log.info("sm41-encryptHex:{}", encryptHex);
        log.info("sm41-decryptStr:{}", decryptStr);
        Assert.assertEquals(DATA_JSON, decryptStr);
    }

}
