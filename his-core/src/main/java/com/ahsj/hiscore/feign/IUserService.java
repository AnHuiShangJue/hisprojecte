package com.ahsj.hiscore.feign;

import com.ahsj.hiscore.core.BoolMessage;
import com.ahsj.hiscore.entity.UserInfo;
import com.ahsj.hiscore.fallback.factory.IUserServiceFallbackFactory;
import com.ahsj.hiscore.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "eureka-zuul-inner",fallbackFactory = IUserServiceFallbackFactory.class,path="accounts/api/user/",configuration = FeignConfiguration.class)
public interface IUserService {

    /**
     *@Description SaveOrUpdateUserInfo
     *@Params [id]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019-06-13
     *@Time 18:32
     **/
    @PostMapping(value = "info/saveOrUpdateUserInfo.ahsj",consumes = "application/json")
    @LoadBalanced
    public BoolMessage saveOrUpdateUserInfo(@RequestBody UserInfo user) throws Exception;
//    public Message innerSaveOrUpdateUserInfo(@RequestBody UserInfo user,@RequestHeader("X-Auth-Token") String header) throws Exception;


    /**
     *@Description selectByName
     *@Params
     *@return
     *@Author chenzhicai
     *@Date 2019-06-18
     *@Time 16:43
     **/
    @PostMapping(value = "info/inner/selectByName.ahsj")
    @LoadBalanced
    UserInfo selectByName(@RequestParam("username") String username) throws Exception;

    @GetMapping(value = "select/userInfo/userId.ahsj")
    @LoadBalanced
    UserInfo getUserLoginId(@RequestParam("userId") String userId) throws Exception;

    @PostMapping("select/userInfo/id.ahsj")
    @LoadBalanced
    UserInfo getUserInfoById(@RequestParam("id") Long id)throws Exception;

    @GetMapping("/select/userInfo/id.ahsj")
    @LoadBalanced
     UserInfo getUserId(@RequestParam("id") Long id);

    @PostMapping(value = "info/saveOrUpdateUserInfo.ahsj")
    @LoadBalanced
    BoolMessage saveOrUpdateUserInfo(@RequestParam("userId") String userId,
                                             @RequestParam("password") String password,
                                             @RequestParam("userName") String userName,
                                             @RequestParam("sex") String sex,
                                             @RequestParam("deptId") String deptId,
                                             @RequestParam("delFlag") String delFlag,
                                             @RequestParam("isInitData") String isInitData,
                                             @RequestParam("telPhone") String telPhone);
}
