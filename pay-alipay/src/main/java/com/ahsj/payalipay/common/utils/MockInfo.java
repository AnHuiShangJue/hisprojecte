package com.ahsj.payalipay.common.utils;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/18
 * @Time 16:44
 **/
public class MockInfo {
    /**
     * className MockInfo
     *
     * @author dingli
     * @date 2019/10/18 16:44
     */
    public static String merchantId;      // 必选，商户ID，来自蚂蚁开放平台，示例："2088011211821038"
    public static String partnerId;       // 必选，合作商户ID，来自蚂蚁开放平台，示例："2088011211821038"
    public static String deviceNum;       // 必选，设备唯一编号，商户自定义
    public static String deviceMac;       // 必选，设备Mac 地址，例如："CC:B8:A8:0F:63:3C"
    public static String brandCode;       // 必选，品牌编码，商户自定义
    public static String appId;           // 可选，商户应用ID，来自蚂蚁开发平台
    public static String storeCode;       // 可选，门店编码，商户自定义
    public static String areaCode;        // 可选，区域编码，商户自定义
    public static String geo;             // 可选，机具坐标，例如"0.000000,0.000000"
    public static String wifiMac;         // 可选，wifi Mac地址
    public static String wifiName;        // 可选，wifi Mac名字
    public static String alipayStoreCode;     //支付宝门店编号
    public MockInfo() {
    }


    public static String getMerchantId() {
        return merchantId;
    }

    public static void setMerchantId(String merchantId) {
        MockInfo.merchantId = merchantId;
    }

    public static String getPartnerId() {
        return partnerId;
    }

    public static void setPartnerId(String partnerId) {
        MockInfo.partnerId = partnerId;
    }

    public static String getDeviceNum() {
        return deviceNum;
    }

    public static void setDeviceNum(String deviceNum) {
        MockInfo.deviceNum = deviceNum;
    }

    public static String getDeviceMac() {
        return deviceMac;
    }

    public static void setDeviceMac(String deviceMac) {
        MockInfo.deviceMac = deviceMac;
    }

    public static String getBrandCode() {
        return brandCode;
    }

    public static void setBrandCode(String brandCode) {
        MockInfo.brandCode = brandCode;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        MockInfo.appId = appId;
    }

    public static String getStoreCode() {
        return storeCode;
    }

    public static void setStoreCode(String storeCode) {
        MockInfo.storeCode = storeCode;
    }

    public static String getAreaCode() {
        return areaCode;
    }

    public static void setAreaCode(String areaCode) {
        MockInfo.areaCode = areaCode;
    }

    public static String getGeo() {
        return geo;
    }

    public static void setGeo(String geo) {
        MockInfo.geo = geo;
    }

    public static String getWifiMac() {
        return wifiMac;
    }

    public static void setWifiMac(String wifiMac) {
        MockInfo.wifiMac = wifiMac;
    }

    public static String getWifiName() {
        return wifiName;
    }

    public static void setWifiName(String wifiName) {
        MockInfo.wifiName = wifiName;
    }
}
