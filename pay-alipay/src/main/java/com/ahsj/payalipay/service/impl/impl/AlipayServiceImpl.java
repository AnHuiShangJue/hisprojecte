package com.ahsj.payalipay.service.impl.impl;


import com.ahsj.payalipay.common.utils.Constants;
import com.ahsj.payalipay.common.utils.OrderStatusEnum;
import com.ahsj.payalipay.dao.AlipayMapper;
import com.ahsj.payalipay.entity.Alipay;
import com.ahsj.payalipay.service.impl.service.AlipayService;
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
    public Map<String, String> aliCallback(Map<String, String> params) throws Exception {
        Map<String, String> map = new HashMap<>();
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
            map.put("false", "非法订单，回调忽略");
            return map;
        }
        if (alipay.getType() >= OrderStatusEnum.PAID.getCode()) {
            map.put("false", "支付宝重复调用");
            return map;
        }

        if (paymentAmount.compareTo(alipay.getPaymentAmount()) == 0) {
            log.info("-------------开始更新为支付完成状态-------------");
            String date = params.get("gmt_payment"); //完成支付时间
            alipay.setType(OrderStatusEnum.PAID.getCode());
            alipay.setAlipayNumber(alipayNumber);//支付宝交易流水号
            alipayMapper.updateByPrimaryKeySelective(alipay);
            map.put("true", "回调成功");
            return map;
        } else {
            map.put("false", "金额错误");
            return map;
        }
    }

}
