package com.dtguai.example.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoLiang
 * @date 2021年4月29日16:45:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    @ApiModelProperty(value = "返回代码")
    private int code = 200;

    @ApiModelProperty(value = "返回信息")
    private String msg = "OK";

    @ApiModelProperty(value = "返回时间")
    private String timestamp;

    @ApiModelProperty(value = "返回数据")
    @JsonInclude(Include.NON_NULL)
    private T result;

    public ApiResponse(T t) {
        this.result = t;
    }

}
