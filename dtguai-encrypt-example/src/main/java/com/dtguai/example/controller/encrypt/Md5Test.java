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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述
 *
 * @author guo
 * @date 2022年4月24日17:41:14
 * @version 1.1.1
 */
@RestController
@RequestMapping("/test")
@Api(value = "md5", tags = {"md5加密"})
@Slf4j
public class Md5Test {

    /**
     * md5输出加密-一般很少用
     *
     * @return ApiResponse
     */
    @ApiOperation(value = "md5输出加密", notes = "md5输出加密-一般很少用")
    @PostMapping(value = "/md5")
    @EncryptBody(value = EncryptBodyMethod.MD5)
    public ApiResponse<User> md5() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse("2022-04-22 14:41:20");
        return new ApiResponse<>(User.builder()
                .name("克隆人")
                .createTime(date)
                .imei("11111")
                .mobile("13811788899")
                .build());
    }

}
