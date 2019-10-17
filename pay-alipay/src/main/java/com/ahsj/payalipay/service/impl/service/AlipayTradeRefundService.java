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
    void addAlipayTradeRefund(String  result,String requestNo) throws Exception;

}
