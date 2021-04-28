package com.dtguai.encrypt.security.encrypt;


import com.dtguai.encrypt.enums.EncryptBodyMethod;
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
     * be87ccd9062f0319502bdefa873bd203380cbab012380f17f94559d041eaa55e
     */
    @Test
    public void sm3() {
        log.info("原数据===== :{}", DATA_JSON);
        //加密
        String jiami = EncryptBodyMethod.SM3.getISecurity().encrypt(DATA_JSON, null, null);
        log.info("sm3加密后 :{}", jiami);
        Assert.assertEquals("be87ccd9062f0319502bdefa873bd203380cbab012380f17f94559d041eaa55e", jiami);
    }


}