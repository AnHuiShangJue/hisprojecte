package com.ahsj.payalipay.service.impl.impl;


import com.ahsj.payalipay.common.utils.Constants;
import com.ahsj.payalipay.common.utils.OrderStatusEnum;
import com.ahsj.payalipay.dao.AlipayMapper;
import com.ahsj.payalipay.entity.Alipay;
import com.ahsj.payalipay.entity.AlipaymentOrder;
import com.ahsj.payalipay.service.impl.service.AlipayService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/16
 * @Time 13:26
 **/
@Service
public class AlipayServiceImpl implements AlipayService {
    /**
     * className aliCallbackService
     *
     * @author dingli
     * @date 2019/10/16 13:26
     */
    @Autowired
    private AlipayMapper alipayMapper;

    private Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class.getSimpleName());

    //支付宝的回调通知接口
    @Transactional(readOnly = false)
    public Message aliCallback(Map<String, String> params) throws Exception {
        log.info("-------------支付宝回调-------------");
        //订单号
        String number = params.get("out_trade_no");
        //交易号
        String alipayNumber = params.get("trade_no");
        //交易金额
        BigDecimal paymentAmount = new BigDecimal(params.get("total_amount"));
        //交易状态
        //String tradeStatus = params.get("trade_status");
        Alipay alipay = alipayMapper.selectByNumber(number);
        if (EmptyUtil.Companion.isNullOrEmpty(alipay)) {
            return MessageUtil.createMessage(false, "支付成功,数据库未查到" + number + "订单!");
        }
        if (alipay.getType() >= OrderStatusEnum.PAID.getCode()) {
            return MessageUtil.createMessage(false, "支付成功,支付宝重复调用!");
        }
        if (paymentAmount.compareTo(alipay.getPaymentAmount()) != 0) {
            return MessageUtil.createMessage(false, "支付成功," + number + "订单支付金额为" + paymentAmount + "查询金额为" + alipay.getPaymentAmount() + "!");
        }
        log.info("-------------开始更新为支付完成状态-------------");
        String date = params.get("gmt_payment"); //完成支付时间
        alipay.setType(OrderStatusEnum.PAID.getCode());
        alipay.setAlipayNumber(alipayNumber);//支付宝交易流水号
        alipayMapper.updateByPrimaryKeySelective(alipay);
        return MessageUtil.createMessage(true, "支付成功,数据正常!");
    }

    /**
     * @Description 根据订单号查询
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.Alipay
     * @Date 2019/10/17
     * @Time 10:13
     **/
    @Override
    @Transactional(readOnly = true)
    public Alipay selectByNumber(String number) throws Exception {
        return alipayMapper.selectByNumber(number);
    }

    /**
     * @Description 修改订单信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 10:14
     **/
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(Alipay record) throws Exception {
        return alipayMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @Description 新增订单信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/18
     * @Time 10:56
     **/
    @Override
    @Transactional(readOnly = false)
    public int insert(Alipay record) {
        return alipayMapper.insert(record);
    }


}
