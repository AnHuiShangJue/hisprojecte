package com.ahsj.smartparkcore.config;


import com.ahsj.smartparkcore.core.CustomTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerProperties sso;


    @Bean
    public ResourceServerTokenServices myUserInfoTokenServices() {
        return new CustomTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(myUserInfoTokenServices());
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/info/inner/**").permitAll()  // 内部用户信息访问路由全部开放
                .antMatchers("/his/back/**").permitAll()  // 内部用户信息访问路由全部开放
                .antMatchers("/api/role/info/inner/**").permitAll()  // 内部角色信息访问路由全部开放
                .antMatchers("/api/permission/info/inner/**").permitAll()  // 内部角色信息访问路由全部开放
                .antMatchers("/api/operate/info/inner/**").permitAll()  // 内部角色信息访问路由全部开放
                .antMatchers("/api/menu/info/inner/**").permitAll()  // 内部角色信息访问路由全部开放
                .antMatchers("/assets/**").permitAll()  // 资源文件开放
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
