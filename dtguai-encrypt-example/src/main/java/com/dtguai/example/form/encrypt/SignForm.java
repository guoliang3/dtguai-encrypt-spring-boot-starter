package com.dtguai.example.api.form.encrypt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TestSignForm
 *
 * @author guo
 * @date 2021年4月29日13:49:41
 */
@Data
@ApiModel(value = "测试sign")
@EqualsAndHashCode(callSuper = false)
public class SignForm {

    @ApiModelProperty(value = "手机号", example = "18600231871")
    private String mobile;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "时间戳", example = "1561529899962")
    private String timestamp;

    @ApiModelProperty(value = "数字签名", example = "73a7864a060117557472ce8072ef44ef")
    private String sign;
}
