package com.dtguai.encrypt.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.InputStream;

/**
 * <p>解密信息输入流</p>
 *
 * @author guo
 * @date 2019年4月16日13:45:42
 */
@NoArgsConstructor
@AllArgsConstructor
public class DecryptHttpInputMessage implements HttpInputMessage {

    private InputStream body;

    private HttpHeaders headers;

    /**
     * @return InputStream body
     */
    @Override
    public InputStream getBody() {
        return body;
    }

    /**
     * @return HttpHeaders headers
     */
    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
