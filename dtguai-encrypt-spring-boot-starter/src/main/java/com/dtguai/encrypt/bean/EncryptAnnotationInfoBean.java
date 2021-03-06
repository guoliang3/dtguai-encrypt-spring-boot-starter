package com.dtguai.encrypt.bean;


import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.enums.SHAEncryptType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>加密注解信息</p>
 *
 * @author guo
 * @date 2019年4月16日14:10:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncryptAnnotationInfoBean {

    /**
     * 加密类型
     */
    private EncryptBodyMethod encryptBodyMethod;

    /**
     * 注解key 优先于配置文件key
     */
    private String key;

    private SHAEncryptType shaEncryptType;

    /**
     * 所需要加密的字段
     */
    private String encryptMsgName;
}
