package com.ahsj.payalipay.service.impl.service;

import com.ahsj.payalipay.entity.AlipayTradeRefund;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 15:54
 **/
public interface AlipayTradeRefundService {
/** className AlipayTradeRefundService
 *@author dingli
 *@date 2019/10/17 15:54
 */

    /**
     * @Description 退款信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 15:56
     **/
    void addAlipayTradeRefund(String result, String requestNo) throws Exception;


    /**
     * @Description 根据交易流水号或者支付宝订单号和退款申请号查询退款申请
     * @Params: [record]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.AlipayTradeRefund
     * @Date 2019/10/18
     * @Time 11:36
     **/
    AlipayTradeRefund selectAlipayTradeRefund(AlipayTradeRefund record) throws Exception;

}
