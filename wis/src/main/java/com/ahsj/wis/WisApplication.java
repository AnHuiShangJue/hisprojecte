package com.ahsj.wis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan("com.ahsj.wis.dao")
@SpringBootApplication
@EnableTransactionManagement //开启事务
public class WisApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WisApplication.class, args);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/File/**")
                .addResourceLocations("file:File/");
    }

}
