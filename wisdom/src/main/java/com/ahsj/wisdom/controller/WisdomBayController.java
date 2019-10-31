package com.ahsj.wisdom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/wisdombay/")
public class WisdomBayController {

    @RequestMapping("/list/index.ahsj")
    ModelAndView listIndex(){
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/wisdom_bay");
        modelAndView.addObject("title","医疗信息系统");
        return modelAndView;
    }
}