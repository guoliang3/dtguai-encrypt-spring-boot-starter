package com.dtguai.encrypt.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录表单
 *
 * @author guo
 * @date 2021年4月28日18:01:55
 */
@Data
@ApiModel(value = "测试-sm3")
@EqualsAndHashCode(callSuper = false)
public class Sm3Form {

    @ApiModelProperty(example = "13811889989")
    private String mobile;

    @ApiModelProperty(example = "123456")
    private String password;

    @ApiModelProperty(example = "1619074512137")
    private String timestamp;

    @ApiModelProperty(value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign;

}
