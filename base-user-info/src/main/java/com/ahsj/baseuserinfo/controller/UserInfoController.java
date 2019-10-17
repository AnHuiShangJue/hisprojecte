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

    @RequestMapping("/jscode2session.ahsj") // 登录
    @ResponseBody
    public String jscode2session(HttpServletRequest req
                                  ,@RequestParam(value="js_code", required=false) String js_code
                                  , @RequestParam(value="appId", required=false) String appId
                                  ,@RequestParam(value="secret", required=false) String secret) throws Exception {
        //String sr = "https://api.weixin.qq.com/sns/jscode2session?appid=wx9eec243b070bf766&secret=8963ca9ce2dd28783d7373b920324474&js_code=043KnI5X0iQfZV1piL4X02CJ5X0KnI59&grant_type=authorization_code";
/*        Map<String,Object> map = new HashMap<String,Object>();
        if (js_code == null || js_code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "js_code 不能为空");
            System.out.println("map1:" + map);
            return map;
        }
/*        String params = "appid="+appId+"&secret="+secret+"&js_code="+js_code+"+&grant_type=authorization_code";
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);*/
        String params = "appid="+appId+"&secret="+secret+"&js_code="+js_code+"&grant_type=authorization_code";
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        JSONObject json = JSONObject.parseObject(sr);
        String openid = (String) json.get("openid");
        System.out.println("openid:" + openid);
        System.out.println("sr:" + sr);
        return sr;
/*        try {
            String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map<String,Object> userInfo = new HashMap<String,Object>();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                System.out.println("map2:" + map);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        System.out.println("map3:" + map);
        return map;*/

/*
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String sr = response.body().string();
            JSONObject json = JSONObject.parseObject(sr);
            String session_key = json.get("session_key").toString();
            String openid = (String) json.get("openid");
                return  openid;
        }*/


//方法三
/*        url=url.replaceAll("JSCODE", js_code);

 //执行get请求.
        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet(url));
 //获取响应实体
       String html = EntityUtils.toString(response.getEntity());
        return html;*/



    }

}

