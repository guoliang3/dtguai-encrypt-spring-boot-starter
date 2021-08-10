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
@ApiModel(value = "测试-sm2")
@EqualsAndHashCode(callSuper = false)
public class Sm2Form {

    @ApiModelProperty(hidden = true)
    private String mobile;

    @ApiModelProperty(hidden = true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String timestamp;

    @ApiModelProperty(value = "数字签名", example = "cb77cdd3d390f7d40a6f36af937ceb50")
    private String sign;

    /**
     * sm2--生成秘钥---privateKeys:MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgqQdt07JbioPh5BFjlRyARaw8dD8XHsBibDmi7ViM60KgCgYIKoEcz1UBgi2hRANCAATO99y6x8+H/NQP5G8WgnX9nsZGkkMwdtVZeno6zf8glbSuKmzg7I7o69Wp66yxcL9lrQ8BiF4fUbD9bkFP27tc
     * sm2--生成秘钥---publicKeys:MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEzvfcusfPh/zUD+RvFoJ1/Z7GRpJDMHbVWXp6Os3/IJW0rips4OyO6OvVqeussXC/Za0PAYheH1Gw/W5BT9u7XA==
     */
    @ApiModelProperty(value = "加密数据", example = "0472ED9E2DD0040B5036B15E5215F81A0D7C7E15AB85607E17AEC62ADC4DBEA31ED59249C60AEF36E9A6C39470D141B4A2872FD906691073D2781C103F659FD7295922DFC937B2430AC3A4350E5D4F34A1848183825C5C7F0D13EC96C5EA29B425B89501753D272AB16495F65CCF4606B385BCD979C582FA367CE407885EFC143CAE04D75962CAD5054858BD6091E514A606AC3567BA274532F63B6473A8F7D3F4311C7CAF32A7136EA44C1D43E497B9E4861096154A64198C7F5EC881437EE474E4E0A2E3677B0C50655D158602D6B3F9A1CFAF8812B6746D7230FF986AFA8D2C7710484B775DB6C7ECFB9829CE74B087991D3A48486848656BAF903E02EACC87FCB00FA4CF76208533F3F709F9")
    private String dataSecret;

}
