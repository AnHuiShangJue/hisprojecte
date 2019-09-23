package com.anhuishangjue.zuulhis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *@Description 对外顶层网关
 *@Params 
 *@return 
 *@Author chenzhicai
 *@Date 2019-09-09
 *@Time 01:55
**/
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulHisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulHisApplication.class, args);
    }

}
