package com.ahsj.wis;

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
@MapperScan("com.ahsj.wis.dao")
@SpringBootApplication(scanBasePackages = {"com.ahsj.wisdom"})
@EnableTransactionManagement //开启事务
public class WisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisApplication.class, args);
    }

}
