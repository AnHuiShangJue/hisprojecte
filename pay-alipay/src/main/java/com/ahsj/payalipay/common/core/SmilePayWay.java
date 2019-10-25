/*
package com.ahsj.payalipay.common.core;


import com.ahsj.payalipay.alipay.api.AlipayCallBack;
import com.ahsj.payalipay.alipay.api.request.TradepayParam;
import com.ahsj.payalipay.common.utils.AlipayConfig;
import com.ahsj.payalipay.common.utils.SmilePayConfig;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.ZolozAuthenticationCustomerSmilepayInitializeRequest;
import com.alipay.zoloz.smile2pay.service.Zoloz;
import com.alipay.zoloz.smile2pay.service.ZolozCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.EmptyUtil;

import java.util.Map;
import java.util.UUID;

import static com.ahsj.payalipay.controller.MerchantInfo.mockInfo;

*/
/**
 * @Description 扫脸支付的一些方法
 * @Author dingli
 * @Date 2019/10/21
 * @Time 13:32
 **//*

public class SmilePayWay {
*/
/** className SmilePayWay
 *@author dingli
 *@date 2019/10/21 13:32
 *//*

    */
/**
     * 发起刷脸支付请求，先zolozGetMetaInfo获取本地app信息，然后调用服务端获取刷脸付协议.
     *//*

    private static final String TAG = "smiletopay";
    // Button mSmilePayButton;

    public static final String KEY_INIT_RESP_NAME = "zim.init.resp";

    private Logger Log = LoggerFactory.getLogger(SmilePayWay.class.getSimpleName());

    private Zoloz zoloz;

    static final String CODE_SUCCESS = "1000";
    static final String CODE_EXIT = "1003";
    static final String CODE_TIMEOUT = "1004";
    static final String CODE_OTHER_PAY = "1005";

    static final String TXT_EXIT = "已退出刷脸支付";
    static final String TXT_TIMEOUT = "操作超时";
    static final String TXT_OTHER_PAY = "已退出刷脸支付";
    static final String TXT_OTHER = "抱歉未支付成功，请重新支付";

    //刷脸支付相关
    static final String SMILEPAY_CODE_SUCCESS = "10000";
    static final String SMILEPAY_SUBCODE_LIMIT = "ACQ.PRODUCT_AMOUNT_LIMIT_ERROR";
    static final String SMILEPAY_SUBCODE_BALANCE_NOT_ENOUGH = "ACQ.BUYER_BALANCE_NOT_ENOUGH";
    static final String SMILEPAY_SUBCODE_BANKCARD_BALANCE_NOT_ENOUGH = "ACQ.BUYER_BANKCARD_BALANCE_NOT_ENOUGH";

    static final String SMILEPAY_TXT_LIMIT = "刷脸支付超出限额，请选用其他支付方式";
    static final String SMILEPAY_TXT_EBALANCE_NOT_ENOUGH = "账户余额不足，支付失败";
    static final String SMILEPAY_TXT_BANKCARD_BALANCE_NOT_ENOUGH = "账户余额不足，支付失败";
    static final String SMILEPAY_TXT_FAIL = "抱歉未支付成功，请重新支付";
    static final String SMILEPAY_TXT_SUCCESS = "刷脸支付成功";

    private void smilePay() {
        zoloz.zolozGetMetaInfo(mockInfo(), new ZolozCallback() {
            @Override
            public void response(Map smileToPayResponse) {
                if (smileToPayResponse == null)
                    String code = (String) smileToPayResponse.get("code");
                String metaInfo = (String) smileToPayResponse.get("metainfo");

                //获取metainfo成功
                if (CODE_SUCCESS.equalsIgnoreCase(code) && metaInfo != null) {
                    Log.info(TAG, "-----------------metanfo is:" + metaInfo + "--------------");

                    //正式接入请上传metaInfo到服务端，不要忘记UrlEncode，使用服务端使用的sdk从服务端访问openapi获取zimId和zimInitClientData；
                    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                            SmilePayConfig.appId,
                            AlipayConfig.appKey,
                            "json",
                            "utf-8",
                            null,
                            "RSA2");
                    ZolozAuthenticationCustomerSmilepayInitializeRequest request
                            = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
                    request.setBizContent(metaInfo);

                    //起一个异步线程发起网络请求
                    try {
                        alipayClient.execute(request, ""
                              */
/*  new AlipayCallBack() {
                                    @Override
                                    public AlipayResponse  onResponse(AlipayResponse response) {
                                        if (!EmptyUtil.Companion.isNullOrEmpty(response) && SMILEPAY_CODE_SUCCESS.equals(response.getCode())) {
                                            try {
                                                ZolozAuthenticationCustomerSmilepayInitializeResponse zolozResponse
                                                        = (ZolozAuthenticationCustomerSmilepayInitializeResponse) response;

                                                String result = zolozResponse.getResult();
                                                JSONObject resultJson = JSON.parseObject(result);
                                                String zimId = resultJson.getString("zimId");
                                                String zimInitClientData = resultJson.getString("zimInitClientData");
                                                //人脸调用
                                                smile(zimId, zimInitClientData);
                                            } catch (Exception e) {
                                                //  promptText(TXT_OTHER);
                                                Log.info("--------"+TXT_OTHER+"-----------");
                                            }
                                        } else {
                                            // promptText(TXT_OTHER);
                                            Log.info("--------"+TXT_OTHER+"-----------");
                                        }
                                   return null;
                                    }
                                }*//*

                        );
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.info("--------" + TXT_OTHER + "-----------");
                    //  promptText(TXT_OTHER);
                }
            }
        });
    }


    */
/**
     * 发起刷脸支付请求.
     *
     * @param zimId    刷脸付token，从服务端获取，不要mock传入
     * @param protocal 刷脸付协议，从服务端获取，不要mock传入
     *//*

    private void smile(String zimId, String protocal) {
        Map params = new HashMap();
        params.put(KEY_INIT_RESP_NAME, protocal);
        zoloz.zolozVerify(zimId, params, new ZolozCallback() {
            @Override
            public void response(final Map smileToPayResponse) {
                if (smileToPayResponse == null) {
                    //promptText(TXT_OTHER);
                    Log.info("-----------smileToPayResponse为空-------------");
                    return;
                }

                String code = (String) smileToPayResponse.get("code");
                String fToken = (String) smileToPayResponse.get("ftoken");
                String subCode = (String) smileToPayResponse.get("subCode");
                String msg = (String) smileToPayResponse.get("msg");
                //  Log.d(TAG, "ftoken is:" + fToken);
                Log.info(TAG, "---------ftoken is:" + fToken + "----------");

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
                        // promptText(SMILEPAY_TXT_FAIL);
                        Log.info("------------" + SMILEPAY_TXT_FAIL + "-----------");
                    }
                } else if (CODE_EXIT.equalsIgnoreCase(code)) {
                    // promptText(TXT_EXIT);
                    Log.info("------------" + TXT_EXIT + "-----------");
                } else if (CODE_TIMEOUT.equalsIgnoreCase(code)) {
                    // promptText(TXT_TIMEOUT);
                    Log.info("------------" + TXT_TIMEOUT + "-----------");
                } else if (CODE_OTHER_PAY.equalsIgnoreCase(code)) {
                    //  promptText(TXT_OTHER_PAY);
                    Log.info("------------" + TXT_OTHER_PAY + "-----------");
                } else {
                    if (!EmptyUtil.Companion.isNullOrEmpty(subCode)) {
                        Log.info("------------" + TXT_OTHER + subCode + "-----------");
                    }
                }
            }
        });
    }

    */
/**
     * 发起刷脸支付请求.
     *
     * @param txt toast文案
     *//*

  */
/*  void promptText(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
            }
        });
    }*//*


    */
/**
     * 发起刷脸支付请求.
     *
     * @param ftoken 刷脸返回的token
     * @param amount 支付金额
     *//*

    private void pay(String ftoken, String amount) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                SmilePayConfig.appId,
                AlipayConfig.appKey,
                "json",
                "utf-8",
                null,
                "RSA2");
        AlipayTradePayRequest alipayTradePayRequest = new AlipayTradePayRequest();
        TradepayParam tradepayParam = new TradepayParam();
        tradepayParam.setOut_trade_no(UUID.randomUUID().toString());

        //auth_code和scene填写需要注意
        tradepayParam.setAuth_code(ftoken);
        tradepayParam.setScene("security_code");
        tradepayParam.setSubject("smilepay");
        tradepayParam.setStore_id("smilepay test");
        tradepayParam.setTimeout_express("5m");
        tradepayParam.setTotal_amount(amount);
        alipayTradePayRequest.setBizContent(JSON.toJSONString(tradepayParam));
        alipayClient.execute(alipayTradePayRequest,
                new AlipayCallBack() {

                    @Override
                    public AlipayResponse onResponse(AlipayResponse response) {
                        if (response != null && SMILEPAY_CODE_SUCCESS.equals(response.getCode())) {
                            // promptText(SMILEPAY_TXT_SUCCESS);
                            Log.info("--------" + SMILEPAY_TXT_SUCCESS + "-----------");
                        } else {
                            if (response != null) {
                                String subCode = response.getSubCode();
                                if (SMILEPAY_SUBCODE_LIMIT.equalsIgnoreCase(subCode)) {
                                    //  promptText(SMILEPAY_TXT_LIMIT);
                                    Log.info("--------" + SMILEPAY_TXT_LIMIT + "-----------");
                                } else if (SMILEPAY_SUBCODE_BALANCE_NOT_ENOUGH.equalsIgnoreCase(subCode)) {
                                    //promptText(SMILEPAY_TXT_EBALANCE_NOT_ENOUGH);
                                    Log.info("--------" + SMILEPAY_TXT_EBALANCE_NOT_ENOUGH + "-----------");
                                } else if (SMILEPAY_SUBCODE_BANKCARD_BALANCE_NOT_ENOUGH.equalsIgnoreCase(subCode)) {
                                    //promptText(SMILEPAY_TXT_BANKCARD_BALANCE_NOT_ENOUGH);
                                    Log.info("--------" + SMILEPAY_TXT_BANKCARD_BALANCE_NOT_ENOUGH + "-----------");
                                } else {
                                    //  promptText(SMILEPAY_TXT_FAIL);
                                    Log.info("--------" + SMILEPAY_TXT_FAIL + "-----------");
                                }
                            } else {
                                //   promptText(SMILEPAY_TXT_FAIL);
                                Log.info("--------" + SMILEPAY_TXT_FAIL + "-----------");
                            }
                        }
                        return null;
                    }
                });
        return;
    }

}
*/
