package com.ahsj.payalipay.dao;

import com.ahsj.payalipay.entity.ReturnAlipay;

public interface ReturnAlipayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReturnAlipay record);

    int insertSelective(ReturnAlipay record);

    ReturnAlipay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReturnAlipay record);

    int updateByPrimaryKey(ReturnAlipay record);
}