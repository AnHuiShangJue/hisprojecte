package com.ahsj.payalipay.controller;

import com.ahsj.payalipay.common.utils.AlipayConfig;
import com.ahsj.payalipay.common.utils.OrderStatusEnum;
import com.ahsj.payalipay.entity.Alipay;
import com.ahsj.payalipay.entity.AlipaymentOrder;
import com.ahsj.payalipay.entity.ReturnAlipay;
import com.ahsj.payalipay.service.impl.service.AlipayTradeRefundService;
import com.ahsj.payalipay.service.impl.service.AlipaymentOrderService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    AlipaymentOrderService alipaymentOrderService;

    @Autowired
    AlipayTradeRefundService alipayTradeRefundService;

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
     * @Description 跳转支付宝接口
     * @Params: [request, response]
     * @Author: dingli
     * @Return: void
     * @Date 2019/10/16
     * @Time 11:13
     **/
    @RequestMapping("/list/pay")
    public void payController(HttpServletRequest request, HttpServletResponse response, Alipay alipay) throws Exception {
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
    public ModelAndView showOrder(HttpServletRequest request, HttpServletResponse response, Alipay alipay, String token) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + alipay.getNumber() + "\"," + "\"trade_no\":\"" + alipay.getAlipayNumber() + "\"}");
        //请求
        //将结果转化为对象并存库里
        String result = alipayClient.execute(alipayRequest).getBody();
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("title", "支付宝支付");
        modelAndView.addObject("token", token);
        //      modelAndView.addObject("alipaymentOrder", alipaymentOrder);
        return modelAndView;
        //输出;
    }

    /**
     * @Description 退款
     * @Params: [request, response, alipay]
     * @Author: dingli
     * @Return: void
     * @Date 2019/10/16
     * @Time 18:11
     **/
    @RequestMapping("/list/refund")
    public ModelAndView refund(HttpServletRequest request, HttpServletResponse response, ReturnAlipay returnAlipay, String token) throws Exception {
        //获得初始化的AlipayClient
        //获得初始化的AlipayClient
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
        alipayTradeRefundService.addAlipayTradeRefund(result, returnAlipay.getReturnNumber());
        ModelAndView modelAndView = new ModelAndView("refund");
        modelAndView.addObject("title", "支付宝退款");
        modelAndView.addObject("token", token);
        modelAndView.addObject("result", result);
        return modelAndView;
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
    public ModelAndView refundQuery(HttpServletRequest request, HttpServletResponse response, ReturnAlipay returnAlipay, String token) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + returnAlipay.getNumber() + "\","
                + "\"trade_no\":\"" + returnAlipay.getReturnNumber() + "\","
                + "\"out_request_no\":\"" + returnAlipay.getReturnNumber() + "\"}");
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        ModelAndView modelAndView = new ModelAndView("refundQuery");
        modelAndView.addObject("title", "退款查询");
        modelAndView.addObject("token", token);
        modelAndView.addObject("result", result);
        return modelAndView;
        //输出;
    }


    
}
