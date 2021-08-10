package com.dtguai.example.api.form.encrypt;

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


}
