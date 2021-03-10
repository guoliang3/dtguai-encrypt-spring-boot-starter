package com.dtguai.encrypt.bean;


import com.dtguai.encrypt.enums.EncryptBodyMethod;
import com.dtguai.encrypt.enums.SHAEncryptType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>加密注解信息</p>
 * @author guo
 * @date 2019年4月16日14:10:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncryptAnnotationInfoBean {

    private EncryptBodyMethod encryptBodyMethod;

    private String key;

    private SHAEncryptType shaEncryptType;

}
