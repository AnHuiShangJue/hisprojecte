package com.ahsj.syssecurity.controller;


import com.ahsj.syssecurity.feign.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    /*
    * 应该注入服务接口，这个地方为了测试，直接注入数据服务层接口进行访问数据库数据
    * */
    @Autowired
    IUserService userInfoService;


    @GetMapping("/user")
    public Principal user(Principal user)
    {
        return  user;
    }
}
