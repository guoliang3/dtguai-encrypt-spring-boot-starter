package com.dtguai.encrypt.security.decrypt;

import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.TestStart;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * 解密
 *
 * @author guo
 * @date 2020年3月31日16:17:56
 */
@SpringBootTest(classes = TestStart.class)
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
public class DesDecryptTest {

    @Autowired
    private EncryptBodyConfig config;

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

    @Test
    public void desEncrypt() {

        log.warn("加密前的json数据为:{}", DATA_JSON);
        log.warn("钥匙长度:{}", DES_KEY.getBytes().length);

        String jiami = EncryptBodyMethod.DES.getISecurity().encrypt(DATA_JSON, DES_KEY, config);
        log.warn("加密后的数据为:        {}", jiami);

        Map<String, String> m = JSON.<Map<String, String>>parseObject(REP, Map.class);
        m.put("result", jiami);
        log.warn("正常服务器返回的数据为:{}", JSON.toJSONString(m));

        log.warn("获取返回的result数据为:{}", m.get("result"));

        //解密
        String jiemi = EncryptBodyMethod.DES.getISecurity().decrypt(jiami, DES_KEY, config);
        log.warn("解密后的json数据为:{}", jiemi);

        Assert.assertEquals(DATA_JSON, jiemi);
    }

}
