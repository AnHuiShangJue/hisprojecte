package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedirecordProject;

public interface HisMedirecordProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisMedirecordProject record);

    int insertSelective(HisMedirecordProject record);

    HisMedirecordProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedirecordProject record);

    int updateByPrimaryKey(HisMedirecordProject record);
}