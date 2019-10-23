package com.ahsj.basesms;

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
@MapperScan("com.ahsj.basesms.dao")
@SpringBootApplication(scanBasePackages = {"com.ahsj.basesms"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
//开启任务处理
@EnableScheduling
public class BaseSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSmsApplication.class, args);
    }

}
