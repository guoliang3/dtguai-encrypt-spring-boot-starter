package com.dtguai.example.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.example.form.encrypt.Sm2Form;
import com.dtguai.example.form.encrypt.Sm4Form;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 描述
 *
 * @author guo
 * @date 2021/3/15 20:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "sm", tags = {"sm"})
@Slf4j
public class SmTest {

    /**
     * sm2 加密
     *
     * @param json 测试数据
     * @return ApiResponse
     */
    @ApiOperation(value = "sm2加密", notes = "使用sm2给测试数据加密")
    @ApiOperationSupport(order = 1)
    @PostMapping(value = "/sm2/data")
    @EncryptBody(value = EncryptBodyMethod.SM2)
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, required = true, defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人sm2\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\""
            + ",\"user\":{\"name\":\"克隆人跑火车\"}" +
            " }")
    public ApiResponse<String> sm2Data(String json) {
        log.info("sm2-测试数据加密,原始数据:{}", json);
        return new ApiResponse<>(json);
    }


    /**
     * sm2 解密
     *
     * @param form 测试表单
     * @return ApiResponse
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "sm2解密", notes = "sm2解密")
    @PostMapping(value = "/sm2", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.SM2)
    public ApiResponse<Sm2Form> sm2(@RequestBody Sm2Form form) {
        log.info("sm2解密数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(form);
    }


    /**
     * sm3加密
     *
     * @return ApiResponse
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "sm3加密", notes = "sm3加密")
    @GetMapping(value = "/sm3", produces = "application/json;charset=UTF-8")
    @EncryptBody(value = EncryptBodyMethod.SM3)
    public ApiResponse<User> sm3() {
        return new ApiResponse<>(User
                .builder()
                .name("sm3")
                .imei("1111")
                .password("111")
                .build());
    }

    /**
     * sm4 加密
     *
     * @param json 测试数据
     * @return ApiResponse
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "sm4加密", notes = "使用sm4给测试数据加密")
    @PostMapping(value = "/sm4/data")
    @EncryptBody(value = EncryptBodyMethod.SM4)
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, required = true, defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人sm4\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> sm4Data(String json) {
        log.info("sm4-测试数据加密,原始数据:{}", json);
        return new ApiResponse<>(json);
    }

    /**
     * sm4解密
     *
     * @param form 测试表单
     * @return ApiResponse
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "sm4解密", notes = "sm4解密")
    @PostMapping(value = "/sm4", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.SM4)
    public ApiResponse<Sm4Form> sm4(@RequestBody Sm4Form form) {
        log.info("sm4解密数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(form);
    }


}
