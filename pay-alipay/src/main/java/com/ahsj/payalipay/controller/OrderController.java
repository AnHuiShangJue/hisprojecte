package com.ahsj.payalipay.controller;

import com.ahsj.payalipay.common.utils.AlipayConfig;
import com.ahsj.payalipay.common.utils.Constants;
import com.ahsj.payalipay.service.impl.service.AlipayService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.api.client.util.Maps;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import core.message.Message;
import core.message.MessageUtil;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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

    /**
     * className OrderController
     *
     * @author dingli
     * @date 2019/10/16 13:12
     */

//支付宝回调函数
    @RequestMapping("/alipay_callback.do")
    public ModelAndView alipayCallback(HttpServletRequest request, String token) throws Exception {
        ModelAndView modelAndView = new ModelAndView("return");
        modelAndView.addObject("title", "支付宝支付信息");
        modelAndView.addObject("token", token);
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
                modelAndView.addObject("message", "非法订单，请忽略");
                return modelAndView;
            }
        } catch (AlipayApiException e) {
            log.info("支付宝回调异常", e);
        }

        //  验证各种数据,修改数据库支付状态
        Map<String, String> map = alipayService.aliCallback(params);
        for (String key : map.keySet()) {  //map只有一条
            String value = map.get(key);
            if (key.equals("true")) {
                modelAndView.addObject("message", "支付成功" + value);
                return modelAndView;
            }
            if (key.equals("false")) {
                modelAndView.addObject("message", "支付失败" + value);
                return modelAndView;
            }
        }
        return null;
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
    @RequestMapping("/alipay_asynchronous.do")
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
        params.remove("sign_type");

        boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, AlipayConfig.getAlipay_public_key(), AlipayConfig.getCharset(), AlipayConfig.getSign_type());
        if (alipayRSACheckedV2) {
            String number = params.get("out_trade_no");
            //交易号
            String alipayNumber = params.get("trade_no");

            String type = params.get("trade_status");
            if (type.equals(Constants.TRADE_FINISHED)) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (type.equals(Constants.TRADE_SUCCESS)) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            return "success";
        } else {//验证失败
            return "fail";
        }
    }
}



