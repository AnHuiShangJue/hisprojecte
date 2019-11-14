package com.ahsj.wis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.ahsj.wis.dao")
@SpringBootApplication
@EnableTransactionManagement //开启事务
public class WisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisApplication.class, args);
    }

}
