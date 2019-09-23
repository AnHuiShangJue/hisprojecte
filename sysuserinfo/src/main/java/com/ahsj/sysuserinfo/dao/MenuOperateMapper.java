package com.ahsj.sysuserinfo.dao;

import com.ahsj.sysuserinfo.entity.MenuOperate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuOperateMapper{


     void deleteByMenuId(Integer menuId);

     void insertBatch(List<MenuOperate> lstMenuOperate);

     List<MenuOperate> listSysMenuOperate(Long id);

    List<MenuOperate> selectAll();
}