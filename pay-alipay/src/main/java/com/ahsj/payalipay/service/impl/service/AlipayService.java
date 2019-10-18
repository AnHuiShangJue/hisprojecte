package com.ahsj.payalipay.service.impl.service;

import com.ahsj.payalipay.entity.Alipay;
import core.message.Message;
import core.message.MessageUtil;

import java.util.Map;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/16
 * @Time 13:27
 **/
public interface AlipayService {

    /**
     * @Description 支付宝回调
     * @Params: [params]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/10/16
     * @Time 13:48
     **/
    Message aliCallback(Map<String, String> params) throws Exception;

    /**
     * @Description 根据订单号查询
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.Alipay
     * @Date 2019/10/17
     * @Time 10:11
     **/
    Alipay selectByNumber(String number) throws Exception;

    /**
     * @Description 修改订单信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 10:12
     **/
    int updateByPrimaryKeySelective(Alipay record) throws Exception;

    /**
     * @Description 新增订单信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/18
     * @Time 10:55
     **/
    int insert(Alipay record);
}
