package com.dtguai;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author com/tellhow
 * @date 2021年3月9日10:20:39
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
        log.info("--------------------服务启动完成!{}--------------------", LocalDateTime.now());
    }

    @GetMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/doc.html");
    }


}
