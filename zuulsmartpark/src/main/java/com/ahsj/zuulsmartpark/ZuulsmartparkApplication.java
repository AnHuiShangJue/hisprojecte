package com.ahsj.zuulsmartpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 *@Description 智慧园区网关层
 *@Params 
 *@return 
 *@Author chenzhicai
 *@Date 2019-08-20
 *@Time 12:43
**/
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulsmartparkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulsmartparkApplication.class, args);
    }

}
