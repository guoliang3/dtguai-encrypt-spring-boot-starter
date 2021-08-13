package com.dtguai.example.api.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.example.api.form.encrypt.AesForm;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author guo
 * @date 2021/3/15 20:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "aes", tags = {"aes"})
@Slf4j
@AllArgsConstructor
public class AesTest {

    private final EncryptBodyConfig config;


    /**
     * aes加密解密
     *
     * @param form 加密对象
     * @return ResponseEntity
     */
    @ApiOperation(value = "aes加密解密", notes = "aes加密解密")
    @PostMapping(value = "/aes")
    @DecryptBody(value = DecryptBodyMethod.AES)
    @EncryptBody(value = EncryptBodyMethod.AES)
    public ApiResponse<User> aes(@RequestBody AesForm form) {
        log.info("aes解密数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(new User());
    }


    /**
     * aes加密解密
     *
     * @param json 测试json数据
     * @return ResponseEntity
     */
    @ApiOperation(value = "aes加密", notes = "使用aes给测试数据加密")
    @PostMapping(value = "/aes/data")
    @EncryptBody(value = EncryptBodyMethod.AES)
    @ApiImplicitParam(name = "json", value = "json测试数据", defaultValue = "{ " +
            " \"createTime\": \"2021-8-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人aes\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> aesData(String json) {
        System.out.println("------------" + json);
        log.info("aes-测试数据加密,原始数据:{}", json);
        String jiami = EncryptBodyMethod.AES.getISecurity().encrypt(json, config.getAesKey(), config);
        log.warn("加密后的数据为:        {}", jiami);
        return new ApiResponse<>(json);
    }

}
