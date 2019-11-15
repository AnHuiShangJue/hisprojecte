package com.ahsj.wis.dao;

import com.ahsj.wis.entity.AboutWisdom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AboutWisdomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AboutWisdom record);

    int insertSelective(AboutWisdom record);

    AboutWisdom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AboutWisdom record);

    int updateByPrimaryKey(AboutWisdom record);
}
