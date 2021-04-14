package com.dtguai.encrypt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * 测试
 *
 * @author guo
 * @date 2021年4月13日18:36:28
 */
@SpringBootApplication
@Controller
@Slf4j
public class TestStart {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestStart.class);
        app.run(args);
    }

}
