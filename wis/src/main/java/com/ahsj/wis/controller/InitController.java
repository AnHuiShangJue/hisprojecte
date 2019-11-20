package com.ahsj.wis.controller;

import com.ahsj.wis.entity.Sys;
import com.ahsj.wis.entity.WisdomIndex;
import com.ahsj.wis.service.SysService;
import com.ahsj.wis.service.WisdomIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(value = "/", tags = "智慧湾初始化")
@RestController
public class InitController {
    @Autowired
    WisdomIndexService wisdomIndexService;


    @Autowired
    SysService sysService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/11/20
     * @Time 13:42
     **/
    @ApiOperation(value = "进入智慧湾首页", notes = "进入智慧湾首页")
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView databasebackup() throws Exception {
        List<WisdomIndex> wisdomIndices = wisdomIndexService.selectAll();
        WisdomIndex wisdomIndex = wisdomIndices.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wis/index");
        modelAndView.addObject("title", "欢迎页");
        modelAndView.addObject("wisdomIndex", wisdomIndex);
        return modelAndView;
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/11/20
     * @Time 13:42
     **/
    @ApiOperation(value = "进入智慧湾后台管理页面", notes = "进入智慧湾后台管理页面")
    @GetMapping("/admin.ahsj")
    public ModelAndView listIndex() throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/admins/list");
        List<Sys> sysAll = sysService.getSysAll();
        modelAndView.addObject("list", sysAll);
        return modelAndView;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/11/20
     * @Time 13:43
     **/
    @ApiOperation(value = "进入智慧湾修改页面", notes = "进入智慧湾修改页面")
    @RequestMapping("/update.ahsj")
    ModelAndView lanhuIndexUpdate() throws Exception {
        List<WisdomIndex> wisdomIndices = wisdomIndexService.selectAll();
        WisdomIndex wisdomIndex = wisdomIndices.get(0);
        ModelAndView modelAndView = new ModelAndView("backend/wisdom/index_update");
        modelAndView.addObject("title", "欢迎页");
        modelAndView.addObject("wisdomIndex", wisdomIndex);
        return modelAndView;
    }
}
