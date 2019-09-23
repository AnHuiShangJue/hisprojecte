package com.ahsj.userinfor.controller;

import core.controller.BaseController;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/his/")
public class IndexController extends BaseController {

    @RequestMapping("back/index/index.ahsj")
    ModelAndView index(String token){
        ModelAndView modelAndView = new ModelAndView("backend/index");
        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }

/*    @RequestMapping("back/smartparkcore/index.ahsj")
    ModelAndView smartparkcoreIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/index");
        modelAndView.addObject("userName",getUserName());
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }*/




}
