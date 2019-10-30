package com.ahsj.wxapplet.controller;


import com.ahsj.wxapplet.MyApplicationListener;
import com.ahsj.wxapplet.common.utils.*;
import com.ahsj.wxapplet.entity.OrderInfo;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.ahsj.wxapplet.common.utils.PayUtil.getContentBytes;

@Controller
@RequestMapping("/api/WxPay")
public class WxPayController {


    @Value("${wxapplet.config.weixinpay.notifyurl}")
    private String notify_url;

    private final String trade_type = "JSAPI";

    private static Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

    private final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    /**
     * @Description 微信小程序支付接口
     * @Author  muxu
     * @Date  2019/10/19
     * @Time 16:53
     * @Return java.util.Map
     * @Params [request, openid, mch_id, money, title, appId]
     **/
    @RequestMapping(value = "/weixin/payment.ahsj")
    @ResponseBody
    public Map payment(HttpServletRequest request
            , @RequestParam(value = "openid",required = false)String openid
            , @RequestParam(value = "mch_id",required = false)String mch_id
            , @RequestParam(value = "money",required = false)Integer money
            , @RequestParam(value = "title",required = false)String title
            , @RequestParam(value = "appId",required = false)String appId
            , @RequestParam(value = "spbillCreateIp",required = false)String spbillCreateIp
    )throws Exception{
        Map map = new HashMap();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAppid(appId);
        orderInfo.setMchId(mch_id);
        orderInfo.setNonceStr(getRandomStringByLength(32));
        orderInfo.setBody(title);
        orderInfo.setOutTradeNo(getRandomStringByLength(32));
        orderInfo.setTotalFee(money);
        orderInfo.setSpbillCreateIp(spbillCreateIp);
        orderInfo.setNotifyUrl(notify_url);
        orderInfo.setTradeType(trade_type);
        orderInfo.setOpenid(openid);
        orderInfo.setSignType("MD5");
        //生成签名
        String sign = Signature.getSign(orderInfo);
        orderInfo.setSign(sign);

        String result = HttpRequest.sendPost(url, orderInfo);
        System.out.println(result);
        XStream xStream = new XStream();
        xStream.alias("xml", OrderReturnInfo.class);

        OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
        if ("SUCCESS".equals(returnInfo.getReturn_code()) && returnInfo.getReturn_code().equals(returnInfo.getResult_code())) {
            SignInfo signInfo = new SignInfo();
            signInfo.setAppId(Configure.getAppID());
            long time = System.currentTimeMillis() / 1000;
            signInfo.setTimeStamp(String.valueOf(time));
            signInfo.setNonceStr(getRandomStringByLength(32));
            signInfo.setRepay_id("prepay_id=" + returnInfo.getPrepay_id());
            signInfo.setSignType("MD5");
            //生成签名
            String sign1 = Signature.getSign(signInfo);
            Map payInfo = new HashMap();
            payInfo.put("timeStamp", signInfo.getTimeStamp());
            payInfo.put("nonceStr", signInfo.getNonceStr());
            payInfo.put("package", signInfo.getRepay_id());
            payInfo.put("signType", signInfo.getSignType());
            payInfo.put("paySign", sign1);
            map.put("status", 200);
            map.put("msg", "统一下单成功!");
            map.put("data", payInfo);
            // 此处可以写唤起支付前的业务逻辑

            // 业务逻辑结束
            return map;
        }else {
            map.put("status", 500);
            map.put("msg", "统一下单失败!");
            map.put("data", null);
            return map;
        }
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


    /**
     * @Description 微信支付回调
     * @Author  muxu
     * @Date  2019/10/19
     * @Time 16:53
     * @Return void
     * @Params [request, response]
     **/
    @RequestMapping(value = "/weixin/callback")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String sign = PayUtil.sign(validStr, Configure.getKey(), "utf-8").toUpperCase();//拼装生成服务器端验证的签名
            // 因为微信回调会有八次之多,所以当第一次回调成功了,那么我们就不再执行逻辑了

            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if(sign.equals(map.get("sign"))){
                /**此处添加自己的业务逻辑代码start**/
                // bla bla bla....
                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                System.out.println("微信支付回调失败!签名不一致");
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();

    }


    /**
          * 签名字符串
          * @param text 需要签名的字符串
          * @param key 密钥
          * @param input_charset 编码格式
          * @return 签名结果
          */
    public static String sign(String text, String key, String input_charset) {
        text = text + "&key=" + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
}
