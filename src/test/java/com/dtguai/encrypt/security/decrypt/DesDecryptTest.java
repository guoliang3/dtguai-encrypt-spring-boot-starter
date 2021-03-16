package com.dtguai.encrypt.security.decrypt;

import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.util.security.DesEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * 解密
 *
 * @author guo
 * @date 2020年3月31日16:17:56
 */
@Slf4j
public class DesDecryptTest {

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

    public static final String DES_KEY = "xiaoFuLoveXiaoQiu";

    public static final String REP = "{\"code\":200,\"msg\":\"成功\",\"result\":[]}";

    public static final String DES_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    @Test
    public void desEncrypt() {

        log.warn("加密前的json数据为:{}", DATA_JSON);
        log.warn("钥匙长度:{}", DES_KEY.getBytes().length);

        String jiami = DesEncryptUtil.encrypt(DATA_JSON, DES_KEY, DES_CIPHER_ALGORITHM);
        log.warn("加密后的数据为:        {}", jiami);

        Map<String, String> m = JSON.<Map<String, String>>parseObject(REP, Map.class);
        m.put("result", jiami);
        log.warn("正常服务器返回的数据为:{}", JSON.toJSONString(m));

        log.warn("获取返回的result数据为:{}", m.get("result"));

        //解密
        String jiemi = DesEncryptUtil.decrypt(jiami, DES_KEY, DES_CIPHER_ALGORITHM);
        log.warn("解密后的json数据为:{}", jiemi);

        Assert.assertNotNull(jiemi);
    }

}
