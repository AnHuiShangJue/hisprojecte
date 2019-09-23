package com.ahsj.userinfor;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

@SpringCloudApplication
@EnableDiscoveryClient
@MapperScan("com.ahsj.userinfor.dao")
@ComponentScan(basePackages = {"com.ahsj.userinfor","com.ahsj.userinfor.error"})
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@EnableFeignClients

public class UserInforApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserInforApplication.class, args);
    }

}
