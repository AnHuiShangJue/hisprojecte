/*
package com.ahsj.payalipay.controller;

import com.ahsj.payalipay.alipay.api.AlipayCallBack;
import com.ahsj.payalipay.common.utils.AlipayConfig;
import com.ahsj.payalipay.common.utils.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZolozAuthenticationCustomerSmilepayInitializeRequest;
import com.alipay.api.response.ZolozAuthenticationCustomerSmilepayInitializeResponse;
import com.alipay.zoloz.smile2pay.service.Zoloz;
import com.alipay.zoloz.smile2pay.service.ZolozCallback;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;
import utils.EmptyUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static com.ahsj.payalipay.controller.MerchantInfo.mockInfo;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.smile;

*/
/**
 * @Description
 * @Author dingli
 * @Date 2019/10/17
 * @Time 18:06
 **//*

@RestController
public class SmilePayController {

    private static final String TAG = "smiletopay";
    Button mSmilePayButton;

    public static final String KEY_INIT_RESP_NAME = "zim.init.resp";
    private Zoloz zoloz;

    private Logger log = LoggerFactory.getLogger(SmilePayController.class.getSimpleName());

    */
/**
     * className SmilePayController
     *
     * @author dingli
     * @date 2019/10/17 18:06
     *//*

*/
/*
    public void oauthToHis() throws Exception {

        String alipayRedirectOauthUri = "restapi/smilePay/getAuthCode";
        String alipayOauthurl = AlipayConfig.ALIPAY_OAUTHURL;//第三应用授权地址
        String oauthToHis = "";
        oauthToHis = alipayOauthurl + "?" + "app_id=" + AlipayConfig.app_id + "&redirect_uri=" + AlipayConfig.redirect_uri;
        log.info("------" + oauthToHis + "-------");
    }
*//*


    private void smilePay() throws Exception {
        zoloz.zolozGetMetaInfo(mockInfo(), new ZolozCallback() {
            @Override
            public void response(Map smileToPayResponse) {

                if (EmptyUtil.Companion.isNullOrEmpty(smileToPayResponse)) {
                    log.info("---------response is null" + Constants.TXT_OTHER + "--------");
                    return;
                }

                String code = (String) smileToPayResponse.get("code");
                String metaInfo = (String) smileToPayResponse.get("metainfo");

                //获取metainfo成功
                if (Constants.CODE_SUCCESS.equalsIgnoreCase(code) && metaInfo != null) {
                    log.info("---------metanfo is:" + metaInfo + "--------");
                    //正式接入请上传metaInfo到服务端，不要忘记UrlEncode，使用服务端使用的sdk从服务端访问openapi获取zimId和zimInitClientData；
                    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                            AlipayConfig.app_id,
                            AlipayConfig.appKey,
                            AlipayConfig.json,
                            AlipayConfig.charset,
                            null,
                            AlipayConfig.sign_type);
                    ZolozAuthenticationCustomerSmilepayInitializeRequest request
                            = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
                    request.setBizContent(metaInfo);

                    //起一个异步线程发起网络请求
                    alipayClient.execute(request,
                            new AlipayCallBack() {
                                @Override
                                public AlipayResponse onResponse(AlipayResponse response) {
                                    if (response != null && Constants.SMILEPAY_CODE_SUCCESS.equals(response.getCode())) {
                                        ZolozAuthenticationCustomerSmilepayInitializeResponse zolozResponse
                                                = (ZolozAuthenticationCustomerSmilepayInitializeResponse) response;

                                        String result = zolozResponse.getResult();
                                        JSONObject resultJson = JSON.parseObject(result);
                                        String zimId = resultJson.getString("zimId");
                                        String zimInitClientData = resultJson.getString("zimInitClientData");
                                        //人脸调用
                                        smile(zimId, zimInitClientData);
                                        log.info("--------------" + Constants.TXT_OTHER + "---------------");
                                    }
                                    return "a";
                                }
                            });
                } else {
                    log.info("--------------" + TXT_OTHER + "---------------");
                }
            }
        });
    }

    private void smile(String zimId, String protocal) {
        Map params = new HashMap();
        params.put(KEY_INIT_RESP_NAME, protocal);
        zoloz.zolozVerify(zimId, params, new ZolozCallback() {
            @Override
            public void response(final Map smileToPayResponse) {
                if (smileToPayResponse == null) {
                    log.info("--------" + TXT_OTHER + "------------");
                    return;
                }
                String code = (String) smileToPayResponse.get("code");
                String fToken = (String) smileToPayResponse.get("ftoken");
                String subCode = (String) smileToPayResponse.get("subCode");
                String msg = (String) smileToPayResponse.get("msg");
                log.info(TAG, "ftoken is:" + fToken);
                //刷脸成功
                if (CODE_SUCCESS.equalsIgnoreCase(code) && fToken != null) {
                    //promptText("刷脸成功，返回ftoken为:" + fToken);
                    //这里在Main线程，网络等耗时请求请放在异步线程中
                    //后续这里可以发起支付请求
                    //https://docs.open.alipay.com/api_1/alipay.trade.pay
                    //需要修改两个参数
                    //scene固定为security_code
                    //auth_code为这里获取到的fToken值
                    //支付一分钱，支付需要在服务端发起，这里只是模拟
                    try {
                        pay(fToken, "0.01");
                    } catch (Exception e) {
                        promptText(SMILEPAY_TXT_FAIL);
                    }
                } else if (CODE_EXIT.equalsIgnoreCase(code)) {
                    promptText(TXT_EXIT);
                } else if (CODE_TIMEOUT.equalsIgnoreCase(code)) {
                    promptText(TXT_TIMEOUT);
                } else if (CODE_OTHER_PAY.equalsIgnoreCase(code)) {
                    promptText(TXT_OTHER_PAY);
                } else {
                    String txt = TXT_OTHER;
                    if (!TextUtils.isEmpty(subCode)) {
                        txt = txt + "(" + subCode + ")";
                    }
                    promptText(txt);
                }
            }

        });
    }
}
*/
