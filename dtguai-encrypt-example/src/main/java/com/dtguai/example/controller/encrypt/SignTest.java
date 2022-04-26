package com.dtguai.example.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.Sign;
import com.dtguai.example.form.encrypt.SignForm;
import com.dtguai.example.response.ApiResponse;
import com.dtguai.example.service.ISignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验签
 *
 * @author guo
 * @date 2021/3/15 20:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "sign", tags = {"sign"})
@Slf4j
@AllArgsConstructor
public class SignTest {

    private final ISignService signService;

    /**
     * 验签
     *
     * @param form 表单
     * @return ApiResponse
     */
    @ApiOperation(value = "sign数字证书", notes = "sign数字证书")
    @PostMapping(value = "/sign")
    @Sign
    public ApiResponse<String> sign(@RequestBody SignForm form) {
        log.info("sign数据:{}", JSON.toJSONStringWithDateFormat(form, "yyyy-MM-dd HH:mm:ss"));
        return new ApiResponse<>("验签成功");
    }

    /**
     * sign 数字签名
     * 注意时间问题
     * createTime 2021-08-13 09:47:49
     * JSON.toJSONStringWithDateFormat(x, "yyyy-MM-dd HH:mm:ss")
     *
     * @param json 测试数据
     * @return ApiResponse
     */
    @ApiOperation(value = "生成数字证书-静态", notes = "timestamp可指定")
    @PostMapping(value = "/sign/data")
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, required = true, defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人cx\"," +
            " \"password\": \"123456\"," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> signData(String json) {
        return new ApiResponse<>(signService.getSign(json));
    }


}
