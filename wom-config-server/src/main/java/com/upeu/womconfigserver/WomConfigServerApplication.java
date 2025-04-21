package com.upeu.womconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class WomConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WomConfigServerApplication.class, args);
    }

}
