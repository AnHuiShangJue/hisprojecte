package com.ahsj.userinfor.controller;

import com.ahsj.userinfor.entity.Role;
import com.ahsj.userinfor.entity.SelectEntity;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.entity.UserInfo;
import com.ahsj.userinfor.services.RoleService;
import core.controller.BaseController;
import core.entity.PageBean;
import core.message.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/selectById")
    private List<Role> selectRoleByUserId(@NotNull Long id) throws Exception {
        return roleService.selectRoleByUserId(id);
    }


    @RequestMapping("/list/index.ahsj")
    ModelAndView UserIndex(String token){
        ModelAndView modelAndView = new ModelAndView("backend/sys/role/list");
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
        return modelAndView;
    }

    @RequestMapping("/roleset/index.ahsj")
    ModelAndView roleset(String token,String roleId){
        ModelAndView modelAndView = new ModelAndView("backend/sys/role/role_set");
        modelAndView.addObject("title","医疗信息系统");
        modelAndView.addObject("token",token);
        modelAndView.addObject("roleId",roleId);
        return modelAndView;
    }

    @RequestMapping("/update/index.ahsj")
    ModelAndView UpdateIndex(Role role) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/sys/role/update");
        if(!EmptyUtil.Companion.isNullOrEmpty(role.getId())){
            modelAndView.addObject("role",roleService.selectById(role.getId()));
        }else{
            modelAndView.addObject("role",role);
        }
        modelAndView.addObject("title","人员信息添加");
        modelAndView.addObject("token", role.getToken());
        return modelAndView;
    }

    @RequestMapping("addUser/index.ahsj")
    ModelAndView addUserIndex(Long id,String token) throws Exception{
        ModelAndView modelAndView = new ModelAndView("backend/sys/role/add_user");
        if( id!= null)  modelAndView.addObject("role",roleService.updateInit(id));
        else {
            modelAndView.addObject("role", new Role());
        }
        modelAndView.addObject("token",token);
        return modelAndView;
    }
    @RequestMapping("listUserInfo.ahsj")
    @ResponseBody
    public List<SelectEntity> listUserInfo(Long roleId) throws Exception{
        UserInfo sysUserInfo = new UserInfo();
        sysUserInfo.setRoleIdStr(roleId.toString());
        return roleService.listUserInfo(sysUserInfo);
    }

    @RequestMapping("/saveOrUpdate.ahsj")
//    @PreAuthorize("hasAuthority('role.add') OR hasAuthority('role.update')")
    Message saveOrUpdate(Role role) throws Exception {
       return roleService.saveOrUpdate(role);
    }

    @RequestMapping("/delete.ahsj")
//    @PreAuthorize("hasAuthority('role.delete')")
    Message delete(Long[] id) throws Exception {
        return roleService.delete(id);
    }

    /**
     *@Description 查询角色
     *@Params [role]
     *@return core.entity.PageBean<com.ahsj.userinfor.entity.Role>
     *@Author chenzhicai
     *@Date 2019-08-17
     *@Time 17:02
    **/
    @RequestMapping("/list.ahsj")
    @ResponseBody
    public PageBean<Role> list(Role role) throws Exception {
        PageBean<Role> pageBean = new PageBean<Role>();
        pageBean.setParameter(role);
        return roleService.list(pageBean);
    }

    /**
     *@Description 给角色添加用户
     *@Params [userIds, roleId]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019-08-17
     *@Time 17:01
    **/
    @RequestMapping("/roleAddUser.ahsj")
//    @PreAuthorize("hasAuthority('role.adduser')")
    Message roleAddUser(Long[] userIds, Long roleId) throws Exception {
        //该逻辑中必须有用户信息被录入，否则如果传入空的用户，会报错！
       return roleService.roleAddUser(userIds,roleId);
    }

    /**
     *@Description 设置权限
     *@Params [permissionIds, roleId]
     *@return core.message.Message
     *@Author chenzhicai
     *@Date 2019/6/20
     *@Time 11:09 PM
    **/
    @RequestMapping("/setPermission.ahsj")
    @Transactional(readOnly = false)
//            @PreAuthorize("hasAuthority('role.setpermission')")
    Message setPermission(String[] permissionIds,Long roleId,String[] organizationIds) throws Exception {
        return roleService.setPermission(permissionIds,roleId,organizationIds);
    }

    /**
     *@Description 返回菜单节点
     *@Params [roleId]
     *@return java.util.List<com.ahsj.userinfor.entity.TreeEntity>
     *@Author chenzhicai
     *@Date 2019/6/20
     *@Time 11:07 PM
    **/
    @RequestMapping("/listMenuTree.ahsj")
    @ResponseBody
    public List<TreeEntity> listMenuTree(Long roleId) throws Exception {
        Role sysRole = new Role();
        sysRole.setUserId(getId());
        sysRole.setId(roleId);
        sysRole.setCompanyId(getCompanyId());
        return roleService.listMenuTree(sysRole);
    }

 
}
