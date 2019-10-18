package com.ahsj.wxapplet.dao;


import com.ahsj.wxapplet.entity.MenuOperate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuOperateMapper extends BaseMapper<MenuOperate>{


     void deleteByMenuId(Integer menuId);

     void insertBatch(List<MenuOperate> lstMenuOperate);

     List<MenuOperate> listSysMenuOperate(Long id);

    List<MenuOperate> selectAll();
}