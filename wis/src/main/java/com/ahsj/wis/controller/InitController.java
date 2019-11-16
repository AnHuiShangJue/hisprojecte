package com.ahsj.wis.controller;

import com.ahsj.wis.entity.Sys;
import com.ahsj.wis.service.SysService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: InitController
 * <p>
 * Date:     2019/11/6 11:31
 *
 * @author XJP
 * @create 2019/11/6
 * @since 1.0.0
 */

@RestController
public class InitController {

    @Autowired
    SysService sysService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView databasebackup() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/admins/list");
        List<Sys> sysAll = sysService.getSysAll();
        modelAndView.addObject("list", sysAll);
        return modelAndView;
    }


}
