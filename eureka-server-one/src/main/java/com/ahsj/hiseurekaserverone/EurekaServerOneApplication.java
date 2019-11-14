package com.ahsj.hiseurekaserverone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @Description HIS系统的第一个服务节点
 * @Params
 * @return
 * @Author chenzhicai
 * @Date 2019-02-28
 * @Time 20:05
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerOneApplication.class, args);
    }

}
