package com.dandan.playserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JustYao
 */
@EnableFeignClients
@SpringBootApplication
@RestController
public class PlayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayServerApplication.class, args);
    }

    @GetMapping
    public String ping(){
        return "TimeStamp:"+System.currentTimeMillis();
    }
}
