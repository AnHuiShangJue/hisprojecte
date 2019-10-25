package com.ahsj.payalipay.controller;

import com.ahsj.payalipay.common.utils.SmilePayConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bruce on 2018/6/15.
 */

public class MerchantInfo {


    /**
     * mock数据，真实商户请填写真实信息.
     */
    public static Map mockInfo() {
        Map merchantInfo = new HashMap();
        //以下信息请根据真实情况填写
        //商户id
        merchantInfo.put("partnerId", SmilePayConfig.partnerId);
        merchantInfo.put("merchantId", SmilePayConfig.merchantId);
        merchantInfo.put("appId", SmilePayConfig.appId);
        merchantInfo.put("storeCode", SmilePayConfig.storeCode);
        merchantInfo.put("brandCode", SmilePayConfig.brandCode);
        merchantInfo.put("areaCode", SmilePayConfig.areaCode);
        merchantInfo.put("geo", SmilePayConfig.geo);
        merchantInfo.put("wifiMac", SmilePayConfig.wifiMac);
        merchantInfo.put("wifiName", SmilePayConfig.wifiName);
        merchantInfo.put("deviceNum", SmilePayConfig.deviceNum);
        merchantInfo.put("deviceMac", SmilePayConfig.deviceMac);
        return merchantInfo;
    }
}
