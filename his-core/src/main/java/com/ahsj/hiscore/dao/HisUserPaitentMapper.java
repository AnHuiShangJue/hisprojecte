package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisUserPaitent;

public interface HisUserPaitentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisUserPaitent record);

    int insertSelective(HisUserPaitent record);

    HisUserPaitent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisUserPaitent record);

    int updateByPrimaryKey(HisUserPaitent record);
}