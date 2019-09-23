package com.ahsj.userinfor.controller;

import com.ahsj.userinfor.entity.UserInfo;
import com.ahsj.userinfor.services.UserInfoService;
import com.ahsj.userinfor.services.UserService;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@Controller
@RequestMapping("/api/user")
public class UserInfoController extends BaseOAController{

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserService userService;

    @GetMapping("/current.ahsj")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }


    @RequestMapping(value = "/saveOrUpdateUserInfo.ahsj")
    @ResponseBody
    @PostAuthorize("hasAnyAuthority('sys.user.add','sys.user.update')")
    public Message saveOrUpdateUserInfo(UserInfo user)  {
        return userInfoService.saveOrUpdateUserInfo(user);
    }


    @RequestMapping("/info/saveOrUpdateUserInfo.ahsj")
    @ResponseBody
    public Message innerSaveOrUpdateUserInfo(@RequestBody UserInfo user)  {
        return userInfoService.saveOrUpdateUserInfo(user);
    }

    @RequestMapping("/delete.ahsj")
    @ResponseBody
    @PostAuthorize("hasAuthority('sys.user.delete')")
    public Message delete(Long[] id) throws Exception {
        return userInfoService.delete(id);
    }


    @RequestMapping("/info/inner/selectByName.ahsj")
    @ResponseBody
    public UserInfo selectByName(@NotNull String username){
        UserInfo userInfo=  userInfoService.selectByUserName(username);
        if(EmptyUtil.Companion.isNullOrEmpty(userInfo)){
            return new UserInfo();
        }else return userInfo;
    }





    @RequestMapping("/list.ahsj")
    @ResponseBody
    public PageBean<UserInfo> list(UserInfo userInfo){
        PageBean<UserInfo> pageBean = new PageBean<UserInfo>();
        pageBean.setParameter(userInfo);
        return userInfoService.list(pageBean);
    }

    @RequestMapping("/selectById.ahsj")
    @ResponseBody
    @PostAuthorize("hasAuthority('sys.operate')")
    public UserInfo selectById(Long id)  {
        return userInfoService.selectById(id);
    }

    @GetMapping("/query.ahsj")
    @ResponseBody
    @PreAuthorize("hasAuthority('user')")
    public String query() {
        return "具有query权限";
    }

    @RequestMapping("/update/index.ahsj")
    @PostAuthorize("hasAuthority('sys.user.update')")
    ModelAndView UpdateIndex(UserInfo userInfo){
        ModelAndView modelAndView = new ModelAndView("backend/sys/user/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(userInfo.getId())){
            modelAndView.addObject("userInfo",userInfoService.selectById(userInfo.getId()));
        }else{
            modelAndView.addObject("userInfo",userInfo);
        }
        modelAndView.addObject("title","人员信息添加");
        modelAndView.addObject("token", userInfo.getToken());
        return modelAndView;
    }

    @RequestMapping("/index/index.ahsj")
    @PostAuthorize("hasAuthority('sys.user')")
    ModelAndView UserIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/sys/user/list");
        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }

    @PostMapping("/select/userInfo/id.ahsj")
    @ResponseBody
    public UserInfo getUserInfoById(@RequestParam("id") Long id){
        UserInfo userInfo = userService.selectByPrimaryKey(id);
        if (userInfo !=null){
            return userInfo;
        }else {
            return  new UserInfo();
        }
    }

    @GetMapping("/select/userInfo/userId.ahsj")
    @ResponseBody
    public UserInfo getUserLoginId(@RequestParam("userId") String userId){
        UserInfo userLoginId = userService.getUserLoginId(userId);
        return userLoginId;
    }

    @GetMapping("/select/userInfo/id.ahsj")
    @ResponseBody
    public UserInfo getUserId(@RequestParam("id") Long id){
        UserInfo userLoginId = userService.getUserId(id);
        return userLoginId;
    }
}

