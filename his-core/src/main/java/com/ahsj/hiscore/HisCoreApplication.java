package com.ahsj.hiscore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.hiscore.dao")
@SpringBootApplication(scanBasePackages = {"com.ahsj.hiscore","com.ahsj.hiscore.error"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
//开启Feign客户端配置
@EnableFeignClients(value = "com.ahsj.hiscore.feign")
//开启任务处理
@EnableScheduling
public class HisCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisCoreApplication.class, args);
    }

}



