package com.dtguai.example.form.encrypt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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

    @ApiModelProperty(value = "创建时间", example = "2021-08-13 09:47:49")
    private Date createTime;

    @ApiModelProperty(value = "id", example = "0")
    private int id;

    @ApiModelProperty(value = "imei", example = "11111")
    private String imei;

    @ApiModelProperty(value = "手机号", example = "13811889989")
    private String mobile;

    @ApiModelProperty(value = "名称", example = "克隆人cx")
    private String name;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "时间戳", example = "1628823973123")
    private String timestamp;

    @ApiModelProperty(value = "数字签名", example = "afcfb9e50db9c36a369da71e44a5ad98")
    private String sign;
}
