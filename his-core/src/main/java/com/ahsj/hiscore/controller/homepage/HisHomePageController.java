package com.ahsj.hiscore.controller.homepage;

import core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/homepage/")
public class HisHomePageController extends BaseController {
    
    /**
     *@Description 首页前端（数据统计）
     *@Params [token]
     *@return org.springframework.web.servlet.ModelAndView
     *@Author zhushixiang
     *@Date 2019-07-29
     *@Time 15:25
    **/
    @RequestMapping("list/index.ahsj")
    ModelAndView listIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/hiscore/statisticalAnalysis/dataStatistics");
//        modelAndView.addObject("name",getUserName());
        modelAndView.addObject("title","数据统计");
        modelAndView.addObject("token",token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }
}
