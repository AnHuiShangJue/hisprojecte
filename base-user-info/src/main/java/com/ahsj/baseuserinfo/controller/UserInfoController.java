package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.entity.UserInfo;
import com.ahsj.baseuserinfo.services.UserService;
import com.alibaba.fastjson.JSONObject;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserService userService;

    @GetMapping("/current.ahsj")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }


    @RequestMapping(value = "/saveOrUpdateUserInfo.ahsj")
    @ResponseBody
    public Message saveOrUpdateUserInfo(UserInfo user)  {
        return userService.saveOrUpdateUserInfo(user);
    }


    @RequestMapping("/info/saveOrUpdateUserInfo.ahsj")
    @ResponseBody
    public Message innerSaveOrUpdateUserInfo(@RequestBody UserInfo user)  {
        return userService.saveOrUpdateUserInfo(user);
    }

    @RequestMapping("/delete.ahsj")
    @ResponseBody
    public Message delete(Long[] id) throws Exception {
        return userService.delete(id);
    }


    @RequestMapping("/info/inner/selectByName.ahsj")
    @ResponseBody
    public UserInfo selectByName(@NotNull String username){
        UserInfo userInfo=  userService.selectByUserName(username);
        if(EmptyUtil.Companion.isNullOrEmpty(userInfo)){
            return new UserInfo();
        }else return userInfo;
    }





    @RequestMapping("/list.ahsj")
    @ResponseBody
    public PageBean<UserInfo> list(UserInfo userInfo){
        PageBean<UserInfo> pageBean = new PageBean<UserInfo>();
        pageBean.setParameter(userInfo);
        return userService.list(pageBean);
    }

    @RequestMapping("/selectById.ahsj")
    @ResponseBody
    public UserInfo selectById(Long id)  {
        return userService.selectById(id);
    }

    @GetMapping("/query.ahsj")
    @ResponseBody
    @PreAuthorize("hasAuthority('user')")
    public String query() {
        return "具有query权限";
    }

    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(UserInfo userInfo){
        ModelAndView modelAndView = new ModelAndView("backend/sys/user/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(userInfo.getId())){
            modelAndView.addObject("userInfo",userService.selectById(userInfo.getId()));
        }else{
            modelAndView.addObject("userInfo",userInfo);
        }
        modelAndView.addObject("title","人员信息添加");
        modelAndView.addObject("token", userInfo.getToken());
        return modelAndView;
    }

    @RequestMapping("/index/index.ahsj")
    ModelAndView UserIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/sys/user/list");
        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","智慧园区系统");
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

    @RequestMapping("/info/inner/jscode2session.ahsj") // 登录
    @ResponseBody
    public String jscode2session(HttpServletRequest req
                                  ,@RequestParam(value="js_code", required=false) String js_code
                                  , @RequestParam(value="appId", required=false) String appId
                                  ,@RequestParam(value="secret", required=false) String secret) throws Exception {
        String params = "appid="+appId+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        JSONObject json = JSONObject.parseObject(sr);
        String openid = (String) json.get("openid");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(openid);
        userInfo.setPassword(openid);
        userInfo.setUserName(openid);
        userService.saveOrUpdateUserInfo(userInfo);
        System.out.println("openid:" + openid);
        System.out.println("sr:" + sr);
        return openid;
    }
}

