package com.ahsj.wxapplet.services.impl;


import com.ahsj.wxapplet.common.CodeHelper;
import com.ahsj.wxapplet.common.SysConstants;
import com.ahsj.wxapplet.dao.*;
import com.ahsj.wxapplet.entity.*;
import com.ahsj.wxapplet.services.OrganizationService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl implements OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserOrganizationMapper userOrganizationMapper;
    @Autowired
    OrgMenuMapper orgMenuMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuOperateMapper menuOperateMapper;
    @Autowired
    RoleOrganizationMapper roleOrganizationMapper;


    private Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class.getSimpleName());


    /**
     * @return core.message.Message
     * @Description 新增或者更新
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:29
     **/
    @Override
    public Message saveOrUpdate(Organization sysOrganization, String companyId) throws Exception {
        //id为空表明新增
        if (EmptyUtil.Companion.isNullOrEmpty(sysOrganization.getId())) {

            Organization sysSelect = new Organization();
            sysSelect.setTreeId(companyId);
            sysSelect.setName(sysOrganization.getName());
            Organization checkOrgName = organizationMapper.selectByOrgNameCompany(sysSelect);
            if (!EmptyUtil.Companion.isNullOrEmpty(checkOrgName) && !EmptyUtil.Companion.isNullOrEmpty(checkOrgName.getId())) {
                return MessageUtil.createMessage(false, "此组织机构名称已存在！");
            }
            //maxTreeId 为同一个父节点下的子节点最大的节点
            Organization maxTreeId = organizationMapper.selectMaxTreeId(sysOrganization.getParentId());
            //treeId 为本次存入的节点的id
            String treeId = null;
            if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                treeId = maxTreeId.getTreeId();
            }
            treeId = TreeHelper.getInstance().getTreeId(treeId, sysOrganization.getParentId());
            sysOrganization.setTreeId(treeId);

            if (EmptyUtil.Companion.isNullOrEmpty(sysOrganization.getType())) {
                if (treeId.length() > 4) {
                    sysOrganization.setType("2");
                } else {
                    sysOrganization.setType("1");
                }
//                Organization companyInfo = companyInfo(treeId);
//                sysOrganization.setType(companyInfo.getType());
            }
            organizationMapper.insert(sysOrganization);


            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            Organization checkInfo = (Organization) organizationMapper.selectByPrimaryKey(sysOrganization.getId());
            if (!checkInfo.getName().equals(sysOrganization.getName())) {
                Organization sysSelect = new Organization();
                if (checkInfo.getTreeId().length() > 4) {
                    sysSelect.setTreeId(checkInfo.getTreeId().substring(0, 4));
                } else {
                    sysSelect.setTreeId(checkInfo.getTreeId());
                }
                sysSelect.setName(sysOrganization.getName());
                Organization checkOrgName = organizationMapper.selectByOrgNameCompany(sysSelect);
                if (!EmptyUtil.Companion.isNullOrEmpty(checkOrgName) && !EmptyUtil.Companion.isNullOrEmpty(checkOrgName.getId())) {
                    return MessageUtil.createMessage(false, "此组织机构名称已存在！");
                }
            }
            organizationMapper.updateByPrimaryKey(sysOrganization);

            return MessageUtil.createMessage(true, "更新成功。");
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.UserInfo>
     * @Description 查询组织机构树
     * @Params [pageEntity]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:29
     **/
    @Override
    public List<TreeEntity> selectTreeCode(Organization sysOrganization, Integer lv) throws Exception {
        if (lv == null) {
            if (SysConstants.ADMIN_COMPANY_ID.equals(sysOrganization.getCompanyId())) {
                sysOrganization.setParentId("0");
            } else {
                sysOrganization.setParentId(companyInfo(sysOrganization.getCompanyId()).getParentId());
            }

            return organizationMapper.selectTreeCode(sysOrganization);
        }

        return organizationMapper.selectTreeCode(sysOrganization);
    }

    /**
     * @return core.message.Message
     * @Description 查询组织对应的人
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:29
     **/
    @Override
    public PageBean<UserInfo> listUserByOrgId(PageBean<UserInfo> pageEntity) throws Exception {
        pageEntity.setData(CodeHelper.getInstance().setCodeValue(userInfoMapper.listUserByOrgId(pageEntity)));
        return pageEntity;
    }


    @Override
    public Organization updateInit(String id) throws Exception {
        return (Organization) CodeHelper.getInstance().setCodeValue(organizationMapper.selectByTreeId(id));
    }

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.UserInfo>
     * @Description 删除
     * @Params [pageEntity]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:28
     **/
    @Override
    public Message delete(String[] id) throws Exception {
        for (int i = 0; i < id.length; i++) {
            recursionDelete(id[i]);

        }
        return MessageUtil.createMessage(true, "删除成功。");
    }

    /**
     * @return void
     * @Description 递归删除父节点下的所有子节点
     * @Params [treeId]
     * @Author zhushixiang
     * @Date 2019-07-30
     * @Time 15:28
     **/
    @Transactional(readOnly = false)
    public void recursionDelete(String id) {
        while (true) {
            List<Organization> organizationList = organizationMapper.selectByParentId(id);
            organizationMapper.deleteByTreeId(id);//删除当前节点
            organizationMapper.deleteByParentId(id);//首先删除当前节点下的所有子节点
            if (organizationList.size() == 0) {
                break;
            } else {
                for (int i = 0; i < organizationList.size(); i++) {
                    recursionDelete(organizationList.get(i).getTreeId());
                }
            }
        }
    }

    /**
     * @return core.message.Message
     * @Description 查找未被选择的组织机构
     * @Params [id, orgId]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:28
     **/
    @Override
    public PageBean<UserInfo> listNotSelectUserByOrgId(PageBean<UserInfo> pageEntity) throws Exception {
        pageEntity.setData(userInfoMapper.listNotSelectUserByOrgId(pageEntity));
        return pageEntity;
    }

    /**
     * @return core.message.Message
     * @Description 将用户放入组织机构内
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:28
     **/
    @Override
    public Message setUserByOrgId(Long[] id, String orgId) throws Exception {
        UserOrganization userOrganization = null;
        List<UserOrganization> listSysUserDept = new ArrayList<UserOrganization>();
        for (int i = 0; i < id.length; i++) {
            userOrganization = new UserOrganization();
            userOrganization.setDeptId(orgId);
            userOrganization.setUserId(id[i]);
            listSysUserDept.add(userOrganization);
        }
        userOrganizationMapper.importBatch(listSysUserDept);
        return MessageUtil.createMessage(true, "用户添加成功。");
    }

    /**
     * @return core.message.Message
     * @Description 将用户移除组织机构
     * @Params [id, leader]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:28
     **/
    @Override
    public Message deleteUserByOrgId(Long[] id) throws Exception {
        UserOrganization userOrganization = new UserOrganization();
        userOrganization.setIds(id);
        userOrganizationMapper.deleteByUserId(userOrganization);
        return MessageUtil.createMessage(true, "用户移除成功。");
    }

    /**
     * @return
     * @Description 设置负责人
     * @Params
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 20:27
     **/
    @Override
    public Message setHead(Long[] id, String leader) throws Exception {
        UserOrganization userOrganization = null;
        for (int i = 0; i < id.length; i++) {
            userOrganization = new UserOrganization();
            userOrganization.setId(id[i]);
            userOrganization.setIsLeader(leader);
            userOrganizationMapper.updateForHead(userOrganization);
        }
        return MessageUtil.createMessage(true, "设置成功！");
    }

    /**
     * 功能说明：返回菜单列表
     *
     * @param
     * @return java.util.List<com.ahsj.userinfor.entity.MenuTreeEntity>
     * @author Eric
     * @data 2019-06-15 11:41
     */

    @Override
    public List<MenuTreeEntity> selectMenuTreeCode(OrgMenu orgMenu, Integer lv) {
        if (lv == null) {
            orgMenu.setOrgId(1L);
            return organizationMapper.selectMenuTreeCode(orgMenu);
        }

        return organizationMapper.selectMenuTreeCode(orgMenu);
    }

    /**
     * 功能说明：给组织分配菜单
     *
     * @param
     * @return core.message.Message
     * @author Eric
     * @data 2019-06-15 11:41
     */

    @Override
    public Message setMenuByOrgId(String[] permissionIds, String[] organizationIds) {
        List<Organization> organizationPerms = new ArrayList<>();
        //将所有的组织取出，和前端传来的值进行比较，若为选中的记录则将其加到准备拼接权限字段的list中
        List<Organization> organizationList = organizationMapper.selectAll();
        for (String item : organizationIds) {
            for (Organization item1 : organizationList) {
                if (item.equals(item1.getTreeId())) {
                    organizationPerms.add(item1);
                }
            }
        }
        //区分部门科室等级
        List<Organization> organizationParent = new ArrayList<>();
        List<Organization> organizaitonChild = new ArrayList<>();

        for (Organization item : organizationPerms) {
            if (item.getType().equals("1")) {
                organizationParent.add(item);
            } else if (item.getType().equals("2")) {
                organizaitonChild.add(item);
            }
        }
        List<OrgMenu> orgMenuList = new ArrayList<>();
        for (Organization item : organizationParent) {
            int flag = 0; //用于检测部门下是否存在科室
            for (Organization item1 : organizaitonChild) {
                if (item.getTreeId().equals(item1.getParentId())) {//如果目录下存在子科室
                    OrgMenu orgMenu = new OrgMenu();
                    orgMenu.setOrgId(item1.getId());
                    orgMenu.setPermssionPermsId(item.getPerms() + "." + item1.getPerms());
                    orgMenuList.add(orgMenu);
                    flag = 1;
                }
            }
            //目录下不存在子科室
            if (flag == 0) {
                OrgMenu orgMenu = new OrgMenu();
                orgMenu.setOrgId(item.getId());
                orgMenu.setPermssionPermsId(item.getPerms());
                orgMenuList.add(orgMenu);
            }
        }// 组织部分权限字段拼接结束

        //拼接菜单部分
        //查找所有的库信息，然后再进行逻辑排除
        List<Menu> menuList = menuMapper.selectAll();
        List<MenuOperate> operateList = menuOperateMapper.selectAll();

        List<Menu> menuList1 = new ArrayList<>();
//        List<MenuOperate> operateList1 = new ArrayList<>();

        //将前端选中的菜单树id转化为对象并存入list中
        for (String item : permissionIds) {
            for (Menu item1 : menuList) {
                if (item.equals(item1.getTreeId())) {
                    menuList1.add(item1);
                }
            }
        }

        //将菜单目录的父子关系区分出，分别存入不同的list用于后续字段拼接
        List<Menu> parentMenuList = new ArrayList<>();
        List<Menu> childMenuList = new ArrayList<>();

        for (Menu item : menuList1) {
            if (item.getHasSub().equals("0")) {
                parentMenuList.add(item);
            }
            if (item.getHasSub().equals("1")) {
                childMenuList.add(item);
            }
        }

        List<OrgMenu> menuMenuList = new ArrayList<>();
        List<OrgMenu> menuMenuList1 = new ArrayList<>();

        for (Menu item : parentMenuList) {
            int flag = 0;//用于判定是否含有自己目录
            for (Menu item1 : childMenuList) {
                if (item.getTreeId().equals(item1.getParentId())) {
                    OrgMenu orgMenu = new OrgMenu();
                    orgMenu.setMenuId(item1.getId());
                    orgMenu.setPermssionPermsId(item.getMenuId() + "." + item1.getMenuId());
                    menuMenuList.add(orgMenu);
                    flag = 1;
                }
            }
            if (flag == 0) {
                OrgMenu orgMenu = new OrgMenu();
                orgMenu.setMenuId(item.getId());
                orgMenu.setPermssionPermsId(item.getMenuId());
                menuMenuList.add(orgMenu);
            }
        }//菜单父子级目录权限标识拼接结束
        List removeId = new ArrayList();
        for (int i = 0; i < menuMenuList.size(); i++) {
            int flag = i;
            int flag1 = 0;//标志位，用于判断是否有操作标识新增情况
            for (MenuOperate item : operateList) {
                if (menuMenuList.get(i).getMenuId().equals(item.getMenuId())) {
                    OrgMenu orgMenu = new OrgMenu();
                    orgMenu.setMenuId(menuMenuList.get(i).getMenuId());
                    orgMenu.setPermssionPermsId(menuMenuList.get(i).getPermssionPermsId() + "." + item.getPermission());
                    menuMenuList1.add(orgMenu);
                    flag1 = 1;
                }
            }
            if (flag1 == 1) {
                removeId.add(flag);
            }
        }

        //移除缘来list里用来拼接操作字段的菜单字段，采用倒序移除，防止因索引问题造成下标溢出
        for (int i = removeId.size() - 1; i >= 0; i--) {
            int s = (int) removeId.get(i);
            menuMenuList.remove(s);
        }

        for (OrgMenu item : menuMenuList1) {
            menuMenuList.add(item);
        }//菜单模块拼接结束

        //组织菜单模块拼接
        List<OrgMenu> finalOrgMenuList = new ArrayList<>();
        for (OrgMenu item : orgMenuList) {
            for (OrgMenu item1 : menuMenuList) {
                OrgMenu orgMenu = new OrgMenu();
                orgMenu.setOrgId(item.getOrgId());
                orgMenu.setMenuId(item1.getMenuId());
                orgMenu.setPermssionPermsId(item.getPermssionPermsId() + "." + item1.getPermssionPermsId());
                finalOrgMenuList.add(orgMenu);
            }
        }

        //删除原有的记录
        orgMenuMapper.deleteAll();


        //添加现有记录
        for (OrgMenu item : finalOrgMenuList) {
            orgMenuMapper.insertSelective(item);
        }


        return MessageUtil.createMessage(true, "菜单添加成功。");
    }

    /**
     * 功能说明：根据菜单id删除菜单
     *
     * @param
     * @return core.message.Message
     * @author Eric
     * @data 2019-06-15 12:54
     */

    @Override
    public Message deleteMenuById(Long[] id) {
        OrgMenu orgMenu = new OrgMenu();
        orgMenu.setIds(id);
        orgMenuMapper.deleteByMenuId(orgMenu);
        return MessageUtil.createMessage(true, "菜单移除成功。");
    }

    @Override
    public List<TreeEntity> listOrgnizationMenuTree(Role role) {
        return roleOrganizationMapper.listOrgnizationMenuTree(role);
    }

    @Override
    public List<Organization> lstAllSysOrganization() {
        return organizationMapper.lstAllSysOrganization();
    }

    @Override
    public List<TreeEntity> listMenuTree(Role sysRole) {
        return orgMenuMapper.listMenuTree(sysRole);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization selectOrganizationById(Long id) {
        Organization organization = organizationMapper.selectByPrimaryKey(id);
        return organization;
    }

    @Override
    @Transactional(readOnly = true)
    public Organization selectByPrimaryKey(Long id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Organization selectOrganizationByIds(Long id) {
        Organization organization = organizationMapper.selectOrganizationByIds(id);
        return organization;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/13
     * @Time 14:02
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<Organization> queryList(PageBean<Organization> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(organizationMapper.queryOrganization(pageBean)));
        return pageBean;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> queryOrganizationList(Long id) {
        List<Organization> list = organizationMapper.queryOrganizationList(id);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organization> queryTranslateInfoByDate(Organization organization) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(organization)) {
            log.info("查询数据不存在 ， 无对应数据");
            return new ArrayList<>();
        } else {
            List<Organization> translateList = organizationMapper.queryTranslateInfoByDate(organization);
            if (EmptyUtil.Companion.isNullOrEmpty(translateList)) {
                log.info("查询数据不存在 ， 无对应数据");
                return new ArrayList<>();
            } else {
                return translateList;
            }
        }
    }

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 15:05
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Organization> getOrganizationAll() throws Exception {
        List<Organization> organizationList = organizationMapper.getOrganizationAll();
        if (EmptyUtil.Companion.isNullOrEmpty(organizationList)) {
            log.info("查询失败");
            return new ArrayList<>();
        } else {
            return organizationList;
        }
    }

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:54
     **/
    @Override
    @Transactional(readOnly = true)
    public Organization getOrganizationName(Organization organization) {
        organization = organizationMapper.getOrganizationName(organization);
        if (EmptyUtil.Companion.isNullOrEmpty(organization)) {
            log.info("查询失败");
            return new Organization();
        } else {
            return organization;
        }
    }


}
