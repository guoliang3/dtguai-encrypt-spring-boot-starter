package com.dtguai.example.api.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.example.api.form.encrypt.AesForm;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class AesTest {


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


}