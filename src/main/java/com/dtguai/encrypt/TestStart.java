package com.dtguai.encrypt;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @author com/tellhow
 * @date 2021年3月18日17:52:40
 */
@SpringBootApplication
@Controller
@Slf4j
public class TestStart implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(TestStart.class, args);
    }

    @Override
    public void run(String... args) {
        LocalDateTime time = LocalDateTime.now();
        log.info("--------------------服务启动完成!{}--------------------", time);
        log.error("--------------------服务启动完成!{}--------------------", time);
        log.warn("--------------------服务启动完成!{}--------------------", time);
        log.debug("-------------------服务启动完成!{}--------------------", time);
    }

}
