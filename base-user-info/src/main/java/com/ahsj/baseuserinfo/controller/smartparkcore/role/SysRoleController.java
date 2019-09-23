package com.ahsj.baseuserinfo.controller.smartparkcore.role;


import core.controller.BaseController;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/sys/role")
public class SysRoleController extends BaseController {

/*    @Autowired
    ISysRoleService iSysRoleService;

    @Autowired
    IMenuService iMenuService;*/

    @RequestMapping("/list/index.ahsj")
    ModelAndView UserIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/smartparkcore/sys/role/list");
        modelAndView.addObject("title","智慧园区系统");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    /**
     *@功能说明 查询角色
     *@Params [role]
     *@return core.entity.PageBean<com.ahsj.userinfor.entity.Role>
     *@Author XJP
     *@Date 2019/9/9
     *@Time 11:03
 /*   **//*
    @PostMapping("/list.ahsj")
    @ResponseBody
    public PageBean<Role> list( Role role) throws Exception {
        return iSysRoleService.list(role);
    }*/

 
}
