package com.ahsj.smartparkcore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.smartparkcore.dao")
@SpringBootApplication(scanBasePackages = {"com.ahsj.smartparkcore","com.ahsj.smartparkcore.error"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
//开启Feign客户端配置
@EnableFeignClients(value = "com.ahsj.smartparkcore.feign")
//开启任务处理
@EnableScheduling
public class SmartparkCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartparkCoreApplication.class, args);
    }

}
