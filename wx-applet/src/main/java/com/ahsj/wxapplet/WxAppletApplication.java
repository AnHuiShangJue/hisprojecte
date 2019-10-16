package com.ahsj.wxapplet;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.wxapplet.dao")
@SpringBootApplication(scanBasePackages = {"com.ahsj"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
//开启Feign客户端配置
@EnableFeignClients(value = "com.ahsj")
//开启任务处理
@EnableScheduling
@RefreshScope
@RestController
public class WxAppletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxAppletApplication.class, args);
    }

    @PostMapping("/hi")
    public String getHi(){
        return "沐旭";
    }

}
