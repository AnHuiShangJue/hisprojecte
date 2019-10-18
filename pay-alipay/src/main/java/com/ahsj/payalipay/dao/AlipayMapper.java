package com.ahsj.payalipay.dao;

import com.ahsj.payalipay.entity.Alipay;

public interface AlipayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Alipay record);

    int insertSelective(Alipay record);

    Alipay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alipay record);

    int updateByPrimaryKey(Alipay record);

    /**
     * @Description 根据订单号查询是否存在
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.Alipay
     * @Date 2019/10/16
     * @Time 13:34
     **/
    Alipay selectByNumber(String number);

    /**
     * @Description 根据交易流水号或者支付宝订单号查询
     * @Params: [record]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.Alipay
     * @Date 2019/10/18
     * @Time 11:01
     **/
    Alipay selectAlipay(Alipay record);
}