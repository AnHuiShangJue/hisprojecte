package com.ahsj.hiseurekasecurityauthorizationserverone;

import com.ahsj.hiseurekasecurityauthorizationserverone.service.CustomUserInfoTokenServices;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@MapperScan("com.ahsj.hiseurekasecuirtyauthorizationserverone.dao")
@EnableTransactionManagement //开启事务
@EnableCaching //开启缓存
@SpringBootApplication(scanBasePackages = {"com.ahsj.hiseurekasecuirtyauthorizationserverone.error"})
//开启Feign客户端配置
@EnableFeignClients
public class SecurityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication.class, args);
    }

}

