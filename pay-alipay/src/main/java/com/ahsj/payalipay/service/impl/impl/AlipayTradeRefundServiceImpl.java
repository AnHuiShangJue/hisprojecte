package com.ahsj.payalipay.service.impl.impl;

import com.ahsj.payalipay.dao.AlipayTradeRefundMapper;
import com.ahsj.payalipay.entity.AlipayTradeRefund;
import com.ahsj.payalipay.service.impl.service.AlipayTradeRefundService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 15:54
 **/
@Service
public class AlipayTradeRefundServiceImpl implements AlipayTradeRefundService {
    /**
     * className AlipayTradeRefundServiceImpl
     *
     * @author dingli
     * @date 2019/10/17 15:54
     */
    @Autowired
    AlipayTradeRefundMapper alipayTradeRefundMapper;

    @Override
    @Transactional(readOnly = false)
    public void addAlipayTradeRefund(String result, String requestNo) throws Exception {
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_refund_response").toString());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AlipayTradeRefund alipayTradeRefund = new AlipayTradeRefund();
        alipayTradeRefund.setBuyerLogonId((String) jsonObject.get("buyer_logon_id"));
        alipayTradeRefund.setGmtRefundPay(format.parse((String) jsonObject.get("gmt_refund_pay")));
        alipayTradeRefund.setOutTradeNo((String) jsonObject.get("out_trade_no"));
        alipayTradeRefund.setRefundFee(new BigDecimal((String) jsonObject.get("refund_fee")));
        alipayTradeRefund.setOutTradeNo((String) jsonObject.get("out_trade_no"));
        alipayTradeRefund.setSendBackFee(new BigDecimal((String) jsonObject.get("send_back_fee")));
        alipayTradeRefund.setTradeNo((String) jsonObject.get("trade_no"));
        alipayTradeRefund.setRequestNo(requestNo);
        alipayTradeRefundMapper.insert(alipayTradeRefund);
    }

    /**
     * @Description 根据交易流水号或者支付宝订单号和退款申请号查询退款申请
     * @Params: [record]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.AlipayTradeRefund
     * @Date 2019/10/18
     * @Time 11:36
     **/
    @Override
    @Transactional(readOnly = true)
    public AlipayTradeRefund selectAlipayTradeRefund(AlipayTradeRefund record) throws Exception {
        return alipayTradeRefundMapper.selectAlipayTradeRefund(record);
    }
}
