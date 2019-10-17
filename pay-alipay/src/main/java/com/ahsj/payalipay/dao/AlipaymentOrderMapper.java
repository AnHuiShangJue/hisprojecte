package com.ahsj.payalipay.dao;

import com.ahsj.payalipay.entity.AlipaymentOrder;

public interface AlipaymentOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipaymentOrder record);

    int insertSelective(AlipaymentOrder record);

    AlipaymentOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipaymentOrder record);

    int updateByPrimaryKey(AlipaymentOrder record);
}