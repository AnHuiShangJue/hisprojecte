package com.ahsj.sysuserinfo;

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
@MapperScan("com.ahsj.sysuserinfo.dao")
@ComponentScan(basePackages = {"com.ahsj.sysuserinfo","com.ahsj.sysuserinfo.error"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@EnableFeignClients
public class SysUserInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysUserInfoApplication.class, args);
    }

}
