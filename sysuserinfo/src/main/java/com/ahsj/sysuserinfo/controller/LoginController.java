package com.ahsj.sysuserinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("his/")
public class LoginController {


    @RequestMapping("back/login/index.ahsj")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("backend/login");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("index", "");
        return modelAndView;
    }
    @RequestMapping("back/smartparkcore/login/index.ahsj")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/login");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("index", "");
        return modelAndView;
    }



}
