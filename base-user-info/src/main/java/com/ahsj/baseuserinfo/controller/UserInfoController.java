package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.entity.UserInfo;
import com.ahsj.baseuserinfo.services.UserService;
import core.entity.PageBean;
import core.message.Message;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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

    @RequestMapping("/jscode2session.ahsj") // 登录
    @ResponseBody
    public String jscode2session(HttpServletRequest req
                                  ,@RequestParam(value="js_code", required=true) String js_code
                                  , @RequestParam(value="appId", required=true) String appId
                                  ,@RequestParam(value="secret", required=true) String secret) throws ClientProtocolException, IOException {


        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+js_code+"+&grant_type=authorization_code";

        url=url.replaceAll("JSCODE", js_code);

 //执行get请求.
        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
// 获取响应实体
       String html = EntityUtils.toString(response.getEntity());

        return html;

    }
}

