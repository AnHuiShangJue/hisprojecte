package com.ahsj.baseuserinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.baseuserinfo.dao")
@ComponentScan(basePackages = {"com.ahsj.baseuserinfo","com.ahsj.baseuserinfo.error"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@EnableFeignClients

public class BaseUserInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseUserInfoApplication.class, args);
    }

}
