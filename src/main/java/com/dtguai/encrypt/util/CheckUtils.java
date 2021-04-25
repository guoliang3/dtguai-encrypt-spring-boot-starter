package com.dtguai.encrypt.util;


import com.dtguai.encrypt.exception.EncryptDtguaiException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * <p>辅助检测工具类</p>
 *
 * @author guo
 * @date 2021年3月15日19:50:23
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckUtils {

    /**
     * 注解的 key 高于 全局配置的 key
     *
     * @param k1      全局
     * @param k2      注解
     * @param keyName 解密方式名称
     * @return key
     */
    public static String checkAndGetKey(String k1, String k2, String keyName) {
        if (!StringUtils.hasText(k1) && !StringUtils.hasText(k2)) {
            log.error("{} is not configured (未配置{})", keyName, keyName);
            throw new EncryptDtguaiException(String.format("%s is not configured (未配置%s)", keyName, keyName));
        }
        return StringUtils.hasText(k2) ? k2 : k1;
    }

}
