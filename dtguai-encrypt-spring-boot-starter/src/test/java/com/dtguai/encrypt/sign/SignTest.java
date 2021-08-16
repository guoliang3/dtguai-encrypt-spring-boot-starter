package com.dtguai.encrypt.sign;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * 加密
 *
 * @author guo
 * @date 2021年3月16日17:08:44
 */
@Slf4j
public class SignTest {


    public static final String rsa = "{" +
            "  \"id\": 1," +
            "  \"mobile\": 18600231871," +
            "  \"password\": 123456," +
            "  \"timestamp\": 1561529899962" +
            //"  \"timestamp\": " + System.currentTimeMillis() +
            "}";

    public static final String signKey = "qyxVsFzp";

    @Test
    public void sign() {
        log.warn("json数据为:{}", rsa);
        Map<String, Object> my = JSON.<Map<String, Object>>parseObject(rsa, TreeMap.class);
        String newDataJson = JSON.toJSONString(my);
        log.warn("添加了时间戳,并且排好序(按key的顺序排)的json:{}", newDataJson);

        StringBuilder paramBuilder = new StringBuilder();
        my.forEach((k, v) -> {
            if (v != null && !k.equals("sign") && !k.equals("token")) {
                paramBuilder.append(k).append("=").append(v).append("&");
            }
        });
        log.warn("循环map拼接数据串把token和sign剔除:{}", paramBuilder);
        String signData = paramBuilder.append("signKey=").append(signKey).toString();
        log.warn("最后加入signkey:                  {}", signData);
        String md5Sign = DigestUtils.md5Hex(signData);
        log.warn("signStr-md5加密:{}", md5Sign);
        Assert.assertNotNull(md5Sign);
    }

}
