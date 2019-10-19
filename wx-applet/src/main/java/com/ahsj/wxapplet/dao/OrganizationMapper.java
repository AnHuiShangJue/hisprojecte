package com.ahsj.wxapplet.dao;


import com.ahsj.wxapplet.entity.MenuTreeEntity;
import com.ahsj.wxapplet.entity.OrgMenu;
import com.ahsj.wxapplet.entity.Organization;
import com.ahsj.wxapplet.entity.TreeEntity;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    List<Organization> selectAll();

    /**
     * 功能说明：查詢所有的组织机构数据
     *
     * @return
     * @author shenggs
     * @date 2017年10月17日 上午11:24:03
     */
    public List<Organization> lstAllSysOrganization();

    /**
     * 功能说明：查詢所有的组织机构数据
     *
     * @return
     * @author shenggs
     * @date 2017年10月17日 上午11:24:03
     */
    public List<Organization> lstAllSysOrganizationNotInit();

    /**
     * 功能说明：查询组织机构树
     *
     * @param id
     * @return
     * @author shenggs
     * @date 2017-1-12 下午7:56:58
     */
    public List<TreeEntity> selectTreeCode(Organization sysOrganization);

    /**
     * 功能说明：验证组织机构名称的唯一性
     *
     * @param name
     * @return
     * @author zanbiao
     * @date 2017年11月12日 下午10:10:11
     */
    public Organization selectByOrgName(String name);

    /**
     * @return void
     * @Description 验证机构的唯一性
     * @Params [treeId]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 18:19
     **/
    public Organization selectByOrgNameCompany(Organization sysOrganization);

    /**
     * 功能说明：根据treeId删除组织机构
     *
     * @param treeId
     * @author zanbiao
     * @date 2017年11月14日 上午10:53:27
     */
    public void deleteByTreeId(String treeId);

    /**
     * 功能说明：根据树节点ID获取组织机构信息
     *
     * @param treeId
     * @return
     * @author zanbiao
     * @date 2017年11月14日 下午8:37:00
     */
     Organization selectByTreeId(String treeId);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.TreeEntity>
     * @Description 取树同一层最大唯一标识
     * @Params [sysOrganization]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 18:21
     **/
    public Organization selectMaxTreeId(String parentId);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.TreeEntity>
     * @Description 查询所有组织机构树
     * @Params [sysOrganization]
     * @Author chenzhicai
     * @Date 2019-04-11
     * @Time 18:21
     **/
    public List<TreeEntity> selectAllTreeCode(Organization sysOrganization);

    /**
     * 功能说明：部门树
     *
     * @return List<TreeEntity>
     * @throws Exception
     * @author shenggs
     * @date 2017-1-16 下午9:14:16
     */
    public List<TreeEntity> listOrgTree(Organization sysOrganization);

    /**
     * 功能说明：部门人员树
     *
     * @return List<TreeEntity>
     * @throws Exception
     * @author shenggs
     * @date 2017-1-16 下午9:14:16
     */
    public List<TreeEntity> listOrgUserTree(Organization sysOrganization);

    /**
     * 功能说明：查找代理机构
     *
     * @return
     * @author diaoyufei
     * @date 2018年5月23日 下午5:55:40
     */
    public List<Organization> selectSysOrganization(Organization sysOrganization);


    /**
     * 功能说明：查找代理机构
     *
     * @return
     * @author diaoyufei
     * @date 2018年5月23日 下午5:55:40
     */
    public List<Organization> lstCompanySysOrganization(Organization sysOrganization);

    /**
     * 功能说明：批量插入
     *
     * @param lstSysUserInfo
     * @throws Exception
     * @author shenggs
     * @date 2017年11月15日 下午9:04:45
     */
    public void importBatch(List<Organization> lstSysOrganization);

    public void updateBatch(List<Organization> lstSysOrganization);

    /**
     * 功能说明：删除初始数据
     *
     * @author shenggs
     * @date 2018年6月12日 上午11:21:44
     */
    public void deleteInitData();

    List<MenuTreeEntity> selectMenuTreeCode(OrgMenu orgMenu);

    Organization selectOrganizationById(Long id);

    Organization selectOrganizationByIds(Long id);

    List<Organization> queryOrganization(PageBean<Organization> pageBean);

    List<Organization> queryOrganizationList(Long id);

    void deleteByParentId(String id);

    List<Organization> selectByParentId(String id);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 12:45
     **/
    List<Organization> queryTranslateInfoByDate(Organization organization);

    /**
     * @return java.util.List<com.ahsj.userinfor.entity.Organization>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 15:04
     **/
    List<Organization> getOrganizationAll();

    /**
     * @return com.ahsj.userinfor.entity.Organization
     * @功能说明
     * @Params [organization]
     * @Author XJP
     * @Date 2019/8/27
     * @Time 14:57
     **/
    Organization getOrganizationName(Organization organization);
}