package com.ahsj.payalipay.common.utils;


public class AlipayConfig {

    public AlipayConfig() {
    }

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101300675365";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQX3sAFG4P1xbT5O4lIxsx9tLI0kUdtbNv+gKYmo4KwaC8Nab8DDtdWW1XRhq63xez4mGroTcsSRYCQOzrMRJ4iild3rBj+OGWifQm+81sxvBmXVkL1qWFCsfI24vMOI+FXSXWu2eaqJIE1I2kbZkyPZoFgp/npZPNqpnN78whBlGfboE4a+v2mFxJIOiyIjeIYph8GTD8fg3v2gs+rw9aSC3zaaHCm347oV6A34BvS0RZDqyWTzytEi5tC//bB9V2/5GUb1xktLVuUgtC/LqQNRX9drmsAnOUMPZ9GdxvHleYHdu6ZWKGs15i93G9N1RRo98FUu75ic87iJZZgOipAgMBAAECggEBAIovWYOSCmaO8QKPtIFHQNhp7wbYe98b80vSeHmoNRyGiFqc39+PtaJ73fQS3bLPpdg/bkzgWfbBXZd6nUngBlXpp/55D0whA4sbL93AVbcwExlnb7utD3BwCRlvSjeGS5jSt0z4wwl9H4YQDCK95Ev6Olnb1UHbccUA7YX3Z+KSvW1RARQDIx1wD807nScGGMOdXctbl6GtTIde+q33RK7SS/z83X0lBPYgNn35LrXp2qH6gTm8XyGqA+fGhdUiB+LqnL+3GnhyybiPMdefHYWB/B9kXSZgvrZhtH2M78H1koHsunWaJDL5tmL5A6i0c8XVntnk6YW0aycXfTMW6oECgYEA3+4V+b6nW9FWzo/FDC4ibCucKOJb5KfgXGEEZv3/LHFoVBycrjXwkJjq2gXaSIs1F9MeewqLkeyv7WUOysunJ7oDEKCTkJ+E8ik5cCGEK/Ev5ZyAo/GZHq9iFRlBGAEuWxTFIfS8j5whREQLL5d7MV3/ge8yhO0dmlITgIn9FyUCgYEApQybG1rMqd7aIhd4vvUloqRqGV8xvpzhaiscSROoAdYB7MdBmIQi1s0aTmvfZEwSMEMeHhHlUxCIPA+vL6h4COgKQBHY4Z08SoVHtWKiW/8bpQ9vW2PFFj5+MXsWQZPBeeMLKqmYZOa6BqzysiBINvTHcSrgPscuymWcPxF7RjUCgYB9oXVyyCPcv3WULdS/IKLvTQgvZ89mWUjmiweNQLMInBeMGQFOO7Tbau9XojD0wiTnWOqg3SmwlLme1jOWUutUvmc5zNz9JoHP2pD+987PWGEm0MbbM5BURtEgRSrf7Mm1D9IJWuTvLQj846/i43maGMJgg3I0YVNaYPHQC5qSgQKBgAz1snAxbwSfpQ0sPqTv5uagdlxLp0JKW0DU5cGvM3ABd+E5vCLXrUykij5CJiZf+sWjX4gI67LcB3Z/3MenONWqMrPZMuh0SjmnwXuypQEIRQlu7tWEPgODILGN8q9vhK+nbkcJThgOLek8R0pJUqJOEiMGtNj5Ful5lQ02uDFJAoGBAKoerU6G34nSPNUpo+14h53RMlGVXQOnDj+d1bWAaCzNKF4bj2cNkBDhZ5hOgWri+8APKjaGMI+tftsT/alEf09D0Y5ctkGQt/kEfNjvxQBgVxJNR5GgIvia/XwsegY2lPBqe0oF2SXCOAc2kisE+UjFK9pEHH2wCFkSsKapt06E";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiYlQbuhB4V/sqf43lHLE8Sv5uTEVQUwGmUpE8NQsFXuOSK9hoXtdb65XglUxr2gAYXPdt9gTvSt4AbwFsfKAGNCjVGf9vKL3atcUoOzGh6KnMICUY5U+1Gz6tXmWUCOaIsbt5SZTm6/s86VoAUw2OQm+efOtB27J/tHkxImZzjXxMRrj/EV4vx81WlaIFpgmRQc89XBRXg6/WmiWk9AtN0920VUAyErzf2k5MEYNA0p9UgcpSQi3l3TweVxIwBiI0GXcJZwTLzAcVDN4AmzPPWfFsBIepEVr9TdAHoVvbGZOj86rDllecLQBajXyBFLFNEt/kPCrEbkMEvFp1z14bwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8022/payalipay/api/order/alipay_asynchronous.do";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问  return_url.jsp
    public static String return_url = "http://localhost:8022/payalipay/api/order/alipay_callback.do";


    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝沙箱网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public static String json = "json";

    public static String appKey = "";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


    public static String getApp_id() {
        return app_id;
    }

    public static void setApp_id(String app_id) {
        AlipayConfig.app_id = app_id;
    }

    public static String getMerchant_private_key() {
        return merchant_private_key;
    }

    public static void setMerchant_private_key(String merchant_private_key) {
        AlipayConfig.merchant_private_key = merchant_private_key;
    }

    public static String getAlipay_public_key() {
        return alipay_public_key;
    }

    public static void setAlipay_public_key(String alipay_public_key) {
        AlipayConfig.alipay_public_key = alipay_public_key;
    }

    public static String getNotify_url() {
        return notify_url;
    }

    public static void setNotify_url(String notify_url) {
        AlipayConfig.notify_url = notify_url;
    }

    public static String getReturn_url() {
        return return_url;
    }

    public static void setReturn_url(String return_url) {
        AlipayConfig.return_url = return_url;
    }

    public static String getSign_type() {
        return sign_type;
    }

    public static void setSign_type(String sign_type) {
        AlipayConfig.sign_type = sign_type;
    }

    public static String getCharset() {
        return charset;
    }

    public static void setCharset(String charset) {
        AlipayConfig.charset = charset;
    }

    public static String getGatewayUrl() {
        return gatewayUrl;
    }

    public static void setGatewayUrl(String gatewayUrl) {
        AlipayConfig.gatewayUrl = gatewayUrl;
    }

}
