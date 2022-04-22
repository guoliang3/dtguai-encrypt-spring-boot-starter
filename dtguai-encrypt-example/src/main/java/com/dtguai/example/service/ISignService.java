package com.dtguai.example.service;

/**
 * sign service
 *
 * @author guo
 */
public interface ISignService {
    /**
     * 根据json 生成签名
     *
     * @param json json
     * @return String
     */
    String getSign(String json);
}
