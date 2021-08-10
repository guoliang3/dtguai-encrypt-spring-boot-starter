package com.dtguai.example.api.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.example.api.form.encrypt.Sm2Form;
import com.dtguai.example.api.form.encrypt.Sm4Form;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
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
     * sm2
     *
     * @param form 测试表单
     * @return ApiResponse
     */
    @ApiOperation(value = "sm2加密解密", notes = "sm2加密解密")
    @PostMapping(value = "/sm2", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.SM2)
    @EncryptBody(value = EncryptBodyMethod.SM2)
    public ApiResponse<User> sm2(@RequestBody Sm2Form form) {
        log.info("sm2解密数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(new User());
    }

    /**
     * sm3
     *
     * @return ApiResponse
     */
    @ApiOperation(value = "sm3加密解密", notes = "sm3加密解密")
    @GetMapping(value = "/sm3", produces = "application/json;charset=UTF-8")
    @EncryptBody(value = EncryptBodyMethod.SM3)
    public ApiResponse<User> sm3() {
        return new ApiResponse<>(new User());
    }

    /**
     * sm4
     *
     * @param form 测试表单
     * @return ApiResponse
     */
    @ApiOperation(value = "sm4加密解密", notes = "sm4加密解密")
    @PostMapping(value = "/sm4", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.SM4)
    @EncryptBody(value = EncryptBodyMethod.SM4)
    public ApiResponse<User> sm4(@RequestBody Sm4Form form) {
        log.info("sm4解密数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(new User());
    }


}
