package com.vicsun.sportplayer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vicsun.sportplayer.dao")
@SpringBootApplication
public class SportplayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportplayerApplication.class, args);
    }

}
