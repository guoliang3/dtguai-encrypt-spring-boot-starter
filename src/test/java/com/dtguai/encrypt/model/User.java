package com.dtguai.encrypt.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户表
 *
 * @author guoLiang
 * @date 2021年2月23日17:40:37
 */
public class User {


    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private final Integer id = 1;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private final String mobile = "18600231919";
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private final String name = "克隆人";

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private final Date createTime = new Date();


}