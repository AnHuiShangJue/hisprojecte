package com.ahsj.payalipay.common.utils;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/21
 * @Time 10:33
 **/
public class SmilePayConfig {

    public SmilePayConfig() {
    }

    /**
     * className SmilePayConfig
     *
     * @author dingli
     * @date 2019/10/21 10:33
     */
    public static String merchantId = "";      // 必选，商户ID，来自蚂蚁开放平台，示例："2088011211821038"
    public static String partnerId = "";       // 必选，合作商户ID，来自蚂蚁开放平台，示例："2088011211821038"
    public static String deviceNum = "";       // 必选，设备唯一编号，商户自定义
    public static String deviceMac = "";       // 必选，设备Mac 地址，例如："CC:B8:A8:0F:63:3C"
    public static String brandCode = "";       // 必选，品牌编码，商户自定义
    public static String appId = "";           // 可选，商户应用ID，来自蚂蚁开发平台
    public static String storeCode = "";       // 可选，门店编码，商户自定义
    public static String areaCode = "";        // 可选，区域编码，商户自定义
    public static String geo = "";             // 可选，机具坐标，例如"0.000000,0.000000"
    public static String wifiMac = "";         // 可选，wifi Mac地址
    public static String wifiName = "";        // 可选，wifi Mac名字


}
