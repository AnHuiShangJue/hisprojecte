package com.ahsj.wisdom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/wisdombay/")
public class WisdomBayController {

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/test1");
        modelAndView.addObject("title", "");
        return modelAndView;
    }

    @RequestMapping("/lanhu/index.ahsj")
    ModelAndView lanhuIndex() {
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/index");
        modelAndView.addObject("title", "");
        return modelAndView;
    }

    /**
     *@Description 跳转至联系我们页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/4
     *@Time 11:43
    */
    @RequestMapping("/contactus/index.ahsj")
    ModelAndView contactusIndex(){
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/contact_us");
        modelAndView.addObject("title","联系我们");
        return modelAndView;
    }

    /**
     *@Description 跳转关于智慧湾页面
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/4
     *@Time 11:41
    */
    @RequestMapping("/aboutwisdom/index.ahsj")
    ModelAndView aboutwisdomIndex(){
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/aboutwisdom");
        modelAndView.addObject("title","关于智慧湾");
        return modelAndView;
    }
    @RequestMapping("/organization/index.ahsj")
    ModelAndView organization(){
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/organization");
        modelAndView.addObject("title","");
        return modelAndView;
    }
}
