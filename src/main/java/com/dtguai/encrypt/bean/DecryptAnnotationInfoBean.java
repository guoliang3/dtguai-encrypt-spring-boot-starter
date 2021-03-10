package com.dtguai.encrypt.bean;


import com.dtguai.encrypt.enums.DecryptBodyMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>解密注解信息</p>
 * @author guo
 * @date 2019年4月16日14:10:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecryptAnnotationInfoBean {

    private DecryptBodyMethod decryptBodyMethod;

    private String key;

    /**
     * 数据超时时间
     */
    private long timeOut;

}
