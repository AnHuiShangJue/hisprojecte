package com.ahsj.payalipay.service.impl.service;

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
     * @Description  支付宝回调
     * @Params: [params]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/10/16
     * @Time 13:48
     **/
    Map<String,String> aliCallback(Map<String, String> params) throws Exception;
}
