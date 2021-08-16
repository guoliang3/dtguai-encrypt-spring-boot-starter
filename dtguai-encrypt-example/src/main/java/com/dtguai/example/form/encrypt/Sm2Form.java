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
@ApiModel(value = "测试-sm2")
@EqualsAndHashCode(callSuper = false)
public class Sm2Form {

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

    @ApiModelProperty(hidden = true, value = "数字签名", example = "73a7864a060117557472ce8072ef44ef")
    private String sign;

    @ApiModelProperty(value = "加密数据", example = "UEtOQVjewP7w7Uj2Tdik6fEeeHV4OmkUO6GktBKQ8unoxaSQJsxnp5eo+gmeZ1FTRV0/KeT7AmRRBlV5RdodMgcsogDe7Q0r9yOMn7UYfj85FB48a7rjdeA67Kr80OXJ7kh21gNY7jo84M1P+eXhnsPAhaO59rrgVjxliL2tOa1qIjMSo54MSJ2D/2JIBmWL3KXHHE0MqsgRS6DAgJ/PjPnNa9wgI0vXlSzNTy1fKvOYVZfjSyczambV2v1H41HhvolCeXv5MsR/1PiIPpfn/ylrQbDo8tLfpf6gYF6fGBoGse6fR7Wt/Mf7gOlK93lo1W5OMpKrIZQC7iVW7YqqIA==")
    private String dataSecret;

}
