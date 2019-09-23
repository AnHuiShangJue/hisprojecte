package com.ahsj.baseuserinfo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 *@Description   用于进行方法边界鉴权
 *@Params
 *@return
 *@Author chenzhicai
 *@Date 2019-03-16
 *@Time 17:23
**/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public  class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

}
