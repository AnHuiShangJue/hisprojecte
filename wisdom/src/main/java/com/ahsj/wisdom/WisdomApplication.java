package com.ahsj.wisdom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.wisdo.dao")
@SpringBootApplication(scanBasePackages = {"com.ahsj.wisdom","com.ahsj.wisdom.error"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@EnableFeignClients(value = "com.ahsj.wisdom")
@EnableScheduling
@RefreshScope
public class WisdomApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisdomApplication.class, args);
    }

}
