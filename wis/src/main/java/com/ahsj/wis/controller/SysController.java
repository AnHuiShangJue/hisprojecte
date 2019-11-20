package com.ahsj.wis.controller;

import com.ahsj.wis.entity.Sys;
import com.ahsj.wis.service.SysService;
import core.message.Message;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "/api/sys", tags = "智慧湾后台管理")
@RestController
@RequestMapping("/api/sys")
public class SysController {

    @Autowired
    SysService sysService;

    /**
     *@功能说明 
     *@Params []
     *@return org.springframework.web.servlet.ModelAndView
     *@Author XJP
     *@Date 2019/11/20
     *@Time 14:10
    **/
    @GetMapping("/admin.ahsj")
    public ModelAndView listIndex() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/admins/list");
        List<Sys> sysAll = sysService.getSysAll();
        modelAndView.addObject("list", sysAll);
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [sys]
     * @Author XJP
     * @Date 2019/11/16
     * @Time 9:44
     **/
    @PostMapping("/add.ahsj")
    public Message addSys(Sys sys) throws Exception {
        Message message = sysService.addSys(sys);
        return message;
    }

    /**
     * @return java.util.List<com.ahsj.wis.entity.Sys>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/11/16
     * @Time 14:22
     **/
    @PostMapping("/getSysAll.ahsj")
    public List<Sys> getSysAll() throws Exception {
        List<Sys> sysAll = sysService.getSysAll();
        return sysAll;
    }
    @PostMapping("/delete.ahsj")
    public void delete(Sys sys) throws Exception {
        Message message = sysService.delete(sys);
        return;
    }
}