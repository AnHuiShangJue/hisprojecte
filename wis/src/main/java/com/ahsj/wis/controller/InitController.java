package com.ahsj.wis.controller;

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

    @RequestMapping(value = "/",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView databasebackup() throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/wis/home");
        return modelAndView;
        }
    }
