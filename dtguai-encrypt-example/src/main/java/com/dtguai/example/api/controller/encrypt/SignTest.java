package com.dtguai.example.api.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.Sign;
import com.dtguai.example.api.form.encrypt.SignForm;
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
@Api(value = "sign", tags = {"sign"})
@Slf4j
public class SignTest {


    /**
     * aes加密解密
     *
     * @param form 加密对象
     * @return ResponseEntity
     */
    @ApiOperation(value = "sign数字证书", notes = "sign数字证书")
    @PostMapping(value = "/sign")
    @Sign
    public ApiResponse<User> sign(@RequestBody SignForm form) {
        log.info("sign数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(new User());
    }


}
