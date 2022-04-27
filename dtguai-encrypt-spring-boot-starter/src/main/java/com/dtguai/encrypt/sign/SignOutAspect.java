package com.dtguai.encrypt.sign;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.dtguai.encrypt.annotation.SignOut;
import com.dtguai.encrypt.config.SignConfig;
import com.dtguai.encrypt.exception.SignDtguaiException;
import com.dtguai.encrypt.util.CheckUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


/**
 * 数字签名 输出加签 切面操作
 *
 * @author guo
 * @version 1.1.1
 * @date 2022年4月15日16:51:00
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class SignOutAspect {

    private final SignConfig signConfig;

    @Pointcut("@annotation(com.dtguai.encrypt.annotation.SignOut)")
    public void signOutPointCut() {
        //自定义切入点
    }

    @Around("signOutPointCut()&&@annotation(signOut)")
    public Object around(ProceedingJoinPoint point, SignOut signOut) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取返回类型
        Type returnType = signature.getMethod().getGenericReturnType();

        Object result = point.proceed();

        //获取返回类型->转换成map
        Map<String, Object> repMap = Optional.of(result)
                .map(x -> JSON.toJSONString(x, "yyyy-MM-dd HH:mm:ss"))
                .map(x -> {
                    try {
                        return JSON.<Map<String, Object>>parseObject(x, Map.class);
                    } catch (JSONException e) {
                        log.error("sing加签json转map败请检查返回参数:{},fastjson转换后的json:{}", result, x);
                        throw new SignDtguaiException("sing加签json转map败请检查返回参数:" + result);
                    }
                })
                .orElseThrow(() -> new SignDtguaiException("sing加签失败请检查返回参数:" + result));

        String timestamp = String.valueOf(System.currentTimeMillis());
        //返回对象添加时间戳以便验证sing(验签) 参数:必须
        repMap.put("timestamp", timestamp);

        //获取返回对象需要->加签对象的key
        String dataName = CheckUtils.checkAndGetKey(signConfig.getResultName(), signOut.resultName(), "sign返回加签");

        TreeMap<String, Object> nm = Optional.of(repMap)
                .map(x -> x.get(dataName))
                .map(Object::toString)
                .map(x -> JSON.<TreeMap<String, Object>>parseObject(x, TreeMap.class))
                .orElseThrow(() -> new SignDtguaiException("sing注解中加密数据为空,请检查返回对象key:" + dataName + ",返回数据:" + repMap));

        //添加时间戳避免加签相同
        nm.put("timestamp", timestamp);
        //添加签名
        repMap.put("sign", sign(nm, signOut));

        return parse(JSON.toJSONString(repMap), returnType);
    }

    /**
     * 加签
     *
     * @param repm 数据map
     */
    private String sign(Map<String, Object> repm, SignOut signOut) {
        StringBuilder paramBuilder = new StringBuilder();
        repm.forEach((k, v) ->
                paramBuilder.append(k).append("=").append(v).append("&")
        );
        String key = CheckUtils.checkAndGetKey(signConfig.getKey(), signOut.key(), "signOut加签key不能为空");
        String signData = paramBuilder.append("signKey=").append(key).toString();
        log.info("signOut加签数据:{}", signData);
        return DigestUtils.md5Hex(signData);
    }

    /**
     * 返回类型转换
     *
     * @param value json
     * @param type  返回类型
     * @return Object
     */
    public Object parse(String value, Type type) {
        Object result = null;
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (((Class<?>) rawType).isAssignableFrom(List.class)) {
                result = JSON.parseArray(value, (Class<?>) parameterizedType.getActualTypeArguments()[0]);
            }
        }
        return result == null ? JSON.parseObject(value, type) : result;
    }

}
