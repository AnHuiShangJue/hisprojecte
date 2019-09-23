package com.ahsj.userinfor.controller.smartparkcore.menu;

import com.ahsj.userinfor.entity.Menu;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.entity.dto.ParamVo;
import com.ahsj.userinfor.feign.IMenuService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysMenuController
 * <p>
 * Date:     2019/9/7 14:31
 *
 * @author XJP
 * @create 2019/9/7
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/sys/menu")
public class SysMenuController {

    @Autowired
    IMenuService iMenuService;

    @GetMapping("/list/index.ahsj")
    public ModelAndView UserIndex(String token) {
        ModelAndView modelAndView = new ModelAndView("backend/sys/menu/sys/list");
        modelAndView.addObject("title", "智慧园区系统");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    @RequestMapping("/list.ahsj")
    @ResponseBody
    public PageBean<Menu> list(Menu sysMenu) throws Exception {
        return iMenuService.list(sysMenu);
    }

    @PostMapping("/treeCode.ahsj")
    @ResponseBody
    public List<TreeEntity> treeCode( Integer lv,String id,String token) throws Exception {
        ParamVo paramVo = new ParamVo();
        paramVo.setId(id);
        paramVo.setLv(lv);
        return iMenuService.treeCode(paramVo);
    }
}
