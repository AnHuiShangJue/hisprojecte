package com.ahsj.payalipay.controller;

import com.ahsj.payalipay.common.utils.AlipayConfig;
import com.ahsj.payalipay.common.utils.Constants;
import com.ahsj.payalipay.common.utils.OrderStatusEnum;
import com.ahsj.payalipay.entity.Alipay;
import com.ahsj.payalipay.entity.AlipaymentOrder;
import com.ahsj.payalipay.service.impl.service.AlipayService;
import com.ahsj.payalipay.service.impl.service.AlipaymentOrderService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.google.api.client.util.Maps;
import core.message.Message;
import core.message.MessageUtil;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/16
 * @Time 13:12
 **/
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private Logger log = LoggerFactory.getLogger(OrderController.class.getSimpleName());

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipaymentOrderService alipaymentOrderService;

    /**
     * className OrderController
     *
     * @author dingli
     * @date 2019/10/16 13:12
     */

//支付宝回调函数
    @RequestMapping("/alipay_callback.do")
    public Message alipayCallback(HttpServletRequest request, String token) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        log.info("支付宝回调, sign:{},trade_status:{},参数:{}", params.get("sign"), params.get("trade_status"), params.toString());
        //!!! 验证回调的正确性，是不是支付宝发了，而且还要避免重复通知
        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, AlipayConfig.getAlipay_public_key(), AlipayConfig.getCharset(), AlipayConfig.getSign_type());
            if (!alipayRSACheckedV2) {
                return MessageUtil.createMessage(false, "非法订单，请忽略!");
            }
        } catch (AlipayApiException e) {
            log.info("支付宝回调异常", e);
        }
        //  验证各种数据,修改数据库支付状态
        Message mes = alipayService.aliCallback(params);
        JSONObject jsonObject = mes.buildJSON();
        String message = (String) jsonObject.get("message");
        boolean isSuccess = (boolean) jsonObject.get("success");
        if (isSuccess) {//数据校对正确 ，查询订单并添加
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            //设置请求参数
            AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + params.get("out_trade_no") + "\"," + "\"trade_no\":\"" + params.get("trade_no") + "\"}");
            String result = alipayClient.execute(alipayRequest).getBody();
            JSONObject json = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_query_response").toString());
            if (StringUtil.equals((String) json.get("msg"), "Success")) {//查询成功，添加查询记录
                alipaymentOrderService.saveAlipaymentOrder(result);
                log.info("------订单查询并添加成功------");
                return MessageUtil.createMessage(true, "" + message);
            } else {
                log.info("------订单查询失败，未添加订单，请稍后重新查询------");
                return MessageUtil.createMessage(true, "" + message);
            }
        } else {
            return MessageUtil.createMessage(false, "支付成功,数据校对不正确!" + message);
        }
    }

    /**
     * @Description 跳转结果
     * @Params: [token, tmoney, val]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/16
     * @Time 15:01
     **/
    @RequestMapping(value = "/list/index.ahsj")
    ModelAndView list(String token, BigDecimal tmoney, String val) throws Exception {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "支付宝支付");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    //异步回调
    @PostMapping("/alipay_asynchronous.do")
    public String asynchronous(HttpServletRequest request, String token) throws Exception {
        Map<String, String> params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        log.info("支付宝回调, sign:{},trade_status:{},参数:{}", params.get("sign"), params.get("trade_status"), params.toString());
        //!!! 验证回调的正确性，是不是支付宝发了，而且还要避免重复通知
        params.remove("sign_type"); //签名属性
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV2(params, AlipayConfig.getAlipay_public_key(), AlipayConfig.getCharset(), AlipayConfig.getSign_type());
            log.info("==================验签成功 ！");
        } catch (AlipayApiException e) {
            log.info("==================验签失败 ！");
            e.printStackTrace();
        }
        if (signVerified) {
            String appId = params.get("app_id");//支付宝分配给开发者的应用Id
            String notifyTime = params.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
            String gmtCreate = params.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
            String gmtPayment = params.get("gmt_payment");//交易付款时间
            String gmtRefund = params.get("gmt_refund");//交易退款时间
            String gmtClose = params.get("gmt_close");//交易结束时间
            String tradeNo = params.get("trade_no");//支付宝的交易号
            String outTradeNo = params.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
            String outBizNo = params.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
            String buyerLogonId = params.get("buyer_logon_id");//买家支付宝账号
            String sellerId = params.get("seller_id");//卖家支付宝用户号
            String sellerEmail = params.get("seller_email");//卖家支付宝账号
            String totalAmount = params.get("total_amount");//订单金额:本次交易支付的订单金额，单位为人民币（元）
            String receiptAmount = params.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
            String invoiceAmount = params.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
            String buyerPayAmount = params.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额		  
            String tradeStatus = params.get("trade_status");// 获取交易状态 
            Alipay alipay = alipayService.selectByNumber(outTradeNo);
            if (!EmptyUtil.Companion.isNullOrEmpty(alipay) && alipay.getPaymentAmount().compareTo(new BigDecimal(totalAmount)) == 0 && StringUtil.equals(appId, AlipayConfig.app_id)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                AlipaymentOrder alipaymentOrder = new AlipaymentOrder();//添加支付宝信息
                alipaymentOrder.setNotifyTime(format.parse(notifyTime));
                alipaymentOrder.setGmtCreate(format.parse(gmtCreate));
                alipaymentOrder.setGmtPayment(format.parse(gmtPayment));
                alipaymentOrder.setGmtRefund(format.parse(gmtRefund));
                alipaymentOrder.setGmtClose(format.parse(gmtClose));
                alipaymentOrder.setTradeNo(tradeNo);
                alipaymentOrder.setOutBizNo(outBizNo);
                alipaymentOrder.setBuyerLogonId(buyerLogonId);
                alipaymentOrder.setSellerId(sellerId);
                alipaymentOrder.setSellerEmail(sellerEmail);
                alipaymentOrder.setTotalAmount(new BigDecimal(totalAmount));
                alipaymentOrder.setReceiptAmount(new BigDecimal(receiptAmount));
                alipaymentOrder.setInvoiceAmount(new BigDecimal(invoiceAmount));
                alipaymentOrder.setBuyerPayAmount(new BigDecimal(buyerPayAmount));
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
                int insert = alipaymentOrderService.updateByPrimaryKeySelective(alipaymentOrder);
                if (tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功
                    if (insert > 0) {
                        return "success";
                    } else {
                        return "fail";
                    }
                } else {
                    return "fail";
                }

            } else {
                log.info("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                return "fail";
            }

        } else {  //验签不通过
            log.info("==================验签不通过 ！");
            return "fail";
        }

    }


}



