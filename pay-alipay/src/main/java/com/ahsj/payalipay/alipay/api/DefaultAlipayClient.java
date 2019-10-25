/*
package com.ahsj.payalipay.alipay.api;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Handler;

import com.ahsj.payalipay.alipay.api.http.HttpUtils;
import com.alibaba.fastjson.JSONObject;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.api.*;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.AlipayParser;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.http.HttpUtils;
import org.apache.http.util.TextUtils;
import sun.rmi.runtime.Log;

*/
/**
 * Created by bruce on 2017/12/25.
 *//*

public class DefaultAlipayClient implements AlipayClient {

    private final String TAG = "DefaultAlipayClient";
    private String serverUrl;
    private String appId;
    private String privateKey;
    private String prodCode;
    private String format = com.alipay.api.AlipayConstants.FORMAT_JSON;
    private String sign_type = com.alipay.api.AlipayConstants.SIGN_TYPE_RSA;

    private String encryptType = com.alipay.api.AlipayConstants.ENCRYPT_TYPE_AES;

    private String encryptKey;

    private String alipayPublicKey;

    private String charset;

    private int connectTimeout = 3000;
    private int readTimeout = 15000;
    private static ThreadPoolProxy threadPoolProxy;
    private Handler mMainHandler;

    public DefaultAlipayClient(String serverUrl, String appId, String privateKey) {
        this.serverUrl = serverUrl;
        this.appId = appId;
        this.privateKey = privateKey;
        this.alipayPublicKey = null;

        mMainHandler = new Handler(Looper.getMainLooper());
    }

    public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format) {
        this(serverUrl, appId, privateKey);
        this.format = format;
    }

    public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset) {
        this(serverUrl, appId, privateKey);
        this.format = format;
        this.charset = charset;
    }

    public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String alipayPulicKey) {
        this(serverUrl, appId, privateKey);
        this.format = format;
        this.charset = charset;
        this.alipayPublicKey = alipayPulicKey;
    }

    public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String alipayPulicKey, String signType) {

        this(serverUrl, appId, privateKey, format, charset, alipayPulicKey);

        this.sign_type = signType;
    }

    public DefaultAlipayClient(String serverUrl, String appId, String privateKey, String format,
                               String charset, String alipayPulicKey, String signType,
                               String encryptKey, String encryptType) {

        this(serverUrl, appId, privateKey, format, charset, alipayPulicKey, signType);
        this.encryptType = encryptType;
        this.encryptKey = encryptKey;
    }



    */
/**
     * 组装接口参数，处理加密、签名逻辑
     *
     * @param request
     * @param accessToken
     * @param appAuthToken
     * @return
     *//*

    private <T extends com.alipay.api.AlipayResponse> RequestParametersHolder getRequestHolderWithSign(com.alipay.api.AlipayRequest<?> request,
                                                                                                       String accessToken,
                                                                                                       String appAuthToken)
            throws com.alipay.api.AlipayApiException {
        RequestParametersHolder requestHolder = new RequestParametersHolder();

        AlipayHashMap appParams = new AlipayHashMap(request.getTextParams());

        if (!TextUtils.isEmpty(appAuthToken)) {
            appParams.put(com.alipay.api.AlipayConstants.APP_AUTH_TOKEN, appAuthToken);
        }

        requestHolder.setApplicationParams(appParams);

        if (TextUtils.isEmpty(charset)) {
            charset = com.alipay.api.AlipayConstants.CHARSET_UTF8;
        }

        AlipayHashMap protocalMustParams = new AlipayHashMap();
        protocalMustParams.put(com.alipay.api.AlipayConstants.METHOD, request.getApiMethodName());
        protocalMustParams.put(com.alipay.api.AlipayConstants.VERSION, request.getApiVersion());
        protocalMustParams.put(com.alipay.api.AlipayConstants.APP_ID, this.appId);
        protocalMustParams.put(com.alipay.api.AlipayConstants.SIGN_TYPE, this.sign_type);
        //protocalMustParams.put(AlipayConstants.TERMINAL_TYPE, request.getTerminalType());
        //protocalMustParams.put(AlipayConstants.TERMINAL_INFO, request.getTerminalInfo());
        //protocalMustParams.put(AlipayConstants.NOTIFY_URL, request.getNotifyUrl());
        //protocalMustParams.put(AlipayConstants.RETURN_URL, request.getReturnUrl());
        protocalMustParams.put(com.alipay.api.AlipayConstants.CHARSET, charset);

        if (request.isNeedEncrypt()) {
            protocalMustParams.put(com.alipay.api.AlipayConstants.ENCRYPT_TYPE, this.encryptType);
        }

        Long timestamp = System.currentTimeMillis();
        DateFormat df = new SimpleDateFormat(com.alipay.api.AlipayConstants.DATE_TIME_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone(com.alipay.api.AlipayConstants.DATE_TIMEZONE));
        protocalMustParams.put(com.alipay.api.AlipayConstants.TIMESTAMP, df.format(new Date(timestamp)));
        requestHolder.setProtocalMustParams(protocalMustParams);

        AlipayHashMap protocalOptParams = new AlipayHashMap();
        protocalOptParams.put(com.alipay.api.AlipayConstants.FORMAT, format);
        //protocalOptParams.put(AlipayConstants.ACCESS_TOKEN, accessToken);
        //protocalOptParams.put(AlipayConstants.ALIPAY_SDK, AlipayConstants.SDK_VERSION);
        //protocalOptParams.put(AlipayConstants.PROD_CODE, request.getProdCode());
        requestHolder.setProtocalOptParams(protocalOptParams);

        if (!TextUtils.isEmpty(this.sign_type)) {

            String signContent = AlipaySignature.getSignatureContent(requestHolder);
            Log.i("Test", "signContent:" + signContent);
            try {

                protocalMustParams.put(com.alipay.api.AlipayConstants.SIGN,
                        AlipaySignature.rsaSign(signContent, privateKey, charset, this.sign_type));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            protocalMustParams.put(AlipayConstants.SIGN, "");
        }
        return requestHolder;
    }

    private <T extends com.alipay.api.AlipayResponse> void _execute(com.alipay.api.AlipayRequest<T> request, AlipayParser<T> parser,
                                                                    String authToken,
                                                                    String appAuthToken, AlipayCallBack callBack) {

        try {
            doPost(request, authToken, appAuthToken, callBack);
        } catch (com.alipay.api.AlipayApiException e) {

        }

    }

    */
/**
     * @param request
     * @param accessToken
     * @return
     *//*

    private <T extends com.alipay.api.AlipayResponse> void doPost(final AlipayRequest<T> request,
                                                                  String accessToken,
                                                                  String appAuthToken, final AlipayCallBack callBack)
            throws AlipayApiException {
        Map<String, Object> result = new HashMap<String, Object>();
        final RequestParametersHolder requestHolder = getRequestHolderWithSign(request, accessToken,
                appAuthToken);

        //String rsp = HttpUtils.doPost(url);
        final HashMap<String, String> params = new HashMap<String, String>();
        params.putAll(requestHolder.getProtocalMustParams());
        params.putAll(requestHolder.getProtocalOptParams());
        params.putAll(requestHolder.getApplicationParams());

        if (threadPoolProxy == null) {
            synchronized (DefaultAlipayClient.this) {
                if (threadPoolProxy == null) {
                    threadPoolProxy = new ThreadPoolProxy(3, 5, 10);
                }
            }
        }

        threadPoolProxy.executeTask(new Runnable() {
            @Override
            public void run() {

                String responseStr = HttpUtils.postData(serverUrl, params);
           //     Log.i(TAG, "response:" + responseStr);

                com.alipay.api.AlipayResponse response = getResponse(responseStr, request.getResponseClass());
                if (callBack != null) {
                    callBack.onResponse(response);
                }

            }
        });

    }

    private <T extends com.alipay.api.AlipayResponse> T getResponse(String rsp, Class<T> clazz) {

        JSONObject resObject = JSONObject.parseObject(rsp);

        String resultObj = resObject.get(getItemName(clazz)).toString();


        T obj = JSONObject.parseObject(resultObj, clazz);

        return obj;
    }

    private <T extends AlipayResponse> String getItemName(Class<T> clazz) {

        String inputStr = clazz.getSimpleName();
        String replaceStr = inputStr;
        for (int i = inputStr.length() - 1; i >= 0; i--) {
            char c = inputStr.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    replaceStr = replaceStr.replace("" + c, "_" + Character.toLowerCase(c));
                } else {
                    replaceStr = replaceStr.replace(c, Character.toLowerCase(c));
                }
                continue;
            }
        }

        return replaceStr;
    }

    @Override
    public <T extends AlipayResponse> T execute(AlipayRequest<T> request) throws AlipayApiException {
        return null;
    }

    @Override
    public <T extends AlipayResponse> T execute(AlipayRequest<T> request, String authToken) throws AlipayApiException {
        return null;
    }

    @Override
    public <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken, String appAuthToken) throws AlipayApiException {
        return null;
    }

    @Override
    public <T extends AlipayResponse> T pageExecute(AlipayRequest<T> request) throws AlipayApiException {
        return null;
    }

    @Override
    public <T extends AlipayResponse> T sdkExecute(AlipayRequest<T> request) throws AlipayApiException {
        return null;
    }

    @Override
    public <T extends AlipayResponse> T pageExecute(AlipayRequest<T> request, String method) throws AlipayApiException {
        return null;
    }

    @Override
    public <TR extends AlipayResponse, T extends AlipayRequest<TR>> TR parseAppSyncResult(Map<String, String> result, Class<T> requsetClazz) throws AlipayApiException {
        return null;
    }

    @Override
    public BatchAlipayResponse execute(BatchAlipayRequest request) throws AlipayApiException {
        return null;
    }
}
*/
