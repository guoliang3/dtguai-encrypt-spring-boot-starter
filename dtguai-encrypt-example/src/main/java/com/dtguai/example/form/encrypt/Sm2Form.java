package com.dtguai.example.form.encrypt;

import com.dtguai.example.model.User;
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
    private User user;

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

    @ApiModelProperty(value = "加密数据", example = "0485E123101AA1F27279E01E1A27F18B567F7BE5938164A67A4BBB5EBD5B6E9D6800AFDD31C217A20BAE5AA7BF522A50E15054A7D2377FC812F0B2AEB2C7595C9CB002CF5416F450DF8F2A9DA1850F38D09FED8E61C6826546C9DA3F86259562A8C4385D1BEB93D02E3B163E4E215901A34E765D33E82E4547CE12BF97C9C32D7C060E41F053CDEEEBE28F569C323A4EF0F783C424C6479E899BB00F2E8BCC9B24001CDC30C33A9F0CBB94EB6F751B8B8A8C1C5DAEA383691EAB56AE26F94DF4250834C4235C7DFA9958543EB688171F51F139D35C56BF91095A9CA2AF0FC2C5BAB9A7F8E7FF8B6F5A6DA6826C0E73BBEF847437BCD764444558D69383D50AC421F5639556823CFE6ED6D8E96047F3E586E8A3BB63039B90A686E4BCDF7D27D61EBD4F90F003A2744ECAAFCA9073B2E74DCDC594F0B2C8DA")
    private String dataSecret;

}
