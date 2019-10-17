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
    OEDER_CLOSE(60, "订单关闭"),
    TRADE_FINISHED(3,"交易结束并不可退款"),
    TRADE_SUCCESS(2,"交易支付成功"),
    TRADE_CLOSED(1,"未付款交易超时关闭或支付完成后全额退款"),
    WAIT_BUYER_PAY(0,"交易创建并等待买家付款");

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
