package com.dtguai.encrypt.security.encrypt;


import com.dtguai.encrypt.util.security.AesEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * 加密
 *
 * @author guo
 * @date 2019年6月25日17:26:17
 */
@Slf4j
public class AesEncryptTest {

    public static final String dataJson = "{" +
            "  \"createTime\": \"2021-03-15 10:34:57\"," +
            "  \"id\": 0," +
            "  \"imei\": 11111," +
            "  \"mobile\": 18600231871," +
            "  \"name\": \"陈雄\"," +
            "  \"password\": 123456," +
            "  \"type\": 0" +
            "}";

    public static final String aesKey = "xiaoFuLoveXiaoQi";

    public static final String AES_CIPHER_ALGORITHM= "AES/GCM/NoPadding";

    @Test
    public void aesEncrypt() {
        log.warn("加密前的json数据为:{}", dataJson);
        String jiami = AesEncryptUtil.encrypt(dataJson, aesKey,AES_CIPHER_ALGORITHM);
        log.warn("加密后的数据为:{}", jiami);
        Assert.assertNotNull(jiami);
    }

}
