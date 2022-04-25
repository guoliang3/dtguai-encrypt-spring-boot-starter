package com.dtguai.example.controller.encrypt;


import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.enums.SHAEncryptType;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
     * SHA-224 / 256 / 384 / 512 加密测试
 *
 * @author guo
 * @version 1.1.1
 * @date 2022年4月24日17:41:14
 */
@RestController
@RequestMapping("/test")
@Api(value = "sha", tags = {"sha加密"})
@Slf4j
public class ShaTest {

    /**
     * SHA256加密
     *
     * @return ApiResponse
     */
    @ApiOperation(value = "SHA256输出加密", notes = "SHA256输出加密")
    @PostMapping(value = "/SHA256")
    @EncryptBody(value = EncryptBodyMethod.SHA, shaType = SHAEncryptType.SHA256)
    public ApiResponse<User> SHA256() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse("2022-04-22 14:41:20");
        return new ApiResponse<>(User.builder()
                .name("克隆人")
                .createTime(date)
                .imei("11111")
                .mobile("13811788899")
                .build());
    }

    /**
     * SHA224加密
     *
     * @return ApiResponse
     */
    @ApiOperation(value = "SHA224输出加密", notes = "SHA224输出加密")
    @PostMapping(value = "/SHA224")
    @EncryptBody(value = EncryptBodyMethod.SHA, shaType = SHAEncryptType.SHA224)
    public ApiResponse<User> SHA224() {
        return new ApiResponse<>(User.builder()
                .name("克隆人")
                .createTime(new Date())
                .imei("11111")
                .mobile("13811788899")
                .build());
    }

}
