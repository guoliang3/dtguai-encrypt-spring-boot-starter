package com.dtguai.encrypt.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TestSignForm
 *
 * @author guo
 * @date 2019年6月25日15:27:47
 */
@Data
@ApiModel(value = "测试form")
@EqualsAndHashCode(callSuper = false)
public class SignForm {

    @ApiModelProperty(value = "手机号", required = true, example = "18600231871")
    private String mobile = "18600231871";

    @ApiModelProperty(value = "密码", example = "123456")
    private String password = "123456";

    @ApiModelProperty(value = "时间戳", example = "1561529899962")
    private String timestamp = "1561529899962";

    @ApiModelProperty(value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign = "cb77cdd3d390f7d40a6f36af937ceb50";
}
