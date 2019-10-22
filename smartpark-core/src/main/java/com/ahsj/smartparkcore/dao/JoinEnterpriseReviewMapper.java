package com.ahsj.smartparkcore.dao;

import com.ahsj.smartparkcore.entity.po.JoinEnterpriseReview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinEnterpriseReviewMapper {

    int deleteByPrimaryKey(Long id);

    int insert(JoinEnterpriseReview joinEnterpriseReview);

    int insertSelective(JoinEnterpriseReview joinEnterpriseReview);

    JoinEnterpriseReview selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JoinEnterpriseReview joinEnterpriseReview);

    int updateByPrimaryKey(JoinEnterpriseReview joinEnterpriseReview);

    JoinEnterpriseReview queryJoinEnterpriseReview(JoinEnterpriseReview joinEnterpriseReview);

    JoinEnterpriseReview queryJoinEnterpriseReviewByEmailAndPhone(JoinEnterpriseReview joinEnterpriseReview);
}