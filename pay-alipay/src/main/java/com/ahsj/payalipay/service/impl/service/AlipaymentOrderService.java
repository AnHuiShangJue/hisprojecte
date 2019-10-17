package com.ahsj.payalipay.service.impl.service;

import com.ahsj.payalipay.entity.AlipaymentOrder;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 11:00
 **/
public interface AlipaymentOrderService {

    /**
     * @Description 添加支付宝信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 11:01
     **/
    int insert(AlipaymentOrder record);

    /**
     * @Description 修改支付宝信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 13:56
     **/
    int updateByPrimaryKeySelective(AlipaymentOrder record);

    /**
     * @Description 添加支付宝信息
     * @Params: [result]
     * @Author: dingli
     * @Return: boolean
     * @Date 2019/10/17
     * @Time 14:04
     **/
   boolean saveAlipaymentOrder(String result) throws Exception;
}
