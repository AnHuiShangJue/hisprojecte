package com.ahsj.smartparkcore.feign;

import com.ahsj.smartparkcore.entity.UserInfo;
import com.ahsj.smartparkcore.core.BoolMessage;

import com.ahsj.smartparkcore.fallback.factory.IUserServiceFallbackFactory;
import com.ahsj.smartparkcore.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-zuul-outer-one", fallbackFactory = IUserServiceFallbackFactory.class, path = "accounts/api/user/", configuration = FeignConfiguration.class)
public interface IUserService {

    /**
     * @return core.message.Message
     * @Description SaveOrUpdateUserInfo
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-06-13
     * @Time 18:32
     **/
    @PostMapping(value = "info/saveOrUpdateUserInfo.ahsj", consumes = "application/json")
    @LoadBalanced
    public BoolMessage saveOrUpdateUserInfo(@RequestBody UserInfo user) throws Exception;
//    public Message innerSaveOrUpdateUserInfo(@RequestBody UserInfo user,@RequestHeader("X-Auth-Token") String header) throws Exception;


    /**
     * @return
     * @Description selectByName
     * @Params
     * @Author chenzhicai
     * @Date 2019-06-18
     * @Time 16:43
     **/
    @PostMapping(value = "info/inner/selectByName.ahsj")
    @LoadBalanced
    UserInfo selectByName(@RequestParam("username") String username) throws Exception;

    @GetMapping(value = "select/userInfo/userId.ahsj")
    @LoadBalanced
    UserInfo getUserLoginId(@RequestParam("userId") String userId) throws Exception;

    @PostMapping("select/userInfo/id.ahsj")
    @LoadBalanced
    UserInfo getUserInfoById(@RequestParam("id") Long id) throws Exception;

    @GetMapping("/select/userInfo/id.ahsj")
    @LoadBalanced
    UserInfo getUserId(@RequestParam("id") Long id);

}
