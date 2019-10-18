package com.ahsj.payalipay.controller;

import com.ahsj.payalipay.common.utils.AlipayConfig;
import com.ahsj.payalipay.common.utils.OrderStatusEnum;
import com.ahsj.payalipay.entity.Alipay;
import com.ahsj.payalipay.entity.AlipayTradeRefund;
import com.ahsj.payalipay.entity.AlipaymentOrder;
import com.ahsj.payalipay.entity.ReturnAlipay;
import com.ahsj.payalipay.service.impl.service.AlipayService;
import com.ahsj.payalipay.service.impl.service.AlipayTradeRefundService;
import com.ahsj.payalipay.service.impl.service.AlipaymentOrderService;
import com.ahsj.payalipay.service.impl.service.ReturnAlipayService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import core.message.Message;
import core.message.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.EmptyUtil;
import utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/16
 * @Time 9:40
 **/
@RestController
@RequestMapping("/api/alipay")
public class AlipayController {
    /**
     * className AlipayController
     *
     * @author dingli
     * @date 2019/10/16 9:40
     */
    private Logger log = LoggerFactory.getLogger(AlipayController.class.getSimpleName());

    @Autowired
    AlipaymentOrderService alipaymentOrderService;

    @Autowired
    AlipayTradeRefundService alipayTradeRefundService;

    @Autowired
    AlipayService alipayService;

    @Autowired
    ReturnAlipayService returnAlipayService;

    /**
     * @Description
     * @Params: [token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/16
     * @Time 9:42
     **/
    @RequestMapping(value = "/list/index.ahsj")
    ModelAndView list(String token, BigDecimal tmoney, String val) throws Exception {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "支付宝支付");
        modelAndView.addObject("token", token);
        return modelAndView;
    }

    /**
     * @Description 跳转支付宝接口付费
     * @Params: [request, response]
     * @Author: dingli
     * @Return: void
     * @Date 2019/10/16
     * @Time 11:13
     **/
    @RequestMapping("/list/pay")
    public void payController(HttpServletRequest request, HttpServletResponse response, Alipay alipay) throws Exception {
        alipay.setType(OrderStatusEnum.NO_PAY.getCode());//未支付
        int insert = alipayService.insert(alipay);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //out_trade_no  商户订单号，商户网站订单系统中唯一订单号，必填  total_amount  付款金额，必填   subject  订单名称，必填    body 商品描述，可空

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + alipay.getNumber() + "\","
                + "\"total_amount\":\"" + alipay.getPaymentAmount() + "\","
                + "\"subject\":\"" + alipay.getOrderName() + "\","
                + "\"body\":\"" + alipay.getDescription() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.charset);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * @Description 订单查询
     * @Params: [request, response, alipay]
     * @Author: dingli
     * @Return: void
     * @Date 2019/10/16
     * @Time 17:44
     **/
    @RequestMapping("/list/show")
    public AlipaymentOrder showOrder(HttpServletRequest request, HttpServletResponse response, AlipaymentOrder alipaymentOrder, String token) throws Exception {
        AlipaymentOrder ao = alipaymentOrderService.selectAlipaymentOrder(alipaymentOrder);
        if (!EmptyUtil.Companion.isNullOrEmpty(ao)) {//如果库里有直接查询
            return ao;
        } else {//没有，网络查询并存库里
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            //设置请求参数
            AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + alipaymentOrder.getOutTradeNo() + "\"," + "\"trade_no\":\"" + alipaymentOrder.getTradeNo() + "\"}");
            //请求
            //将结果转化为对象并存库里
            String result = alipayClient.execute(alipayRequest).getBody();
            JSONObject json = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_query_response").toString());
            if (StringUtil.equals((String) json.get("msg"), "Success")) {//查询成功，添加查询记录
                alipaymentOrderService.saveAlipaymentOrder(result);
                log.info("------订单查询并添加成功------");
                return alipaymentOrderService.selectAlipaymentOrder(alipaymentOrder);
            } else {
                log.info("------订单查询失败，未添加订单，请稍后重新查询------");
                return new AlipaymentOrder();
            }
        }
    }

    /**
     * @Description 支付宝退款申请
     * @Params: [request, response, returnAlipay, token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/18
     * @Time 11:33
     **/
    @RequestMapping("/list/refund")
    public Message refund(HttpServletRequest request, HttpServletResponse response, ReturnAlipay returnAlipay, String token) throws Exception {
        int id = returnAlipayService.insert(returnAlipay);
        log.info("------新增商家退款记录成功------");
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + returnAlipay.getNumber() + "\","
                + "\"trade_no\":\"" + returnAlipay.getAlipayNumber() + "\","
                + "\"refund_amount\":\"" + returnAlipay.getReturnAmount() + "\","
                + "\"refund_reason\":\"" + returnAlipay.getReason() + "\","
                + "\"out_request_no\":\"" + returnAlipay.getReturnNumber() + "\"}");
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_refund_response").toString());
        if (StringUtil.equals((String) jsonObject.get("msg"), "Success")) {
            ReturnAlipay ra = new ReturnAlipay();
            ra.setId(new Long(id));
            ra.setType(1);//已退款
            returnAlipayService.updateByPrimaryKeySelective(ra);
            log.info("--------修改支付状态成功-------");
            alipayTradeRefundService.addAlipayTradeRefund(result, returnAlipay.getReturnNumber());
            log.info("------退款成功并查询退款记录并添加成功-------");
            return MessageUtil.createMessage(true, "退款成功");
        } else {
            String message = (String) jsonObject.get("sub_msg");
            log.info("------退款失败" + message + "-------");
            return MessageUtil.createMessage(false, "退款失败" + message);
        }
    }

    /**
     * @Description 退款查询
     * @Params: [request, response, returnAlipay, token]
     * @Author: dingli
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Date 2019/10/17
     * @Time 12:27
     **/
    @RequestMapping("/list/refundQuery")
    public AlipayTradeRefund refundQuery(HttpServletRequest request, HttpServletResponse response, AlipayTradeRefund alipayTradeRefund, String token) throws Exception {

        AlipayTradeRefund ar = alipayTradeRefundService.selectAlipayTradeRefund(alipayTradeRefund);
        if (!EmptyUtil.Companion.isNullOrEmpty(ar)) {
            return ar;
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + alipayTradeRefund.getOutTradeNo() + "\","
                + "\"trade_no\":\"" + alipayTradeRefund.getTradeNo() + "\","
                + "\"out_request_no\":\"" + alipayTradeRefund.getRequestNo() + "\"}");
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        JSONObject jsonObject = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_refund_response").toString());
        if (StringUtil.equals((String) jsonObject.get("msg"), "Success")) {
            alipayTradeRefundService.addAlipayTradeRefund(result, alipayTradeRefund.getRequestNo());
            log.info("------查询退款记录并添加成功-------");
            return alipayTradeRefundService.selectAlipayTradeRefund(alipayTradeRefund);
        } else {
            String message = (String) jsonObject.get("sub_msg");
            log.info("------" + message + "查询退款记录失败，请稍后查询-------");
            return new AlipayTradeRefund();
        }
        //输出;
    }

    /**
     * @Description 交易关闭
     * @Params: [request, response, alipayTradeRefund, token]
     * @Author: dingli
     * @Return: com.ahsj.payalipay.entity.AlipayTradeRefund
     * @Date 2019/10/18
     * @Time 15:21
     **/
    @RequestMapping("/list/close")
    public Message close(HttpServletRequest request, HttpServletResponse response, Alipay alipay, String token) throws Exception {
        Alipay al = alipayService.selectByNumber(alipay.getNumber());
        if (EmptyUtil.Companion.isNullOrEmpty(al)) {
            return MessageUtil.createMessage(false, "关闭失败" + alipay.getNumber() + "订单号不存在");
        } else {
            if (al.getType() == OrderStatusEnum.OEDER_CLOSE.getCode()) { //订单关闭
                return MessageUtil.createMessage(false, "关闭失败" + alipay.getNumber() + "订单已关闭");
            }
            else {
                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
                //设置请求参数
                AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
                alipayRequest.setBizContent("{\"out_trade_no\":\"" + alipay.getNumber() + "\"," + "\"trade_no\":\"" + "" + "\"}");
                //请求
                String result = alipayClient.execute(alipayRequest).getBody();
                JSONObject jsonObject = JSON.parseObject(JSON.parseObject(result).get("alipay_trade_close_response").toString());
                if (StringUtil.equals((String) jsonObject.get("msg"), "Success")) {
                    al.setType(OrderStatusEnum.OEDER_CLOSE.getCode());
                    alipayService.updateByPrimaryKeySelective(al);
                    log.info("------交易关闭成功,修改交易关闭状态成功-------");
                    return MessageUtil.createMessage(true, "订单关闭成功！");
                } else {
                    String message = (String) jsonObject.get("sub_msg");
                    log.info("------" + message + "交易关闭失败，请稍后查询-------");
                    return MessageUtil.createMessage(false, "关闭失败" + message);
                }
            }
        }
    }
}
