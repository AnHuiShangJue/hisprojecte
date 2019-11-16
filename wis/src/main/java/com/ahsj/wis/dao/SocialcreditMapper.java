package com.ahsj.wis.dao;

import com.ahsj.wis.entity.Socialcredit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SocialcreditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Socialcredit record);

    int insertSelective(Socialcredit record);

    Socialcredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Socialcredit record);

    int updateByPrimaryKey(Socialcredit record);

    List<Socialcredit> selectAll();

}