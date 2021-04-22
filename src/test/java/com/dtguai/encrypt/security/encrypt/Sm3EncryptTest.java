package com.dtguai.encrypt.security.encrypt;


import cn.hutool.crypto.SmUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;


/**
 * 加密
 *
 * @author guo
 * @date 2021年4月22日14:39:11
 */
@Slf4j
public class Sm3EncryptTest {


    public static final String DATA_JSON = "{" +
            "  \"createTime\": \"2021-03-15 10:34:57\"," +
            "  \"id\": 0," +
            "  \"imei\": 11111," +
            "  \"mobile\": 13811889989," +
            "  \"name\": \"克隆人\"," +
            "  \"password\": 123456," +
            "  \"type\": 0," +
            "  \"timestamp\": 1619074512137" +
            "}";

    /**
     * 摘要加密算法SM3 相当于md5
     * 结果为:
     * cdc91cfad08a3c27703553a8a88d419c97ed6f8ec32df203da02ab5ba6c37654
     */
    @Test
    public void sm3() {
        String digestHex = SmUtil.sm3(DATA_JSON);
        log.info("sm3加密后:{}", digestHex);
        System.out.println(System.currentTimeMillis());
        Assert.assertEquals("cdc91cfad08a3c27703553a8a88d419c97ed6f8ec32df203da02ab5ba6c37654", digestHex);
    }


}