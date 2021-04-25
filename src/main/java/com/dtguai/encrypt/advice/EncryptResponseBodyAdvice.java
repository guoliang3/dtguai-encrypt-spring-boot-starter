package com.dtguai.encrypt.advice;


import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.bean.EncryptAnnotationInfoBean;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.enums.SHAEncryptType;
import com.dtguai.encrypt.exception.DecryptDtguaiException;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import com.dtguai.encrypt.util.CheckUtils;
import com.dtguai.encrypt.util.Md5EncryptUtil;
import com.dtguai.encrypt.util.ShaEncryptUtil;
import com.dtguai.encrypt.util.security.AesEncryptUtil;
import com.dtguai.encrypt.util.security.DesEncryptUtil;
import com.dtguai.encrypt.util.security.RsaEncryptUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


/**
 * 响应数据的加密处理<br>
 * 本类只对控制器参数中含有<strong>{@link org.springframework.web.bind.annotation.ResponseBody}</strong>
 * 或者控制类上含有<strong>{@link org.springframework.web.bind.annotation.RestController}</strong>
 * 以及package为com.dtguai.app.annotation.encrypt.*下的注解有效
 *
 * @author guo
 * @date 2019年6月17日09:29:45
 */
@Order(1)
@RestControllerAdvice(basePackages = "com.dtguai.app")
@Slf4j
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    private final EncryptBodyConfig config;

    @Autowired
    public EncryptResponseBodyAdvice(ObjectMapper objectMapper, EncryptBodyConfig config) {
        this.objectMapper = objectMapper;
        this.config = config;
    }

    /**
     * @param returnType    returnType
     * @param converterType converterType
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        Annotation[] annotations = returnType.getDeclaringClass().getAnnotations();

        if (Arrays.stream(annotations).anyMatch(x -> x instanceof EncryptBody)) {
            return true;
        }

        return Optional.of(returnType)
                .map(MethodParameter::getMethod)
                .map(x -> x.isAnnotationPresent(EncryptBody.class))
                .orElseThrow(() -> new DecryptDtguaiException("加密拦截器返回异常"));

    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body == null) {
            return null;
        }
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        String str;
        String result = null;
        Map<String, Object> repMap = null;

        //获取方法注解 执行顺序 方法 ->类
        EncryptAnnotationInfoBean methodAnnotation = getMethodAnnotation(returnType);
        //获取类注解 执行顺序 方法 ->类
        EncryptAnnotationInfoBean classAnnotation = getClassAnnotation(returnType.getDeclaringClass());

        String dataName = Optional.ofNullable(methodAnnotation)
                .map(EncryptAnnotationInfoBean::getEncryptMsgName)
                .orElse(
                        Optional.ofNullable(classAnnotation)
                                .map(EncryptAnnotationInfoBean::getEncryptMsgName)
                                .orElse(null)
                );

        try {
            str = objectMapper.writeValueAsString(body);
            repMap = Optional.ofNullable(str)
                    .map(x -> JSON.<Map<String, Object>>parseObject(x, Map.class))
                    .orElse(null);

            result = Optional.ofNullable(repMap)
                    .map(x -> x.get(dataName))
                    .map(JSON::toJSONString)
                    .orElse(null);

        } catch (JsonProcessingException e) {
            log.error("响应数据的加密异常,请联系管理员", e);
        }

        String encryptStr;

        if (methodAnnotation != null && result != null) {
            encryptStr = switchEncrypt(result, methodAnnotation);
        } else if (classAnnotation != null && result != null) {
            encryptStr = switchEncrypt(result, classAnnotation);
        } else {
            log.error("EncryptResponseBodyAdvice 加密数据失败 body:{}", body);
            encryptStr = null;
        }

        Optional.ofNullable(repMap)
                .ifPresent(x -> x.put(dataName, encryptStr));

        return repMap;
    }

    /**
     * 获取方法控制器上的加密注解信息
     *
     * @param methodParameter 控制器方法
     * @return 加密注解信息
     */
    private EncryptAnnotationInfoBean getMethodAnnotation(MethodParameter methodParameter) {

        Method method = Optional.ofNullable(methodParameter)
                .map(MethodParameter::getMethod)
                .orElseThrow(() -> {
                    log.error("获取方法控制器上的加密注解信息,为null--methodParameter:{}", methodParameter);
                    return new EncryptDtguaiException("获取方法控制器上的加密注解信息,为null");
                });

        EncryptBody encryptBody = methodParameter.getMethodAnnotation(EncryptBody.class);

        if (method.isAnnotationPresent(EncryptBody.class) && encryptBody != null) {

            return EncryptAnnotationInfoBean.builder()
                    .encryptBodyMethod(encryptBody.value())
                    .key(encryptBody.otherKey())
                    .shaEncryptType(encryptBody.shaType())
                    .encryptMsgName(encryptBody.encryptMsgName())
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
    private EncryptAnnotationInfoBean getClassAnnotation(Class<?> clazz) {
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        return Optional.of(annotations)
                .map(x -> {
                    for (Annotation annotation : x) {
                        if (annotation instanceof EncryptBody) {
                            EncryptBody encryptBody = (EncryptBody) annotation;
                            return EncryptAnnotationInfoBean.builder()
                                    .encryptBodyMethod(encryptBody.value())
                                    .key(encryptBody.otherKey())
                                    .shaEncryptType(encryptBody.shaType())
                                    .build();
                        }
                    }
                    return null;
                })
                .orElse(null);
    }


    /**
     * 选择加密方式并进行加密
     *
     * @param formatStringBody 目标加密字符串
     * @param infoBean         加密信息
     * @return 加密结果
     */
    private String switchEncrypt(String formatStringBody, EncryptAnnotationInfoBean infoBean) {
        EncryptBodyMethod method = infoBean.getEncryptBodyMethod();
        if (method == null) {

            log.error("EncryptResponseBodyAdvice加密方式未定义  找不到加密的method=null  formatStringBody:{}", formatStringBody);
            throw new EncryptDtguaiException("EncryptResponseBodyAdvice加密方式未定义  找不到加密的method");

        } else if (method == EncryptBodyMethod.MD5) {

            return Md5EncryptUtil.encrypt(formatStringBody);

        } else if (method == EncryptBodyMethod.SHA) {

            SHAEncryptType shaEncryptType = infoBean.getShaEncryptType();
            if (shaEncryptType == null) {
                shaEncryptType = SHAEncryptType.SHA256;
            }
            return ShaEncryptUtil.encrypt(formatStringBody, shaEncryptType);

        } else if (method == EncryptBodyMethod.SM3) {

            return SmUtil.sm3(formatStringBody);

        }

        String key = infoBean.getKey();

        if (method == EncryptBodyMethod.DES) {

            key = CheckUtils.checkAndGetKey(config.getDesKey(), key, "DES-KEY加密");
            return DesEncryptUtil.encrypt(formatStringBody, key, config.getDesCipherAlgorithm());

        } else if (method == EncryptBodyMethod.AES) {

            key = CheckUtils.checkAndGetKey(config.getAesKey(), key, "AES-KEY加密");
            return AesEncryptUtil.encrypt(formatStringBody, key, config.getAesCipherAlgorithm());

        } else if (method == EncryptBodyMethod.RSA) {

            key = CheckUtils.checkAndGetKey(config.getRsaPirKey(), key, "RSA-KEY加密");
            return RsaEncryptUtil.encrypt(formatStringBody, key);

        } else if (method == EncryptBodyMethod.SM2) {

            key = CheckUtils.checkAndGetKey(config.getSm2PubKey(), key, "SM2-KEY-对方公钥-加密");
            return SmUtil.sm2(null, key)
                    .encryptBcd(formatStringBody, KeyType.PublicKey);


        } else if (method == EncryptBodyMethod.SM4) {

            key = CheckUtils.checkAndGetKey(config.getSm4Key(), key, "RSA-KEY加密");
            return SmUtil.sm4(key.getBytes())
                    .encryptHex(formatStringBody);

        }

        log.error("EncryptResponseBodyAdvice 加密数据失败 method:{}  formatStringBody:{}", method, formatStringBody);
        throw new EncryptDtguaiException("EncryptResponseBodyAdvice 加密数据失败");
    }


}
