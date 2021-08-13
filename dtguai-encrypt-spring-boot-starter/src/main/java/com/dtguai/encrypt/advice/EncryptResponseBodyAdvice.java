package com.dtguai.encrypt.advice;


import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.annotation.encrypt.EncryptBody;
import com.dtguai.encrypt.bean.EncryptAnnotationInfoBean;
import com.dtguai.encrypt.config.EncryptBodyConfig;
import com.dtguai.encrypt.config.EncryptConfig;
import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.exception.DecryptDtguaiException;
import com.dtguai.encrypt.exception.EncryptDtguaiException;
import com.dtguai.encrypt.util.CheckUtils;
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
@RestControllerAdvice
@Slf4j
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    private final EncryptBodyConfig config;

    private final EncryptConfig encryptConfig;

    @Autowired
    public EncryptResponseBodyAdvice(ObjectMapper objectMapper, EncryptBodyConfig config, EncryptConfig encryptConfig) {
        this.objectMapper = objectMapper;
        this.config = config;
        this.encryptConfig = encryptConfig;
    }

    /**
     * @param returnType    returnType
     * @param converterType converterType
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        Annotation[] annotations = returnType.getDeclaringClass().getAnnotations();

        if (Arrays.stream(annotations).anyMatch(EncryptBody.class::isInstance)) {
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
                    .map(Object::toString)
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
                    .encryptMsgName(CheckUtils.checkAndGetKey(encryptConfig.getResultName(), encryptBody.resultName(), "返回值名称"))
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


        EncryptBodyMethod method = Optional.ofNullable(infoBean.getEncryptBodyMethod())
                .orElseThrow(() -> new DecryptDtguaiException("加密方式未定义"));

        if (method == EncryptBodyMethod.SHA) {
            return method.getISecurity().encrypt(formatStringBody, infoBean.getShaEncryptType().getValue(), config);
        }

        return method.getISecurity().encrypt(formatStringBody, infoBean.getKey(), config);

    }


}
