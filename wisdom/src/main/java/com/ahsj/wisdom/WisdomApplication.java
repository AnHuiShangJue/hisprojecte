package com.ahsj.wisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@SpringBootApplication
public class WisdomApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisdomApplication.class, args);
    }

}
