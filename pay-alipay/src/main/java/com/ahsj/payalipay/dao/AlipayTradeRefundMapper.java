package com.ahsj.payalipay.dao;

import com.ahsj.payalipay.entity.AlipayTradeRefund;

public interface AlipayTradeRefundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AlipayTradeRefund record);

    int insertSelective(AlipayTradeRefund record);

    AlipayTradeRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlipayTradeRefund record);

    int updateByPrimaryKey(AlipayTradeRefund record);

    /**
     * @Description 根距交易流水号或者支付宝订单号和退款申请号查询退款申请
     * @Params: [record]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.AlipayTradeRefund
     * @Date 2019/10/18
     * @Time 11:36
     **/
    AlipayTradeRefund selectAlipayTradeRefund(AlipayTradeRefund record);
}