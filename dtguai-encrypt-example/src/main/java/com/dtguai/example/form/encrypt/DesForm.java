package com.dtguai.example.form.encrypt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录表单
 *
 * @author guo
 * @date 2019年6月25日15:28:42
 */
@Data
@ApiModel(value = "测试-des")
@EqualsAndHashCode(callSuper = false)
public class DesForm {

    @ApiModelProperty(hidden = true)
    private String mobile;

    @ApiModelProperty(hidden = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String timestamp;

    @ApiModelProperty(hidden = true, value = "数字签名", example = "fcc9f41273e7cdf43b148a9fa6a33d81")
    private String sign;

    @ApiModelProperty(value = "加密数据", example = "AAA46A730A25EEB1F39BFF356D3D926CAE4D673DA3660256D475CDD5EECE88FDCC9AE63ADB4EF812F0E7D5800DFA6AEF3659BD4F9F3CC31A633B8839A5FB8BE861108852E585C564A77E02B435E6EF3F85D4B94A8864ACBF4254B6B0D70C0F616E6A542B99AE5C98B57A375A9993DDF7ED5DC3D65A092EEC2ED53CCA82E12B411C35E09E58C47583B3E83EFCB4575A0463CA4573D1C06B0E8495C3A063E44E2AB477A74B234987693D3508C54FF66131")
    private String dataSecret;

}
