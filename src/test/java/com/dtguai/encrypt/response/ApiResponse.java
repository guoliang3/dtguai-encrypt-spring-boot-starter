package com.dtguai.encrypt.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author guoLiang
 * @date 2021年4月28日18:06:26
 */
@Data
public class ApiResponse<T> {

    @ApiModelProperty(value = "返回代码")
    private int code = 200;
    @ApiModelProperty(value = "返回信息")
    private String msg = "test";
    @ApiModelProperty(value = "返回时间")
    private String timestamp;
    @ApiModelProperty(value = "返回数据")

    @JsonInclude(Include.NON_NULL)
    private T result;


    public ApiResponse(T t) {
        this.result = t;
    }


}
