package com.ahsj.sysuserinfo.controller;

import com.ahsj.sysuserinfo.entity.UserInfo;
import com.ahsj.sysuserinfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utils.EmptyUtil;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/api/sysuserinfo")
public class UserInfoController extends BaseOAController{

   @Autowired
   private UserService userService;

   @RequestMapping("/info/inner/selectByName.ahsj")
   @ResponseBody
   public UserInfo selectByName(@RequestParam("username") String username) throws Exception {
      UserInfo userInfo=  userService.selectByUserName(username);
      if(EmptyUtil.Companion.isNullOrEmpty(userInfo)){
         return new UserInfo();
      }else return userInfo;
   }

}

