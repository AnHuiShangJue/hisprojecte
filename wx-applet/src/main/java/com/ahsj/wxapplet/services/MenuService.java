package com.ahsj.wxapplet.services;


import com.ahsj.wxapplet.entity.Menu;
import com.ahsj.wxapplet.entity.MenuOperate;
import com.ahsj.wxapplet.entity.TreeEntity;
import core.entity.PageBean;
import core.message.Message;

import java.util.List;
import java.util.Map;

public interface MenuService {


    public List<TreeEntity> selectTreeCode(String id) throws Exception;


    public PageBean<Menu> list(PageBean<Menu> pageEntity) throws Exception;



    public Menu updateInit(Long id) throws Exception;


    public Message saveOrUpdate(Menu sysMenu) throws Exception;


    public Message delete(Long[] id) throws Exception;


    public Map<String,Object> addOperateInit(Integer id);


    public Message addOperate(Integer[] operate, String[] operatePermission, Integer menuId) throws Exception;

    /**
     *@Description 
     *@Params [id]
     *@return java.util.List<com.ahsj.userinfor.entity.Menu>
     *@Author chenzhicai
     *@Date 2019/7/1
     *@Time 3:35 PM
    **/
    List<Menu> listSysMenu(Long id);

    /**
     *@Description 列出指定用户的操作权限
     *@Params [id]
     *@return java.util.List<com.ahsj.userinfor.entity.MenuOperate>
     *@Author chenzhicai
     *@Date 2019/7/1
     *@Time 3:36 PM
    **/
    List<MenuOperate> listSysMenuOperate(Long id);
}
