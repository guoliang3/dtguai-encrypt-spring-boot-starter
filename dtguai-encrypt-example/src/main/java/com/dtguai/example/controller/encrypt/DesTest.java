package com.dtguai.example.controller.encrypt;

import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.example.form.encrypt.DesForm;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 描述
 *
 * @author guo
 * @date 2021/3/15 20:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "des", tags = {"des"})
@Slf4j
public class DesTest {


    /**
     * des加密解密
     *
     * @param form 加密对象
     * @return ResponseEntity
     */
    @ApiOperation(value = "des加密解密", notes = "des加密解密")
    @PostMapping(value = "/des", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.DES)
    @EncryptBody(value = EncryptBodyMethod.DES)
    public ApiResponse<User> des(@RequestBody DesForm form) {
        log.info("des解密数据:{}", JSON.toJSONString(form));
        return new ApiResponse<>(User.builder()
                .name("克隆人")
                .createTime(new Date())
                .imei("11111")
                .mobile("13811788899")
                .build());
    }

    /**
     * des加密解密
     *
     * @param json 测试json数据
     * @return ApiResponse
     */
    @ApiOperation(value = "des测试数据加密", notes = "使用des给测试数据加密")
    @PostMapping(value = "/des/data")
    @EncryptBody(value = EncryptBodyMethod.DES)
    @ApiImplicitParam(name = "json", value = "json测试数据", defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人des\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> desData(String json) {
        log.info("des-测试数据加密,原始数据:{}", json);
        return new ApiResponse<>(json);
    }

}
