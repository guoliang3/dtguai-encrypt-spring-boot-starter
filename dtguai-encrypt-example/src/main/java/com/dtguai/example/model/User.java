package com.dtguai.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表
 *
 * @author guoLiang
 * @date 2021年2月23日17:40:37
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @JsonIgnore
    private String password;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer type;
    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private Long expire;
    /**
     * imei号
     */
    @ApiModelProperty(value = "imei号")
    private String imei;

}