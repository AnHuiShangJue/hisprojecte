package com.ahsj.userinfor.config

import com.ahsj.baseuserinfo.core.PageInterceptor
import org.apache.ibatis.plugin.Interceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @program: squarenavigationsys
 * @description:
 * @author:  chenzhicai
 * @create: 2018-11-12-01-13
 **/
@Configuration
open class MyBatisConfig {


@Bean
open fun getInterceptor(): Interceptor {
    return PageInterceptor()
}
}