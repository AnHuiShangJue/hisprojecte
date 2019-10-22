package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.JoinEnterpriseReview;

public interface JoinEnterpriseReviewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JoinEnterpriseReview record);

    int insertSelective(JoinEnterpriseReview record);

    JoinEnterpriseReview selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JoinEnterpriseReview record);

    int updateByPrimaryKey(JoinEnterpriseReview record);
}