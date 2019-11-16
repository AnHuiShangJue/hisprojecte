package com.ahsj.wis.dao;

import com.ahsj.wis.entity.AboutWisdom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AboutWisdomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AboutWisdom record);

    int insertSelective(AboutWisdom record);

    AboutWisdom selectByPrimaryKey(Integer id);

    /**
     *@Description 查询最新的一条记录
     *@Params
     *@return
     *@Author jin
     *@Date 2019/11/16
     *@Time 14:55
    */
    List<AboutWisdom> select();

    int updateByPrimaryKeySelective(AboutWisdom record);

    int updateByPrimaryKey(AboutWisdom record);
}
