package com.ahsj.wxapplet.common.utils;
/**
 * @Description 配置类
 * @Author  muxu
 * @Date  2019/10/19
 * @Time 15:38
 * @Return 
 * @Params 
**/
public class Configure {
        // 商户支付秘钥
        private static String key = "wx9eec243b070bf766";
        //小程序ID    
        private static String appID = "wx42ebbFFFFFFFFFF";
        //商户号
        private static String mch_id = "1305638280";
        // 小程序的secret
        private static String secret = "8963ca9ce2dd28783d7373b920324474";

        public static String getSecret() {
        return secret;
        }

        public static void setSecret(String secret) {
        Configure.secret = secret;
        }

        public static String getKey() {
        return key;
        }

        public static void setKey(String key) {
        Configure.key = key;
        }

        public static String getAppID() {
        return appID;
        }

        public static void setAppID(String appID) {
        Configure.appID = appID;
        }

        public static String getMch_id() {
        return mch_id;
        }

        public static void setMch_id(String mch_id) {
        Configure.mch_id = mch_id;
        }

}
