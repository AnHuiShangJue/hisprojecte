package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.StationInfo;
import core.entity.PageBean;

import java.util.List;
import java.util.logging.XMLFormatter;

public interface StationInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StationInfo record);

    int insertSelective(StationInfo record);

    StationInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StationInfo record);

    int updateByPrimaryKey(StationInfo record);

    /**
     *@功能说明
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.smartparkcore.entity.po.StationInfo>
     *@Author XJP
     *@Date 2019/10/19
     *@Time 18:15
    **/
    List<StationInfo> queryList(PageBean<StationInfo> pageBean);
}