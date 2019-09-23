package com.ahsj.userinfor.controller.smartparkcore;

import com.ahsj.userinfor.entity.Menu;
import com.ahsj.userinfor.feign.IMenuService;
import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SmartparkcoreController
 * <p>
 * Date:     2019/9/6 15:09
 *
 * @author XJP
 * @create 2019/9/6
 * @since 1.0.0
 */

@RestController
@RequestMapping("/his/back")
public class SmartparkcoreController extends BaseController {

    @Autowired
    IMenuService iMenuService;


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 智慧园区系统 首页
     * @Params [token]
     * @Author XJP
     * @Date 2019/9/6
     * @Time 15:12
     **/
    @RequestMapping("/smartparkcore/index.ahsj")
    ModelAndView smartparkcoreIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/index");
        modelAndView.addObject("userName", getUserName());
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
//        modelAndView.addObject("roleName",getRoleName());
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明 智慧园区系统 登录
     * @Params []
     * @Author XJP
     * @Date 2019/9/6
     * @Time 15:11
     **/
    @RequestMapping("/smartparkcore/login/index.ahsj")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/login");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("index", "");
        return modelAndView;
    }

    @PostMapping("/smartparkcore/menu/layout/lstMenu.ahsj")
    public Map<String, String> listMenu() throws Exception {
        Map<String, String> map = iMenuService.listMenu(getId());
        return map;
    }


}
