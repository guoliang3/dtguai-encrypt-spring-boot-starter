package com.dtguai.encrypt.util;


import com.dtguai.encrypt.exception.EncryptDtguaiException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * <p>辅助检测工具类</p>
 *
 * @author guo
 * @date 2021年3月15日19:50:23
 */
@Slf4j
@UtilityClass
public class CheckUtils {

    /**
     * 注解的 key 优先级高于 全局配置的 key
     *
     * @param k1      全局
     * @param k2      注解
     * @param keyName 来源信息
     * @return key
     */
    public static String checkAndGetKey(String k1, String k2, String keyName) {
        if (!StringUtils.hasText(k1) && !StringUtils.hasText(k2)) {
            log.error("{} 未配置,k1:{},k2:{}", keyName,k1,k2);
            throw new EncryptDtguaiException(String.format("%s未配置,k1:%s,k2:%s", keyName, k1, k2));
        }
        return StringUtils.hasText(k2) ? k2 : k1;
    }

}
