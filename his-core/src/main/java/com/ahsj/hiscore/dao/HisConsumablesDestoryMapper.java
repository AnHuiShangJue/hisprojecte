package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisConsumablesDestory;
import core.entity.PageBean;

import java.util.List;

public interface HisConsumablesDestoryMapper extends BaseMapper<HisConsumablesDestory>{
    int deleteByPrimaryKey(Long id);

    int insert(HisConsumablesDestory record);

    int insertSelective(HisConsumablesDestory record);

    HisConsumablesDestory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisConsumablesDestory record);

    int updateByPrimaryKey(HisConsumablesDestory record);

    List<HisConsumablesDestory> list(PageBean<HisConsumablesDestory> pageBean);


}