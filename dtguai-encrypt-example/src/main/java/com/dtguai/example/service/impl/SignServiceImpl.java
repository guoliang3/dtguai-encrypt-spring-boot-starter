package com.dtguai.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.config.SignConfig;
import com.dtguai.example.service.ISignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述
 *
 * @author guo
 * @date 2022/4/22 14:58
 */
@Service
@Slf4j
@AllArgsConstructor
public class SignServiceImpl implements ISignService {

    private final SignConfig signConfig;

    /**
     * 根据json 生成签名
     *
     * @param json json
     * @return String
     */
    @Override
    public String getSign(String json) {
        log.info("json数据为:{}", json);

        Map<String, Object> my = JSON.<Map<String, Object>>parseObject(json, TreeMap.class);
        String newDataJson = JSON.toJSONString(my);
        log.info("1.将json转换为TreeMap,进行重新排序(按key的顺序排)的json:{}", newDataJson);

        StringBuilder paramBuilder = new StringBuilder();
        my.forEach((k, v) -> {
            List<String> ignore = signConfig.getIgnore();
            if (v != null && !ignore.contains(k)) {
                paramBuilder.append(k).append("=").append(v).append("&");
            }
        });
        log.warn("2.循环map拼接数据串把token和sign剔除:{}", paramBuilder);

        String signData = paramBuilder.append("signKey=").append(signConfig.getKey()).toString();
        log.warn("3.顺序拼好后加入sign-key,完整signData:{}", signData);

        String md5Sign = DigestUtils.md5Hex(signData);
        log.warn("4.signStr-md5加密:{}", md5Sign);
        return md5Sign;
    }
}
