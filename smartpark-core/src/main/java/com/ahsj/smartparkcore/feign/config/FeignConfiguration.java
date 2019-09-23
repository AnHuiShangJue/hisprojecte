package com.ahsj.smartparkcore.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: his
 * @description:FeignConfiguration
 * @author: chenzhicai
 * @create: 2019-06-19-15-37
 **/
@Configuration
public class FeignConfiguration
{
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

//    @Bean
//    public Decoder feignDecoder() {
//        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
//    }
}

    