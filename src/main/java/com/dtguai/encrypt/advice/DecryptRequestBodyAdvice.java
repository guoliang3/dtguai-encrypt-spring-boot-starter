package com.dtguai.encrypt.advice;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.decrypt.DecryptBody;
import com.dtguai.encrypt.bean.DecryptAnnotationInfoBean;
import com.dtguai.encrypt.bean.DecryptHttpInputMessage;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.DecryptBodyMethod;
import com.dtguai.encrypt.exception.DecryptDtguaiException;
import com.dtguai.encrypt.util.CheckUtils;
import com.dtguai.encrypt.util.security.AesEncryptUtil;
import com.dtguai.encrypt.util.security.DesEncryptUtil;
import com.dtguai.encrypt.util.security.RsaEncryptUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 请求数据解密处理
 * 本类只对控制器参数中含有<strong>{@link org.springframework.web.bind.annotation.RequestBody}</strong>
 * 以及package为<strong><code>com.dtguai.encrypt.annotation.decrypt</code></strong>下的注解有效
 *
 * @author guo
 * @date 2019年6月17日09:29:37
 * @see RequestBodyAdvice
 */
@Order(1)
@RestControllerAdvice(basePackages = "com.dtguai.app")
@Slf4j
@AllArgsConstructor
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    private final EncryptBodyConfig config;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        Annotation[] annotations = methodParameter.getDeclaringClass().getAnnotations();

        if (Arrays.stream(annotations).anyMatch(x -> x instanceof DecryptBody)) {
            return true;
        }

        return Optional.of(methodParameter)
                .map(MethodParameter::getMethod)
                .map(x -> x.isAnnotationPresent(DecryptBody.class))
                .orElseThrow(() -> new DecryptDtguaiException("解密拦截器返回异常"));

    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        if (!Optional.of(inputMessage).map(x -> {
            try {
                return x.getBody();
            } catch (IOException e) {
                log.error("数据解密初始化异常,时间:{}", new Date());
                return null;
            }
        }).isPresent()) {
            return inputMessage;
        }

        LinkedHashMap<String, Object> req;
        String body;
        String sign;
        try {
            body = IOUtils.toString((inputMessage.getBody()), config.getEncoding());
            req = JSON.<LinkedHashMap<String, Object>>parseObject(body, LinkedHashMap.class);

            body = (String) Optional.ofNullable(req.get("dataSecret"))
                    .orElse(null);

            sign = (String) Optional.ofNullable(req.get("sign"))
                    .orElse(null);

        } catch (Exception e) {
            log.error("无法获取请求正文数据，请检查发送数据体或请求方法是否符合规范", e);
            throw new DecryptDtguaiException("无法获取请求正文数据，请检查发送数据体或请求方法是否符合规范");
        }
        if (StringUtils.hasText(body)) {
            log.error("请求参数dataSecret为null或为空字符串，因此解密失败body:{}", body);
            throw new DecryptDtguaiException("请求正文为NULL或为空字符串，因此解密失败");
        }
        String decryptBody = null;
        DecryptAnnotationInfoBean methodAnnotation = this.getMethodAnnotation(parameter);
        if (methodAnnotation != null) {
            decryptBody = switchDecrypt(body, methodAnnotation);
        } else {
            DecryptAnnotationInfoBean classAnnotation = this.getClassAnnotation(parameter.getDeclaringClass());
            if (classAnnotation != null) {
                decryptBody = switchDecrypt(body, classAnnotation);
            }
        }

        decryptBody = Optional.ofNullable(decryptBody).orElseThrow(() -> {
                    log.error("decryptBody是null" +
                            "当前类:{}", this.getClass().getName());
                    return new DecryptDtguaiException("解密错误，请检查选择的源数据的加密方式是否正确");
                }
        );

        try {
            req = JSON.<LinkedHashMap<String, Object>>parseObject(decryptBody, LinkedHashMap.class);
            req.put("sign", sign);
            log.info("解密数据补充timestamp和sign的map:{}", JSON.toJSONString(req));
            InputStream inputStream = IOUtils.toInputStream(JSON.toJSONString(req), config.getEncoding());
            return new DecryptHttpInputMessage(inputStream, inputMessage.getHeaders());
        } catch (Exception e) {
            throw new DecryptDtguaiException("字符串转换成流格式异常，请检查编码等格式是否正确");
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 获取方法控制器上的加密注解信息
     *
     * @param methodParameter 控制器方法
     * @return 加密注解信息
     */
    private DecryptAnnotationInfoBean getMethodAnnotation(MethodParameter methodParameter) {

        boolean annotation = Optional.ofNullable(methodParameter)
                .map(MethodParameter::getMethod)
                .map(x -> x.isAnnotationPresent(DecryptBody.class))
                .orElse(false);

        if (annotation) {

            DecryptBody decryptBody = methodParameter.getMethodAnnotation(DecryptBody.class);

            return DecryptAnnotationInfoBean.builder()
                    .decryptBodyMethod(Optional.ofNullable(decryptBody)
                            .map(DecryptBody::value)
                            .orElse(null))
                    .key(Optional.ofNullable(decryptBody)
                            .map(DecryptBody::otherKey)
                            .orElse(null))
                    .timeOut(Optional.ofNullable(decryptBody)
                            .map(DecryptBody::timeOut)
                            .orElse(0L))
                    .build();
        }


        return null;
    }

    /**
     * 获取类控制器上的加密注解信息
     *
     * @param clazz 控制器类
     * @return 加密注解信息
     */
    private DecryptAnnotationInfoBean getClassAnnotation(Class<?> clazz) {

        Annotation[] annotations = clazz.getDeclaredAnnotations();

        return Optional.of(annotations)
                .map(x -> {
                            for (Annotation annotation : x) {
                                if (annotation instanceof DecryptBody) {
                                    DecryptBody decryptBody = (DecryptBody) annotation;
                                    return DecryptAnnotationInfoBean.builder()
                                            .decryptBodyMethod(decryptBody.value())
                                            .key(decryptBody.otherKey())
                                            .build();
                                }
                            }
                            return null;
                        }
                )
                .orElse(null);
    }


    /**
     * 选择加密方式并进行解密
     *
     * @param formatStringBody 目标解密字符串
     * @param infoBean         加密信息
     * @return 解密结果
     */
    private String switchDecrypt(String formatStringBody, DecryptAnnotationInfoBean infoBean) {

        DecryptBodyMethod method = infoBean.getDecryptBodyMethod();

        method = Optional.ofNullable(method)
                .orElseThrow(() -> new DecryptDtguaiException("解密方式未定义"));

        //获取注解中的key
        String key = infoBean.getKey();
        String decodeData;
        if (method == DecryptBodyMethod.DES) {

            key = CheckUtils.checkAndGetKey(config.getDesKey(), key, "DES-KEY解密");
            decodeData = DesEncryptUtil.decrypt(formatStringBody, key, config.getDesCipherAlgorithm());

        } else if (method == DecryptBodyMethod.AES) {

            key = CheckUtils.checkAndGetKey(config.getAesKey(), key, "AES-KEY解密");
            decodeData = AesEncryptUtil.decrypt(formatStringBody, key, config.getAesCipherAlgorithm());

        } else if (method == DecryptBodyMethod.RSA) {

            key = CheckUtils.checkAndGetKey(config.getRsaPirKey(), key, "RSA-KEY解密");
            decodeData = RsaEncryptUtil.decrypt(formatStringBody, key);

        } else if (method == DecryptBodyMethod.SM2) {

            key = CheckUtils.checkAndGetKey(config.getSm2PirKey(), key, "SM2-KEY解密");
            decodeData = StrUtil.utf8Str(
                    SmUtil.sm2(key, null).decryptFromBcd(formatStringBody, KeyType.PrivateKey)
            );

        } else if (method == DecryptBodyMethod.SM4) {

            key = CheckUtils.checkAndGetKey(config.getSm4Key(), key, "SM4-KEY解密");
            decodeData = SmUtil.sm4(key.getBytes()).decryptStr(formatStringBody);

        } else {
            log.error("解密方式未定义,不知道你是aes/ecs/rsa");
            throw new DecryptDtguaiException("解密方式未定义,不知道你是aes/ecs/rsa");
        }

        //验证数据是否过期timestamp
        verifyTime(decodeData, infoBean.getTimeOut());

        return decodeData;
    }

    /**
     * 判断数据是否超时 不配置 不判断超时时间
     *
     * @param decodeData 解密后的数据
     * @param timeOut    过期时间
     */
    private void verifyTime(String decodeData, long timeOut) {
        if (0 != timeOut) {

            long timestamp = Optional.ofNullable(decodeData)
                    .map(x -> JSON.parseObject(x, Map.class))
                    .map(x -> x.get("timestamp"))
                    .map(x -> Long.parseLong(x.toString()))
                    .orElseThrow(() -> new DecryptDtguaiException("数据加密timestamp不能为空"));

            //当前时间
            long nowTime = System.currentTimeMillis();

            //判断数据加密时间 时间戳+过期时间如果小于当前时间踢飞
            if (timestamp + timeOut < nowTime) {
                log.error("时间戳:{},时间戳+过期时间:{},当前时间:{},时间差:{},数据为:{}"
                        , timestamp
                        , timestamp + timeOut
                        , nowTime
                        , timestamp + timeOut - nowTime
                        , decodeData);
                throw new DecryptDtguaiException("解密数据过期,提交失败");
            }
        }
    }
}
