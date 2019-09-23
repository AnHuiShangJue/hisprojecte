package com.ahsj.syssecurity.fallback

import com.ahsj.syssecurity.entity.UserInfo
import com.ahsj.syssecurity.feign.IUserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController


@RestController
class IUserServiceFallback : IUserService {

    internal var logger = LoggerFactory.getLogger(IUserServiceFallback::class.java)


    override fun selectByUserName(userName: String): UserInfo? {
        logger.error("用户服务无法访问!")
        return null;
    }


}