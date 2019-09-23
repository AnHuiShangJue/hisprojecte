package com.ahsj.syssecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;



/*
*
*
*
* */

@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "userInfo";



    /*
    *
    *   这个地方配置的是授权服务器，认证服务器走的是Security
    * */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        //这个类是顶层授权
        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        DataSource dataSource;

//        @Autowired
//        private Environment env;
//        @Bean
//         public DataSource dataSource(){
//            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//            dataSource.setUrl(env.getProperty("spring.datasource.url"));
//            dataSource.setUsername(env.getProperty("spring.datasource.username"));
//            dataSource.setPassword(env.getProperty("spring.datasource.password"));
//            return dataSource;
//        };

        @Autowired
        RedisConnectionFactory redisConnectionFactory;


        //ClientDetailsServiceConfigurer 定义客户端详细信息服务的配置器。可以初始化客户端详细信息，也可以只引用现有商店。
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


            // 支持多种编码，通过密码的前缀区分编码方式
            String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("1a9030fbca47a5b2c28e92f19050bb77824b5ad1");
            //配置两个客户端,一个用于password认证一个用于client认证
            clients.inMemory().withClient("bd1c0a783ccdd1c9b9e4")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("select")
                    .authorities("oauth2")
                    .secret(finalSecret);
        }

        //AuthorizationServerEndpointsConfigurer 定义授权和令牌端点以及令牌服务。
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                    .tokenStore(new JdbcTokenStore(dataSource))
//                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }

        //AuthorizationServerSecurityConfigurer 定义令牌端点上的安全性约束。
        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }
    }

}
