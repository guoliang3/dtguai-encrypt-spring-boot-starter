package com.dtguai.example.api.form.encrypt;

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
public class Sm4Form {

    @ApiModelProperty(hidden = true)
    private String mobile;

    @ApiModelProperty(hidden = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String timestamp;

    @ApiModelProperty(hidden = true, value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign;

    @ApiModelProperty(value = "加密数据", example = "d653c8e827b3604a142bad2ad2ea9f787abae11267489c9e9721a9e08fc7f33eda826c2b875ea4d1ec20393f9dd74309cf25d32b214fae7fc017e9ad1f9704e32aa1cd02bd5af9ca46211389a42eaa5e20ea152c201c4326853f56a233180cba943456e727b62c0b5170247da55cb2b32b3a93ab6dc1f6f34a52060684f37a71820d106e588e1759d5b73a94d468c6e7f051b5d98f9ec833150bfb9261f3591a046864f047aa1f0b5d518f7fabfaca0c")
    private String dataSecret;

}
