package com.ahsj.syssecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@MapperScan("com.ahsj.syssecurity.dao")
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@SpringBootApplication(scanBasePackages = {"com.ahsj.syssecurity.error"})
//开启Feign客户端配置
@EnableFeignClients
public class SysSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysSecurityApplication.class, args);
    }

}
