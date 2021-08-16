package com.dtguai.example.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.Sign;
import com.dtguai.encrypt.config.SignConfig;
import com.dtguai.example.form.encrypt.SignForm;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述
 *
 * @author guo
 * @date 2021/3/15 20:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "sign", tags = {"sign"})
@Slf4j
@AllArgsConstructor
public class SignTest {

    private final SignConfig signConfig;

    /**
     * aes加密解密
     *
     * @param form 加密对象
     * @return ApiResponse
     */
    @ApiOperation(value = "sign数字证书", notes = "sign数字证书")
    @PostMapping(value = "/sign")
    @Sign
    public ApiResponse<User> sign(@RequestBody SignForm form) {
        log.info("sign数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(new User());
    }

    /**
     * sign 数字签名
     * 注意时间问题
     * createTime 2021-08-13 09:47:49
     * JSON.toJSONStringWithDateFormat(x, "yyyy-MM-dd HH:mm:ss")
     *
     * @param json 测试数据
     * @return ApiResponse
     */
    @ApiOperation(value = "生成数字证书", notes = "通过测试数据生成sign数字证书")
    @PostMapping(value = "/sign/data")
    @ApiImplicitParam(name = "json", value = "json测试数据", defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人cx\"," +
            " \"password\": \"123456\"," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> signData(String json) {

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
        return new ApiResponse<>(md5Sign);
    }


}
