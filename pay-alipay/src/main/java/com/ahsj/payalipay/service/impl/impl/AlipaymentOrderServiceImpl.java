package com.ahsj.payalipay.service.impl.impl;

import com.ahsj.payalipay.common.utils.JsonUtils;
import com.ahsj.payalipay.common.utils.OrderStatusEnum;
import com.ahsj.payalipay.dao.AlipaymentOrderMapper;
import com.ahsj.payalipay.entity.Alipay;
import com.ahsj.payalipay.entity.AlipaymentOrder;
import com.ahsj.payalipay.service.impl.service.AlipaymentOrderService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 11:00
 **/
@Service
public class AlipaymentOrderServiceImpl implements AlipaymentOrderService {
    /**
     * className AlipaymentOrderServiceImpl
     *
     * @author dingli
     * @date 2019/10/17 11:00
     */
    @Autowired
    AlipaymentOrderMapper alipaymentOrderMapper;

    /**
     * @Description 添加支付宝信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 11:02
     **/
    @Override
    @Transactional(readOnly = false)
    public int insert(AlipaymentOrder record) {
        return alipaymentOrderMapper.insert(record);
    }

    /**
     * @Description 修改支付宝信息
     * @Params: [record]
     * @Author: dingli
     * @Return: int
     * @Date 2019/10/17
     * @Time 13:57
     **/
    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(AlipaymentOrder record) {
        return alipaymentOrderMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @Description 添加支付宝信息
     * @Params: [result]
     * @Author: dingli
     * @Return: boolean
     * @Date 2019/10/17
     * @Time 14:05
     **/
    @Override
    @Transactional(readOnly = false)
    public boolean saveAlipaymentOrder(String result) throws Exception {
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_query_response").toString());
        AlipaymentOrder alipaymentOrder = new AlipaymentOrder();
        alipaymentOrder.setBuyerLogonId((String) jsonObject.get("buyer_logon_id"));
        alipaymentOrder.setBuyerPayAmount(new BigDecimal((String) jsonObject.get("buyer_pay_amount")));
        alipaymentOrder.setInvoiceAmount(new BigDecimal((String) jsonObject.get("invoice_amount")));
        alipaymentOrder.setOutTradeNo((String) jsonObject.get("out_trade_no"));
        alipaymentOrder.setReceiptAmount(new BigDecimal((String) jsonObject.get("receipt_amount")));
        alipaymentOrder.setTotalAmount(new BigDecimal((String) jsonObject.get("total_amount")));
        alipaymentOrder.setTradeNo((String) jsonObject.get("trade_no"));
        String tradeStatus = (String) jsonObject.get("trade_status");
        switch (tradeStatus) // 判断交易结果
        {
            case "TRADE_FINISHED": // 交易结束并不可退款
                alipaymentOrder.setTradeStatus(OrderStatusEnum.TRADE_FINISHED.getCode());
                break;
            case "TRADE_SUCCESS": // 交易支付成功
                alipaymentOrder.setTradeStatus(OrderStatusEnum.TRADE_SUCCESS.getCode());
                break;
            case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                alipaymentOrder.setTradeStatus(OrderStatusEnum.TRADE_CLOSED.getCode());
                break;
            case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                alipaymentOrder.setTradeStatus(OrderStatusEnum.WAIT_BUYER_PAY.getCode());
                break;
            default:
                break;
        }
        int insert = alipaymentOrderMapper.insert(alipaymentOrder);
        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

}
