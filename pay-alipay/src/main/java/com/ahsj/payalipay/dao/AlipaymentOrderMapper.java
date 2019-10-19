package com.ahsj.payalipay.dao;

import com.ahsj.payalipay.entity.AlipaymentOrder;

public interface AlipaymentOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipaymentOrder record);

    int insertSelective(AlipaymentOrder record);

    AlipaymentOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipaymentOrder record);

    int updateByPrimaryKey(AlipaymentOrder record);

    /**
     * @Description 查询支付宝订单
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.AlipaymentOrder
     * @Date 2019/10/18
     * @Time 11:22
     **/
    AlipaymentOrder selectAlipaymentOrder(AlipaymentOrder record);
}