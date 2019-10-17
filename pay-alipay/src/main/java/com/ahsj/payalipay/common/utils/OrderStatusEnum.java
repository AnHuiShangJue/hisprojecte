package com.ahsj.payalipay.common.utils;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/16
 * @Time 13:16
 **/
public enum OrderStatusEnum {

    CANCELED(0, "已取消"),
    NO_PAY(10, "未支付"),
    PAID(20, "已付款"),
    SHIPPED(40, "已发货"),
    ORDER_SUCCESS(50, "订单完成"),
    OEDER_CLOSE(60, "订单关闭");


    private String value;
    private int code;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    OrderStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public enum PayPlatformEnum {
        ALIPAY(1, "支付宝");

        PayPlatformEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

}
