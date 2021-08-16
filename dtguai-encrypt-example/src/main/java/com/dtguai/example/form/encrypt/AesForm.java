package com.dtguai.example.form.encrypt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录表单
 *
 * @author guo
 * @date 2021年3月15日19:57:56
 */
@Data
@ApiModel(value = "测试-aes")
@EqualsAndHashCode(callSuper = false)
public class AesForm {

    @ApiModelProperty(hidden = true)
    private String mobile;

    @ApiModelProperty(hidden = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String timestamp;

    @ApiModelProperty(hidden = true ,value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign;

    @ApiModelProperty(value = "加密数据", example = "fKeXjbDo65BIqpyJFJxnGG4gDz/UpDer9X0Gg3gRoEBL/B+nyL45q4kDfVij/eiCEvd2ZOfmo6awBftbp3f2VQl2ICMmCHi9lpCoAp8wGHDzbDIZV9E5QKYC7R0is5C9RtJJbwrOVCT0SbwvQjmEufs7x0saLGl5RVlFFPnabH1Rsd13jAmU0DcdKcC8s/soRS0Cp/7+u+/QVOaZoGCokWfgqGsVS1xNKQ8uiaafmrbr+j1eYd7JknwhdMccQtjKpzzNfNuL78gp")
    private String dataSecret;

}
