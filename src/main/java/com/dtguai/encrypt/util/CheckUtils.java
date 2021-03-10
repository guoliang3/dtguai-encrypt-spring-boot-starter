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
 * @date 2019年6月25日09:31:46
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckUtils {

    public static String checkAndGetKey(String k1, String k2, String keyName) {
        if (StringUtils.isEmpty(k1) && StringUtils.isEmpty(k2)) {
            log.error("{} is not configured (未配置{})", keyName, keyName);
            throw new EncryptDtguaiException(String.format("%s is not configured (未配置%s)", keyName, keyName));
        }
        return k1 == null ? k2 : k1;
    }

}
