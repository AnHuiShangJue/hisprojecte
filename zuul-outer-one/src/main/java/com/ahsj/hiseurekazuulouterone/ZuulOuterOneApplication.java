package com.ahsj.hiseurekazuulouterone;

import com.ahsj.hiseurekazuulouterone.Filter.AccessFilter;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


/**
 *@Description  HIS对外网关层
 *@Params
 *@return
 *@Author chenzhicai
 *@Date 2019-02-28
 *@Time 20:34
**/
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulOuterOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulOuterOneApplication.class, args);
    }


    /**
     *@Description 资源过滤器
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-03-15
     *@Time 22:28
    **/
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        System.out.println("START");
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
            System.out.println("START");
            connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}新生儿入院护理评估记录单");
            connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}新生儿入院护理评估记录单");
        });
        return factory;
    }


}
