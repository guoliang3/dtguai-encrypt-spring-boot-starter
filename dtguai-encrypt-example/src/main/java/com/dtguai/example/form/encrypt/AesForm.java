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
@ApiModel(value = "aes-form")
@EqualsAndHashCode(callSuper = false)
public class AesForm {

    /**
     * 手机号
     */
    @ApiModelProperty(hidden = true)
    private String mobile;
    /**
     * 用户名
     */
    @ApiModelProperty(hidden = true)
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty(hidden = true)
    private String password;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     * 状态
     */
    @ApiModelProperty(hidden = true)
    private Integer type;


    /**
     * imei号
     */
    @ApiModelProperty(hidden = true)
    private String imei;

    /**
     * 显示解密后的timestamp,用于展示
     */
    @ApiModelProperty(hidden = true)
    private String timestamp;

    /**
     * 数字证书所需签名,按需要添加
     */
    @ApiModelProperty(hidden = true, value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign;

    /**
     * 加密串,必要参数
     */
    @ApiModelProperty(value = "加密数据", example = "ws2plf0ITCN77oFKgIfFzmILS+ah2KuBGGRY1GoPZ8mgroQiMMZOOdO5vhR7zcYmoBKzfSwNCQa/v0SNgkxk4rZZK3WsXokm9MOJzYfrF0dn7hz1xdxKEG4OlQGs9ZKDZEejhqp6x6zWrHzKSXewGHkCd1JoI+lVjVm2PF9EHldeaxlb6kXvJoO983uWcULWppHdjRQTox8cmaskEGeXiNEVwmVILPzgE18Q97HR8mbVY7jpezjU4m5a2kORTR7lT+jrTxJDKDVrLtpbzEw=")
    private String dataSecret;

}
