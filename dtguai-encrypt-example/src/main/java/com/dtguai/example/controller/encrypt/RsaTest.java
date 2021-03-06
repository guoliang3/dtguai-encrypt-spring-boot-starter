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
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
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
 * @version 1.1.1
 * @date 2022年4月26日15:36:29
 */
@RestController
@RequestMapping("/test")
@Api(value = "rsa", tags = {"rsa"})
@Slf4j
@AllArgsConstructor
public class RsaTest {

    private final EncryptBodyConfig config;

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
                .name("克隆人-rsa私钥加密")
                .createTime(new Date())
                .imei("11111")
                .build());
    }

    /**
     * rsa公钥加密
     *
     * @param json 测试json数据
     * @return ApiResponse
     */
    @ApiOperation(value = "rsa公钥加密", notes = "rsa公钥加密")
    @PostMapping(value = "/rsa/data")
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, defaultValue = "{ " +
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

    /**
     * rsa私钥解密
     *
     * @param form 公钥加密对象
     * @return ApiResponse
     */
    @ApiOperation(value = "rsa私钥解密", notes = "此处输出的是私钥加密数据,需要公钥解密")
    @PostMapping(value = "/rsa/decrypt", produces = "application/json;charset=UTF-8")
    @DecryptBody(value = DecryptBodyMethod.RSA)
    public ApiResponse<User> rsaDecrpt(@RequestBody RsaForm form) {
        User user = new User();
        BeanUtils.copyProperties(form, user);
        return new ApiResponse<>(user);
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
    @ApiImplicitParam(name = "json", value = "json测试数据", dataTypeClass = String.class, defaultValue = "{ " +
            " \"createTime\": \"2022-04-13 09:47:49\", " +
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
    @ApiImplicitParam(name = "data", value = "私钥加密后的数据", required = true, dataTypeClass = String.class, defaultValue = "b2GJx5qlCNfVxPPpIiii8MprtPAkTbbReewjJy0FZeHHAMXmgDo6eGyEGRlvLebvPM+L+wvAFZs8EhdoInk0+SEU6FtBHkv0GmF9A1gQtuokhgdNAAHVwo7WcwIUDS/P4GC9m5Iy39XPkDX5Z9MGrE15TdCRXLhH7+PnHdWZFww99d/Xff7W2DCDAgvdDAYm/82eXF6D1eBwCFEw4TyCOutxrqf9rKe7Z0b0sP1c/3OjuwE+QKwr+Zm7XROYG6LUeZTlgNOFjTpc+XPoP3yKgwODlXDiEUPhzPb/beqyGyLgCyewJ2wrfoB8kS5UReSj+LBg/VZq3/6HCk54oip4Ng==")
    public ApiResponse<User> rsaPub(String data) {
        //公钥解密
        byte[] decode = RsaUtil.decryptByPublicKey(Base64.decodeBase64(data), Base64.decodeBase64(config.getRsaPubKey()));
        User user = JSON.parseObject(new String(decode), User.class);
        log.info("公钥解密后的数据{}", user);
        return new ApiResponse<>(user);
    }


    /**
     * 根据传入的参数初始化秘钥
     *
     * @param key 自定义key
     * @return ApiResponse<Map < String, Object>>
     */
    @ApiOperation(value = "初始化公私钥", notes = "rsa初始化公私钥")
    @PostMapping(value = "/rsa/key", produces = "application/json;charset=UTF-8")
    @ApiImplicitParam(name = "key", value = "自定义key初始化rsa公私钥", required = true, dataTypeClass = String.class, defaultValue = "xiaoFuLoveXiaoQiu")
    public ApiResponse<Map<String, Object>> rsaKey(String key) {
        Map<String, Object> keyMap = InitKey.initKey(key);
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
