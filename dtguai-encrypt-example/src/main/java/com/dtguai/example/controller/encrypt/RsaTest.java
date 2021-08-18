package com.dtguai.example.controller.encrypt;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.util.security.rsa.InitKey;
import com.dtguai.encrypt.util.security.rsa.RsaUtil;
import com.dtguai.example.form.encrypt.RsaForm;
import com.dtguai.example.model.User;
import com.dtguai.example.response.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @author guo
 * @date 2021/3/15 20:07
 */
@RestController
@RequestMapping("/test")
@Api(value = "rsa", tags = {"rsa"})
@Slf4j
@AllArgsConstructor
public class RsaTest {

    private final EncryptBodyConfig config;

    private final InitKey initKey;

    /**
     * rsa私钥解密+rsa私钥加密
     *
     * @param form 加密对象
     * @return ApiResponse
     */
    @ApiOperation(value = "rsa私钥解密+私钥加密输出", notes = "rsa私钥解密+私钥加密输出")
    @PostMapping(value = "/rsa", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.RSA)
    @EncryptBody(value = EncryptBodyMethod.RSA)
    public ApiResponse<User> rsa(@RequestBody RsaForm form) {
        log.info("rsa解密数据:{}", JSON.toJSONString(form));
        log.warn("注意此处输出的是私钥加密数据,需要公钥解密");
        return new ApiResponse<>(User.builder()
                .name("克隆人1")
                .createTime(new Date())
                .imei("11111")
                .build());
    }

    /**
     * rsa私钥加密
     *
     * @param json 数据
     * @return ApiResponse
     */
    @ApiOperation(value = "rsa私钥加密", notes = "rsa私钥加密")
    @PostMapping(value = "/rsa/pir", produces = "application/json;charset=UTF-8")
    @EncryptBody(value = EncryptBodyMethod.RSA)
    @ApiImplicitParam(name = "json", value = "json测试数据", defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"rsa私钥加密\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> rsaPir(String json) {
        log.info("rsa私钥加密,原始数据:{}", json);
        return new ApiResponse<>(json);
    }

    /**
     * rsa公钥解密
     *
     * @param data 加密对象
     * @return ApiResponse
     */
    @ApiOperation(value = "rsa公钥解密", notes = "rsa公钥解密")
    @PostMapping(value = "/rsa/pub", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "data", value = "私钥加密后的数据", defaultValue = "JLl3ZOvq7Ue6DmzyKGJZUaZ63Nn9+6i27/mtZ/FbEXysTa2kYCP7XFCJGbzRznd3g17iCRZboAOXj/2NUgQNgQ2Qhoa0j9P3NwYB43Y8Ljm9ezodMhw3QT3VcdBWvcVX8eF3nnxUCjEGLjdAXSV/ZwAjA5BBKOsNlzdAn1C67eExTFOPt2LQOUEpmdvVNUEnYKfubci0yny0fDL6XvRoAj50zojmK3ys16rGiacD3g/6oAK3YGR3OaskYW5eMNFowbJ2BdpdmdDfesYm6/iqBl9FcjnZLKdghO0ELbDuuo03Z9cy1+ta2/QnFXKsplPNOpulOna8s3XaKkcaKjlqoA==")
    public ApiResponse<User> rsaPub(String data) {
        log.info("rsa公钥解密,加密串:{}", data);
        //公钥解密
        byte[] decode = RsaUtil.decryptByPublicKey(Base64.decodeBase64(data), Base64.decodeBase64(config.getRsaPubKey()));
        data = new String(decode);
        User user = JSON.parseObject(data, User.class);
        log.info("公钥解密后的数据{}", user);
        return new ApiResponse<>(user);
    }

    /**
     * rsa公钥加密
     *
     * @param json 测试json数据
     * @return ApiResponse
     */
    @ApiOperation(value = "rsa公钥加密", notes = "rsa公钥加密")
    @PostMapping(value = "/rsa/data")
    @ApiImplicitParam(name = "json", value = "json测试数据", defaultValue = "{ " +
            " \"createTime\": \"2021-08-13 09:47:49\", " +
            " \"id\": 0," +
            " \"imei\": \"11111\"," +
            " \"mobile\": \"13811889989\"," +
            " \"name\": \"克隆人rsa\"," +
            " \"password\": \"123456\"," +
            " \"type\": 0," +
            " \"timestamp\":\"1628823973123\"" +
            " }")
    public ApiResponse<String> rsaData(String json) {

        log.info("rsa-测试数据公钥加密,原始数据:{}", json);
        //公钥加密
        byte[] jiami = RsaUtil.encryptByPublicKey(json.getBytes(), Base64.decodeBase64(config.getRsaPubKey()));
        json = Base64.encodeBase64String(jiami);
        log.warn("公钥加密数据为:{}", Base64.encodeBase64String(jiami));

        return new ApiResponse<>(json);
    }


    @ApiOperation(value = "初始化公私钥", notes = "rsa初始化公私钥")
    @PostMapping(value = "/rsa/key", produces = "application/json;charset=UTF-8")
    public ApiResponse<Map<String, Object>> rsaKey() {
        Map<String, Object> keyMap = initKey.initKey();
        //公钥
        String publicKey = Base64.encodeBase64String(InitKey.getPublicKey(keyMap));
        //私钥
        String privateKey = Base64.encodeBase64String(InitKey.getPrivateKey(keyMap));

        log.warn("公钥：{}" + publicKey);
        log.warn("私钥：{}" + privateKey);

        keyMap = new HashMap<>(4);
        keyMap.put("公钥", publicKey);
        keyMap.put("私钥", privateKey);
        return new ApiResponse<>(keyMap);
    }
}
