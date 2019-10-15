package com.ahsj.payalipay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.payAlipay.dao")
@ComponentScan(basePackages = "com.ahsj")
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@EnableFeignClients
public class PayAlipayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayAlipayApplication.class, args);
    }

    @PostMapping("/api/hi")
    public String getHi(){
        return "订立";
    }

}
