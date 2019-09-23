package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHandover;
import core.entity.PageBean;

import java.util.List;

public interface HisHandoverMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisHandover record);

    int insertSelective(HisHandover record);

    HisHandover selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisHandover record);

    int updateByPrimaryKey(HisHandover record);

    /**
     *@Description list
     *@Params [pageBean]
     *@return java.util.List<com.ahsj.hiscore.entity.HisHandover>
     *@Author chenzhicai
     *@Date 2019-09-15
     *@Time 13:45
    **/
    List<HisHandover> list(PageBean<HisHandover> pageBean);

    /**
     *@Description 根据住院编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisHandover
     *@Author chenzhicai
     *@Date 2019-09-16
     *@Time 12:35
     **/
    HisHandover selectByHosNumber(String number);
}