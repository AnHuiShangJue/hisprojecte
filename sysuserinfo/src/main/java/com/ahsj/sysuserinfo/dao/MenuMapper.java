package com.ahsj.sysuserinfo.dao;

import com.ahsj.sysuserinfo.entity.Menu;
import com.ahsj.sysuserinfo.entity.Role;
import com.ahsj.sysuserinfo.entity.TreeEntity;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper{

    int deleteByPrimaryKey(Long var1);

    int insert(Menu menu);

    int insertSelective(Menu menu);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu menu);

    int updateByPrimaryKey(Menu menu);

    /**
     * 功能说明：查询字典表树
     * @author shenggs
     * @date 2017-1-12 下午7:56:58
     * @param id
     * @return
     */
    public List<TreeEntity> selectTreeCode(String id);

    /**
     * 功能说明：查询
     * @author shenggs
     * @date 2017-1-9 上午9:17:39
     * @param pageEntity
     */
    public List<Menu> list(PageBean<Menu> pageEntity);

    /**
     * 功能说明：根据用户ID查询
     * @author shenggs
     * @date 2017-1-9 上午9:17:39
     * @param menuId
     */
    public Menu selectByMenuId(String menuId);

    /**
     * 功能说明：更新
     * @author shenggs
     * @date 2017-1-9 上午9:17:39
     * @param sysMenu
     */
    public void update(Menu sysMenu);

    /**
     * 功能说明：更新父节点的打开状态
     * @author shenggs
     * @date 2017-1-9 上午9:17:39
     * @param sysMenu
     */
    public void updateParent(Menu sysMenu);

    /**
     * 功能说明：取树同一层最大唯一标识
     * @author shenggs
     * @date 2017-1-16 下午1:15:42
     * @param parentId
     * @return
     */
    public Menu selectMaxTreeId(String parentId);

    /**
     * 功能说明：菜单树
     * @author shenggs
     * @date 2017-1-16 下午9:14:16
     * @return List<TreeEntity>
     * @throws Exception
     */
    public List<TreeEntity> listMenuTree(Role Role);

    /**
     * 功能说明：菜单树
     * @author shenggs
     * @date 2017-1-16 下午9:14:16
     * @return List<TreeEntity>
     * @throws Exception
     */
    public List<TreeEntity> listProjectMenuTree(String dutyId);

    /**
     * 功能说明：菜单树
     * @author shenggs
     * @date 2017-1-16 下午9:14:16
     * @return List<TreeEntity>
     * @throws Exception
     */
    public List<Menu> listSysMenu(Long id);


    /**
     * 功能说明：菜单树
     * @author shenggs
     * @date 2017-1-16 下午9:14:16
     * @return List<TreeEntity>
     * @throws Exception
     */
    public List<Menu> listSysMenuGaoqi(Long id);

    List<Menu> selectAll();
}