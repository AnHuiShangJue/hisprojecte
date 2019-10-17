package com.ahsj.payalipay.dao;

import com.ahsj.payalipay.entity.AlipayTradeRefund;

public interface AlipayTradeRefundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipayTradeRefund record);

    int insertSelective(AlipayTradeRefund record);

    AlipayTradeRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipayTradeRefund record);

    int updateByPrimaryKey(AlipayTradeRefund record);
}