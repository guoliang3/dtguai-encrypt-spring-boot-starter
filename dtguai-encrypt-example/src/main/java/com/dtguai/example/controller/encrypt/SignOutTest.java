package com.dtguai.example.controller.encrypt;


import com.dtguai.encrypt.annotation.SignOut;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
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
                .name("克隆人cx911-signOut加签")
                .type(1)
                .mobile("13800138000")
                .imei("123456789")
                .createTime(date)
                .build());
    }

    /**
     * sign 加签->动态timestamp
     * 每次会覆盖timestamp 请注意
     *
     * @param json 测试数据
     * @return ApiResponse
     */
    @ApiOperation(value = "动态加签", notes = "动态加签")
    @PostMapping(value = "/signOut/dynamic", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, defaultValue = "{ " +
            " \"createTime\": \"2022-04-22 14:41:20\", " +
            " \"type\": 1," +
            " \"imei\": \"123456789\"," +
            " \"mobile\": \"13800138000\"," +
            " \"name\": \"克隆人cx911\"," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    @SignOut
    public ApiResponse<String> dynamic(String json) {
        return new ApiResponse<>(json);
    }

}
