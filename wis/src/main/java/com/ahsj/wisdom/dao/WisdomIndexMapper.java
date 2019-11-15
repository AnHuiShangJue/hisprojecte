package com.ahsj.wisdom.dao;

import com.ahsj.wisdom.entity.WisdomIndex;

public interface WisdomIndexMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WisdomIndex record);

    int insertSelective(WisdomIndex record);

    WisdomIndex selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WisdomIndex record);

    int updateByPrimaryKey(WisdomIndex record);
}