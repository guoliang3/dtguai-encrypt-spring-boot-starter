package com.dtguai.example.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.example.form.encrypt.AesForm;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author guo
 * @version 1.1.1
 * @date 2022年4月24日18:14:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "aes", tags = {"aes"})
@Slf4j
public class AesTest {

    /**
     * aes解密
     *
     * @param form 加密对象
     * @return ApiResponse<User>
     */
    @ApiOperation(value = "aes解密", notes = "aes解密")
    @PostMapping(value = "/aes")
    @DecryptBody(value = DecryptBodyMethod.AES)
    public ApiResponse<User> aes(@RequestBody AesForm form) {
        log.info("aes解密数据:{}", JSON.toJSONString(form));
        User user = new User();
        BeanUtils.copyProperties(form, user);
        return new ApiResponse<>(user);
    }


    /**
     * aes加密
     *
     * @param json 测试json数据
     * @return ApiResponse<String>
     */
    @ApiOperation(value = "aes加密", notes = "aes加密")
    @PostMapping(value = "/aes/data")
    @EncryptBody(value = EncryptBodyMethod.AES)
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, defaultValue = "{ " +
            " \"createTime\": \"2022-04-25 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人aes\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> aesData(String json) {
        log.info("aes-测试数据加密,原始数据:{}", json);
        return new ApiResponse<>(json);
    }

}
