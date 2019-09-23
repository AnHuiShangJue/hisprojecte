package com.ahsj.hiseurekasecurityauthorizationserverone.feign

import com.ahsj.hiseurekasecurityauthorizationserverone.entity.UserInfo
import com.ahsj.hiseurekasecurityauthorizationserverone.fallback.IUserServiceFallback
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 *@Description  该接口调用用户模块
 *@Params
 *@return
 *@Author chenzhicai
 *@Date 2019-03-15
 *@Time 18:16
**/

@FeignClient(name = "eureka-userinfo",fallback = IUserServiceFallback::class, path = "/api/user/")
interface IUserService {

    /**
     *@Description   远程调用用户信息模块的查询账号，该路由为内网路由，外网不可访问
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-04-08
     *@Time 0:35
     **/
    @PostMapping("info/inner/selectByName.ahsj")
    fun selectByUserName(@RequestParam("username") userName: String): UserInfo?

    object demo{
        @JvmStatic
        fun main(args:Array<String>){
            println(1111)
        }
    }
}