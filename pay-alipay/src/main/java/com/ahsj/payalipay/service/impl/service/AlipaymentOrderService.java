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
    int insert(AlipaymentOrder record) throws Exception;

    /**
     * @Description 修改支付宝信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 13:56
     **/
    int updateByPrimaryKeySelective(AlipaymentOrder record) throws Exception;

    /**
     * @Description 添加支付宝信息
     * @Params: [result]
     * @Author: dingli
     * @Return: boolean
     * @Date 2019/10/17
     * @Time 14:04
     **/
    boolean saveAlipaymentOrder(String result) throws Exception;

    /**
     * @Description 查询支付宝订单
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.AlipaymentOrder
     * @Date 2019/10/18
     * @Time 11:22
     **/
    AlipaymentOrder selectAlipaymentOrder(AlipaymentOrder record) throws Exception;


}
