package com.ahsj.payalipay.service.impl.service;

import com.ahsj.payalipay.entity.ReturnAlipay;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/18
 * @Time 14:04
 **/
public interface ReturnAlipayService {

    /**
     * @Description 退款申请
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/18
     * @Time 14:06
     **/
    int insert(ReturnAlipay record) throws Exception;

    /**
     * @Description 修改退款状态
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/18
     * @Time 14:44
     **/
    int updateByPrimaryKeySelective(ReturnAlipay record)throws Exception;
}
