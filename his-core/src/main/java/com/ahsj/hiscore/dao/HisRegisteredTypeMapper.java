package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRegisteredType;
import core.entity.PageBean;

import java.util.List;

public interface HisRegisteredTypeMapper extends BaseMapper{
    int deleteByPrimaryKey(Long id);

    int insert(HisRegisteredType record);

    int insertSelective(HisRegisteredType record);

    HisRegisteredType selectByPrimaryKey(Long id);
    HisRegisteredType selectByRegisterId(Long id);
    HisRegisteredType myselectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRegisteredType record);

    int updateByPrimaryKey(HisRegisteredType record);

    int updateByRegisterId(Long id);



    List<HisRegisteredType> list(PageBean<HisRegisteredType> pageBean);

    List<HisRegisteredType> listByAll();
}
