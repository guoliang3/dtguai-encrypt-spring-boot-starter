
package com.dtguai.example.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guo
 * @date 2018年8月24日上午11:32:13
 */
@Controller
@RequestMapping()
@Slf4j
public class Csrf {

    public final String CSRF_OUT = "----------------csrf---------------";

    @ResponseBody
    @RequestMapping(value = "csrf", produces = "application/text;charset=UTF-8")
    public String csrf() {
        log.info(CSRF_OUT);

        log.info("-------------------CSRF服务启动完成!{}--第一:{}-第二:{}-第三:{}-第四:{}-------------", CSRF_OUT, 1, 2, 3, 4);
        log.error("-------------------CSRF服务启动完成!{}--第一:{}-第二:{}-第三:{}-第四:{}-------------", CSRF_OUT, 1, 2, 3, 4);
        log.warn("-------------------CSRF服务启动完成!{}--第一:{}-第二:{}-第三:{}-第四:{}-------------", CSRF_OUT, 1, 2, 3, 4);
        log.debug("-------------------CSRF服务启动完成!{}--第一:{}-第二:{}-第三:{}-第四:{}-------------", CSRF_OUT, 1, 2, 3, 4);

        throw new NullPointerException();
        //return CSRF_OUT;
    }

}
