package com.ahsj.hiseurekasecurityauthorizationserverone.config;

import com.ahsj.hiseurekasecurityauthorizationserverone.service.CustomUserInfoTokenServices;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public   class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String DEMO_RESOURCE_ID = "userInfo";

//
//    @Bean
//    public ResourceServerTokenServices myUserInfoTokenServices() {
//        return new CustomUserInfoTokenServices("http://localhost:7000/user", "eureka-userinfo");
//    }

    //通过配置ResourceServerSecurityConfigurer设置被保护的OAuth的资源
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//        resources.tokenServices(myUserInfoTokenServices());
    }

    //通过配置HttpSecurity来设置被保护的URL
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //保护用户请求接口
        http
                .authorizeRequests()
                .antMatchers("/userinfo/**").authenticated();//配置order访问控制，必须认证过后才可以访问

    }
}
