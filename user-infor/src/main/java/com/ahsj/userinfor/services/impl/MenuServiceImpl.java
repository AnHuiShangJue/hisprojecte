package com.ahsj.userinfor.services.impl;

import com.ahsj.userinfor.dao.MenuMapper;
import com.ahsj.userinfor.dao.MenuOperateMapper;
import com.ahsj.userinfor.dao.OperateMapper;
import com.ahsj.userinfor.entity.Menu;
import com.ahsj.userinfor.entity.MenuOperate;
import com.ahsj.userinfor.entity.TreeEntity;
import com.ahsj.userinfor.entity.TreeHelper;
import com.ahsj.userinfor.services.MenuService;
import core.code.Constants;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    OperateMapper operateMapper;

    @Autowired
    MenuOperateMapper menuoperateMapper;


    @Override
    public List<TreeEntity> selectTreeCode(String id) throws Exception {
        return menuMapper.selectTreeCode(id);
    }

    @Override
    public PageBean<Menu> list(PageBean<Menu> pageEntity) throws Exception {
        pageEntity.setData(menuMapper.list(pageEntity));
        return pageEntity;
    }

    @Override
    public Menu updateInit(Long id) throws Exception {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public Message saveOrUpdate(Menu Menu) throws Exception {
        // 如果主健为空 则为新增
        if (EmptyUtil.Companion.isNullOrEmpty(Menu.getId())) {
            Menu check = menuMapper.selectByMenuId(Menu.getMenuId());
            if (!EmptyUtil.Companion.isNullOrEmpty(check) && !EmptyUtil.Companion.isNullOrEmpty(check.getMenuId())) {
                return MessageUtil.createMessage(false, "此菜单已存在！");
            }
            // 叶子节点
            Menu.setHasSub(Constants.TREE_HAS_SUB_NO);
            // 取同一层树唯一标识
            Menu maxTreeId = menuMapper.selectMaxTreeId(Menu.getParentId());
            String treeId = null;
            if (maxTreeId != null && maxTreeId.getTreeId() != null) {
                treeId = maxTreeId.getTreeId();
            }
            treeId = TreeHelper.getInstance().getTreeId(treeId, Menu.getParentId());
            Menu.setTreeId(treeId);
            menuMapper.insert(Menu);
            // 把父节点的状态更新下
            Menu.setHasSub(Constants.TREE_HAS_SUB_YES);
            menuMapper.updateParent(Menu);
            return MessageUtil.createMessage(true, "保存成功。");
        } else {
            // 如果主健不为空 则为更新
            menuMapper.update(Menu);
            return MessageUtil.createMessage(true, "更新成功。");
        }
    }

    @Override
    public Message delete(Long[] id) throws Exception {
        for (int i = 0; i < id.length; i++) {
            menuMapper.deleteByPrimaryKey(id[i]);
        }
        return MessageUtil.createMessage(true, "删除成功。");
    }

    @Override
    public Map<String, Object> addOperateInit(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("operateList", operateMapper.listMenuOperate(id));
        return map;
    }

    @Override
    public Message addOperate(Integer[] operate, String[] operatePermission, Integer menuId) throws Exception {
        // 删除菜单对应的所有功能点
        menuoperateMapper.deleteByMenuId(menuId);
        List<MenuOperate> lstMenuOperate = new ArrayList<MenuOperate>();
        MenuOperate sysMenuOperate = null;
        if (operate != null && operate.length > 0) {
            for (int i = 0; i < operate.length; i++) {
                sysMenuOperate = new MenuOperate();
                sysMenuOperate.setMenuId(new Long(menuId));
                sysMenuOperate.setOperateId(new Long(operate[i]));
                sysMenuOperate.setPermission(operatePermission[i]);
                lstMenuOperate.add(sysMenuOperate);
            }
            menuoperateMapper.insertBatch(lstMenuOperate);
        }
        return MessageUtil.createMessage(true, "设置功能点成功！");
    }

    @Override
    public List<Menu> listSysMenu(Long id) {
        return menuMapper.listSysMenu(id);
    }

    @Override
    public List<MenuOperate> listSysMenuOperate(Long id) {
        return menuoperateMapper.listSysMenuOperate(id);
    }
}
