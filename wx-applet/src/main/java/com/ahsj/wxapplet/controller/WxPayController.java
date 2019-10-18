package com.ahsj.wxapplet.controller;


import com.ahsj.wxapplet.MyApplicationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/api/WxPay")
public class WxPayController {


    @Value("${wxapplet.config.weixinpay.notifyurl}")
    private String notify_url;

    private static Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    public Map payment(HttpServletRequest request
            , @RequestParam(value = "openid",required = false)String openid
            , @RequestParam(value = "mch_id",required = false)String mch_id
            , @RequestParam(value = "money",required = false)String money
            , @RequestParam(value = "title",required = false)String title
    )throws Exception{

        return null;
    }


    /**
     * @Description 随机数生成工具类
     * @Author  muxu
     * @Date  2019/10/18
     * @Time 16:22
     * @Return
     * @Params
    **/

       public  String getRandomStringByLength(int length) {
             String base = "abcdefghijklmnopqrstuvwxyz0123456789";
             Random random = new Random();
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < length; i++) {
             int number = random.nextInt(base.length());
             sb.append(base.charAt(number));
             }
             return sb.toString();
       }

}
