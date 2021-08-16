package com.dtguai.encrypt.sign;

import com.alibaba.fastjson.JSON;
import com.dtguai.encrypt.config.SignConfig;
import com.dtguai.encrypt.exception.SignDtguaiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


/**
 * 系统日志，切面处理类
 *
 * @author guo
 * @date 2021年3月12日13:41:04
 */
@Aspect
@Component
@Order(1)
@Slf4j
@AllArgsConstructor
public class SignAspect {

    private final SignConfig signConfig;

    @Pointcut("@annotation(com.dtguai.encrypt.annotation.Sign)")
    public void signPointCut() {
        //自定义切入点
    }

    public static final String TOKEN_HEADER = "token";
    public static final String SIGN_HEADER = "sign";
    public static final String DATA_SECRET_HEADER = "dataSecret";

    @Around("signPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        //请求的参数
        Object[] args = point.getArgs();

        TreeMap<String, Object> reqm = Optional.ofNullable(args[0])
                .map(x -> JSON.toJSONStringWithDateFormat(x, "yyyy-MM-dd HH:mm:ss"))
                .map(x -> JSON.<TreeMap<String, Object>>parseObject(x, TreeMap.class))
                .orElseThrow(() -> new SignDtguaiException("sing注解中加密数据为空"));

        String timestamp = Optional.ofNullable(reqm.get("timestamp"))
                .map(Object::toString)
                .orElseThrow(() -> new SignDtguaiException("数字证书timestamp不能为空"));

        log.info("sign的TreeMap默认key升序排序timestamp:{} ---- json:{}", timestamp, JSON.toJSONString(reqm));

        Optional.of(reqm)
                .ifPresent(this::validSign);
        //执行方法
        return point.proceed();
    }

    /**
     * 验证数字证书
     *
     * @param reqm 数据map
     */
    private void validSign(Map<String, Object> reqm) {
        String md5Sign;
        String sign;
        StringBuilder paramBuilder = new StringBuilder();
        try {
            reqm = Optional.ofNullable(reqm)
                    .orElseThrow(() -> new SignDtguaiException(SIGN_HEADER + "的map不能为空"));
            sign = Optional.ofNullable(reqm)
                    .map(x -> x.get(SIGN_HEADER))
                    .map(Object::toString)
                    .orElseThrow(() -> new SignDtguaiException(SIGN_HEADER + "不能为空"));

            // 校验 Sign
            reqm.forEach((k, v) -> {
                List<String> ignore = signConfig.getIgnore();
                if(v != null && !ignore.contains(k)){
                    paramBuilder.append(k).append("=").append(v).append("&");
                }
            });
            String dataSing = paramBuilder.append("signKey=").append(signConfig.getKey()).toString();
            log.info("sing之前的拼装数据:{}", dataSing);
            md5Sign = DigestUtils.md5Hex(dataSing);
        } catch (Exception e) {
            log.error("sign数据签名校验出错{}", reqm, e);
            throw new SignDtguaiException(SIGN_HEADER + "数据签名校验出错");
        }
        if (!md5Sign.equals(sign)) {
            log.error("验证失败:{}  传入的sign:{}  当前生成的md5Sign:{}", paramBuilder, sign, md5Sign);
            throw new SignDtguaiException("数字证书校验失败");
        }

    }


}
