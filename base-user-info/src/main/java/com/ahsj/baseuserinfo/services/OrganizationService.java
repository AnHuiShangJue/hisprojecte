package com.ahsj.baseuserinfo.services;

import com.ahsj.baseuserinfo.entity.*;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;

public interface OrganizationService {

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.TreeEntity>
     * @Description
     * @Params [sysOrganization, lv]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:39
     **/
    public Message saveOrUpdate(Organization sysOrganization, String companyId) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.UserInfo>
     * @Description 查询组织机构树
     * @Params [pageEntity]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:38
     **/
    public List<TreeEntity> selectTreeCode(Organization sysOrganization, Integer lv) throws Exception;

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @Description 查询组织对应的人
     * @Params [id]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:38
     **/
    public PageBean<UserInfo> listUserByOrgId(PageBean<UserInfo> pageEntity) throws Exception;

    /**
     * @return core.message.Message
     * @Description 更新初期化
     * @Params [id, orgId]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:48
     **/
    public Organization updateInit(String id) throws Exception;

    /**
     * @return core.message.Message
     * @Description 删除
     * @Params [id, orgId]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:48
     **/
    public Message delete(String[] id) throws Exception;

    /**
     * @return core.message.Message
     * @Description 查找未被选择的组织机构
     * @Params [id, orgId]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:48
     **/
    public PageBean<UserInfo> listNotSelectUserByOrgId(PageBean<UserInfo> pageEntity) throws Exception;

    /**
     * @return
     * @Description 将用户放入组织机构内
     * @Params
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:37
     **/
    public Message setUserByOrgId(Long[] id, String orgId) throws Exception;

    /**
     * @return
     * @Description 将用户移除组织机构
     * @Params
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:38
     **/
    public Message deleteUserByOrgId(Long[] id) throws Exception;

    /**
     * @return
     * @Description 设置负责人
     * @Params
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 19:38
     **/
    public Message setHead(Long[] id, String leader) throws Exception;


    List<MenuTreeEntity> selectMenuTreeCode(OrgMenu orgMenu, Integer lv);


    Message setMenuByOrgId(String[] permissionIds, String[] organizationIds);


    Message deleteMenuById(Long[] id);

    List<TreeEntity> listOrgnizationMenuTree(Role role);


    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @Description 返回所有的组织
     * @Params []
     * @Author chenzhicai
     * @Date 2019/7/1
     * @Time 5:46 PM
     **/
    List<Organization> lstAllSysOrganization();

    List<TreeEntity> listMenuTree(Role sysRole);

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:43
     **/
    Organization selectOrganizationById(Long id);

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:43
     **/
    Organization selectByPrimaryKey(Long id);

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:43
     **/
    Organization selectOrganizationByIds(Long id);

    /**
     * @return core.entity.PageBean<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:43
     **/
    PageBean<Organization> queryList(PageBean<Organization> pageBean) throws Exception;

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:43
     **/
    List<Organization> queryOrganizationList(Long id);


    /**
     * @return List<HisMedicineInfo>
     * @功能说明
     * @Params [hisMedicineInfo]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:42
     **/
    List<Organization> queryTranslateInfoByDate(Organization organization) throws Exception;

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 15:02
     **/
    List<Organization> getOrganizationAll() throws Exception;

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:53
     **/
    Organization getOrganizationName(Organization organization);
}
