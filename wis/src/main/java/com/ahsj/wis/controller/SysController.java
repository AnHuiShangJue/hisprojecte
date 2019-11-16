package com.ahsj.wis.controller;
import com.ahsj.wis.entity.Sys;
import com.ahsj.wis.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysController
 * <p>
 * Date:     2019/11/15 17:33
 *
 * @author XJP
 * @create 2019/11/15
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/sys")
public class SysController {

    @Autowired
    SysService sysService;

    @RequestMapping("/admin.ahsj")
    public ModelAndView listIndex() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/admins/list");
        List<Sys> sysAll = sysService.getSysAll();
        modelAndView.addObject("list", sysAll);
        return modelAndView;
    }
}