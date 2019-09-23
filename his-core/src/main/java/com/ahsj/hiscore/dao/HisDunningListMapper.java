package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDunningList;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface HisDunningListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisDunningList record);

    int insertSelective(HisDunningList record);

    HisDunningList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDunningList record);

    int updateByPrimaryKey(HisDunningList record);


}