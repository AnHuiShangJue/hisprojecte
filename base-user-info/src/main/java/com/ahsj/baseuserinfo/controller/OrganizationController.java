package com.ahsj.baseuserinfo.controller;

import com.ahsj.baseuserinfo.entity.*;
import com.ahsj.baseuserinfo.entity.dto.UserDept;
import com.ahsj.baseuserinfo.services.OrganizationService;
import com.ahsj.baseuserinfo.services.UserService;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/organization/")
public class OrganizationController extends BaseOAController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    UserService userService;

    @RequestMapping("treeCode.ahsj")
    @ResponseBody
    public List<TreeEntity> treeCode(Integer lv, String id) throws Exception {
        Organization sysOrganization = new Organization();
        sysOrganization.setCompanyPrivi(getPriviSql("organization.tree_id"));
        sysOrganization.setParentId(id);
        sysOrganization.setCompanyId(getCompanyId());
        return organizationService.selectTreeCode(sysOrganization, lv);
    }

    @RequestMapping(value = "list/index.ahsj")
    public ModelAndView list(Organization organization) {
        ModelAndView modelAndView = new ModelAndView("backend/sys/orgnization/list");
        modelAndView.addObject("token", organization.getToken());
        return modelAndView;
    }

    @RequestMapping(value = "update/index.ahsj")
    public ModelAndView updateIndex(Organization organization) throws Exception {
        ModelAndView modelAndView = new ModelAndView("backend/sys/orgnization/update");
        Organization tmp = new Organization();
        if (!EmptyUtil.Companion.isNullOrEmpty(organization.getId())) {
            tmp = organizationService.updateInit(organization.getId().toString());
        } else {
            tmp.setParentId(organization.getParentId());
            tmp.setDepartKbn(organization.getDepartKbn());
        }
        modelAndView.addObject("sysOrganizationInfo", tmp);
        modelAndView.addObject("token", organization.getToken());
        return modelAndView;
    }

    /**
     * @return core.message.Message
     * @Description 用户设置界面
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-14
     * @Time 19:36
     **/
    @RequestMapping(value = "userlist/index.ahsj")
    public ModelAndView userlist(Organization organization) {
        ModelAndView modelAndView = new ModelAndView("backend/sys/orgnization/user_list");
        modelAndView.addObject("deptId", organization.getTreeId());
        modelAndView.addObject("token", organization.getToken());
        return modelAndView;
    }

    @RequestMapping(value = "info/inner/saveOrUpdate.ahsj", method = RequestMethod.POST)
    @ResponseBody
    public Message saveOrUpdate(Organization organization) throws Exception {
        return organizationService.saveOrUpdate(organization, getCompanyId());
    }


    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.UserInfo>
     * @Description 删除组织机构
     * @Params [sysUserInfo]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:33
     **/
    @RequestMapping("info/inner/delete.ahsj")
    @ResponseBody
    public Message delete(String[] id) throws Exception {
        return organizationService.delete(id);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.UserInfo>
     * @Description 根据组织机构Id获取用户列表
     * @Params [sysUserInfo]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:32
     **/
    @RequestMapping("listUserByOrgId.ahsj")
    @ResponseBody
    public PageBean<UserInfo> listUserByOrgId(UserInfo sysUserInfo) throws Exception {
        PageBean<UserInfo> pageEntity = new PageBean<UserInfo>();
        sysUserInfo.setCompanyPrivi(getPriviSql("user_org_rel.dept_id"));
        pageEntity.setParameter(sysUserInfo);
        return organizationService.listUserByOrgId(pageEntity);
    }

    /**
     * @return core.message.Message
     * @Description 根据组织机构Id获取未被选择的用户列表
     * @Params [id, orgId]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:32
     **/
    @RequestMapping("listNotSelectUserByOrgId.ahsj")
    @ResponseBody
    public PageBean<UserInfo> listNotSelectUserByOrgId(UserInfo sysUserInfo) throws Exception {
        PageBean<UserInfo> pageEntity = new PageBean<UserInfo>();
        sysUserInfo.setCompanyPrivi(getPriviSql("user_info.company_id"));
        pageEntity.setParameter(sysUserInfo);
        return organizationService.listNotSelectUserByOrgId(pageEntity);
    }

    /**
     * @return core.message.Message
     * @Description 配置用户
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:33
     **/
    @RequestMapping("setUserByOrgId.ahsj")
    @ResponseBody
    public Message setUserByOrgId(Long[] id, String orgId) throws Exception {
        return organizationService.setUserByOrgId(id, orgId);
    }

    /**
     * @return core.message.Message
     * @Description 移除用户
     * @Params [id, deptId, leader]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:33
     **/
    @RequestMapping("deleteUserByOrgId.ahsj")
    @ResponseBody
    public Message deleteUserByOrgId(Long[] id) throws Exception {
        return organizationService.deleteUserByOrgId(id);
    }

    /**
     * 功能说明：设置负责人
     *
     * @param id
     * @return
     * @throws Exception
     * @author zanbiao
     * @date 2017年11月12日 下午10:51:59
     */
    @RequestMapping("setHead.ahsj")
    @ResponseBody
    public Message setHead(Long[] id, String deptId, String leader) throws Exception {
        return organizationService.setHead(id, leader);
    }

    /**
     *
     * 功能说明：查看二维码
     * @author zanbiao
     * @date 2017年10月25日 上午10:09:15
     * @param fileName
     * @param request
     * @param response
     * @throws Exception
     */
//    @RequestMapping("show.kcppc")
//    public void show(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        FileOperateUtil.show(request, response, uploadFolderPath, userInfoQrcode + fileName);
//    }

    /**
     * 功能说明：查询组织菜单节点
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-14 15:56
     */
    @RequestMapping("/menuTreeCode.ahsj")
    @ResponseBody
    public List<MenuTreeEntity> menuTreeCode(Integer lv, String id) throws Exception {
        OrgMenu orgMenu = new OrgMenu();
        orgMenu.setCompanyPrivi("org_menu.org_id");
        orgMenu.setOrgId(Long.parseLong(id));
        return organizationService.selectMenuTreeCode(orgMenu, lv);

    }

    /**
     * 功能说明：给组织分配菜单
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-14 18:14
     */
    @RequestMapping("/setMenuByOrgId.ahsj")
    @ResponseBody
    public Message setMenuByOrgId(String[] permissionIds, String[] organizationIds) throws Exception {
        return organizationService.setMenuByOrgId(permissionIds, organizationIds);
    }

    /**
     * 功能说明：删除菜单
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-15 11:59
     */
    @RequestMapping("/deleteMenuById.ahsj")
    @ResponseBody
    public Message deleteMenuById(Long[] id) throws Exception {
        return organizationService.deleteMenuById(id);
    }

    /**
     * 功能说明：给角色分配组织菜单：查询菜单列表
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-06-18 16:30
     */

    @RequestMapping("/listOrgnizationMenuTree.ahsj")
    @ResponseBody
    public List<TreeEntity> listOrgnizationMenuTree(Long roleId) throws Exception {
        Role role = new Role();
        role.setId(roleId);
        role.setCompanyId(getCompanyId());
        return organizationService.listOrgnizationMenuTree(role);
    }


    @RequestMapping("info/inner/lstAllSysOrganization.ahsj")
    @ResponseBody
    public List<Organization> lstAllSysOrganization() {
        return organizationService.lstAllSysOrganization();
    }

    /**
     * 功能说明：组织菜单导航
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-07-01 11:53
     */
    @RequestMapping("menu/index.ahsj")
    public ModelAndView orgMenu(Organization organization) {
        ModelAndView modelAndView = new ModelAndView("backend/sys/orgmenu/list");
        modelAndView.addObject("title", "医疗信息系统");
        modelAndView.addObject("token", organization.getToken());
        return modelAndView;

    }

    /**
     * 功能说明：获取菜单节点
     *
     * @param
     * @return
     * @author Eric
     * @data 2019-07-01 13:51
     */
    @RequestMapping("menu/listMenuTree.ahsj")
    @ResponseBody
    public List<TreeEntity> listMenuTree() throws Exception {
        Role sysRole = new Role();
        sysRole.setCompanyId(getCompanyId());
        return organizationService.listMenuTree(sysRole);
    }


    /**
     * 查看某部门待值班人员
     *
     * @param id
     * @return
     */
    @RequestMapping("selectUserByOrgId.ahsj")
    @ResponseBody
    public ArrayList<UserDept> selectUserByOrgId(@RequestParam("id") String id) {
        Long of = Long.valueOf(id);
        ArrayList<UserDept> list = new ArrayList<>();
        Organization organization = organizationService.selectByPrimaryKey(of);
        List<UserInfo> infoList = userService.getUserInfoDeptById(id);
        for (UserInfo userInfo : infoList) {
            UserDept userDept = new UserDept();
            userDept.setUserId(userInfo.getId());
            userDept.setUserLoginId(userInfo.getUserId());
            userDept.setDeptId(organization.getId());
            userDept.setUserName(userInfo.getUserName());
            userDept.setDeptName(organization.getName());
            list.add(userDept);
        }
        return list;
    }


    @GetMapping("select/organization.ahsj")
    @ResponseBody
    public Organization getOrganizationById(@RequestParam("id") Long id) {
        if (id != null) {
            return organizationService.selectByPrimaryKey(id);
        } else {
            return null;
        }
    }


    @GetMapping("info/inner/id.ahsj")
    @ResponseBody
    public Organization getOrganizationByIds(@RequestParam("id") Long id) {
        if (id != null) {
            return organizationService.selectOrganizationById(id);
        } else {
            return null;
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:47
     **/
    @PostMapping("query/selects/organizations.ahsj")
    @ResponseBody
    public PageBean<Organization> queryList(Organization organization) throws Exception {
        PageBean<Organization> pageBean = new PageBean<>();
        pageBean.setParameter(organization);
        return organizationService.queryList(pageBean);
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:47
     **/
    @GetMapping("query/selects/queryOrganizationList.ahsj")
    @ResponseBody
    public List<Organization> queryOrganizationList(@RequestParam("id") Long id) throws Exception {
        List<Organization> list = organizationService.queryOrganizationList(id);
        return list;
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:48
     **/
    @GetMapping("query/selects/queryOrganizationListDate.ahsj")
    @ResponseBody
    public List<Organization> queryTranslateInfoByDate(@RequestBody Organization organization) throws Exception {
        return organizationService.queryTranslateInfoByDate(organization);
    }

    @PostMapping("query/selects/getOrganizationAll.ahsj")
    @ResponseBody
    public List<Organization> getOrganizationAll() throws Exception {
        List<Organization> organizationList = organizationService.getOrganizationAll();
        return organizationList;
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:52
     **/
    @PostMapping("query/selects/getOrganizationName.ahsj")
    @ResponseBody
    public Organization getOrganizationName( @RequestBody Organization organization) throws Exception {
        organization = organizationService.getOrganizationName(organization);
        return organization;
    }


}
