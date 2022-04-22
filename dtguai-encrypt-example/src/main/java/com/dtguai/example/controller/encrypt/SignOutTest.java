package com.dtguai.example.controller.encrypt;


import com.dtguai.encrypt.annotation.SignOut;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import com.dtguai.example.service.ISignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据加签
 *
 * @author guo
 * @date 2022年4月20日10:52:58
 */
@RestController
@RequestMapping("/test")
@Api(value = "signOut", tags = {"signOut"})
@Slf4j
@AllArgsConstructor
public class SignOutTest {

    private final ISignService signService;

    /**
     * 加签 对输出对象进行加签
     *
     * @return ApiResponse
     */
    @ApiOperation(value = "signOut加签", notes = "signOut加签")
    @PostMapping(value = "/signOut")
    @SignOut
    public ApiResponse<User> signOut() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse("2022-04-22 14:41:20");
        log.warn("signOut-date:{}", date);
        return new ApiResponse<>(User
                .builder()
                .name("克隆人cx911")
                .type(1)
                .mobile("13800138000")
                .imei("123456789")
                .createTime(date)
                .build());
    }

    /**
     * sign 数字签名
     * 注意时间问题
     * createTime 2022-04-22 14:41:20
     * JSON.toJSONStringWithDateFormat(x, "yyyy-MM-dd HH:mm:ss")
     * 请根据signOut方法返回的 timestamp 修改入参
     *
     * @param json 测试数据
     * @return ApiResponse
     */
    @ApiOperation(value = "生成数字证书", notes = "通过测试数据生成sign数字证书")
    @PostMapping(value = "/signOut/data", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, defaultValue = "{ " +
            " \"createTime\": \"2022-04-22 14:41:20\", " +
            " \"type\": 1," +
            " \"imei\": \"123456789\"," +
            " \"mobile\": \"13800138000\"," +
            " \"name\": \"克隆人cx911\"," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> signOutData(String json) {
        return new ApiResponse<>(signService.getSign(json));
    }


}
