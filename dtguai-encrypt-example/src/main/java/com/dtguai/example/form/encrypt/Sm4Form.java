package com.dtguai.example.form.encrypt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(hidden = true)
    private String imei;

    @ApiModelProperty(hidden = true)
    private String mobile;

    @ApiModelProperty(hidden = true)
    private String name;

    @ApiModelProperty(hidden = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String timestamp;

    @ApiModelProperty(hidden = true, value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign;

    @ApiModelProperty(value = "加密数据", example = "d653c8e827b3604a142bad2ad2ea9f78d9f1f412a8f9c01a10fe7ab30bd66096150fef84c9f6aa685f571cc497437e0a724c797d5d6548a6d8a4a1442d127889964468a00778404dd03fb6563c3b3a04ea414aa367db9c5a022f9dbc91dfef450ceffb5326425fd839b048afc9f74c6af9cdfbfdc7bc9b980be7a05bbc80c756975fcd2dd13f93e2f4beff8df9da29460b3477da1ce92b021e8678e18fabd601a6bc617698c0265f3a6fb5718a993910daa604dcaf11678cade2bb2ca31899cc")
    private String dataSecret;

}
