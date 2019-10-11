package com.ahsj.eureka_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EurekaConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConfigServerApplication.class, args);
    }

}
