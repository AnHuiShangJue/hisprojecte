package com.ahsj.payalipay.common.utils;

/**
 * @Description
 * @Author dingli
 * @Date 2019/10/16
 * @Time 13:55
 **/
public class Constants {
    /**
     * className Constants
     *
     * @author dingli
     * @date 2019/10/16 13:55
     */
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    public static final String TRADE_FINISHED = "TRADE_FINISHED";
    public static final String CODE_SUCCESS = "1000";
    public static final String CODE_EXIT = "1003";
    public static final String CODE_TIMEOUT = "1004";
    public static final String CODE_OTHER_PAY = "1005";

    public static final String TXT_EXIT = "已退出刷脸支付";
    public static final String TXT_TIMEOUT = "操作超时";
    public static final String TXT_OTHER_PAY = "已退出刷脸支付";
    public static final String TXT_OTHER = "抱歉未支付成功，请重新支付";

    //刷脸支付相关
    public static final String SMILEPAY_CODE_SUCCESS = "10000";
    public static final String SMILEPAY_SUBCODE_LIMIT = "ACQ.PRODUCT_AMOUNT_LIMIT_ERROR";
    public static final String SMILEPAY_SUBCODE_BALANCE_NOT_ENOUGH = "ACQ.BUYER_BALANCE_NOT_ENOUGH";
    public static final String SMILEPAY_SUBCODE_BANKCARD_BALANCE_NOT_ENOUGH = "ACQ.BUYER_BANKCARD_BALANCE_NOT_ENOUGH";

    public static final String SMILEPAY_TXT_LIMIT = "刷脸支付超出限额，请选用其他支付方式";
    public static final String SMILEPAY_TXT_EBALANCE_NOT_ENOUGH = "账户余额不足，支付失败";
    public static final String SMILEPAY_TXT_BANKCARD_BALANCE_NOT_ENOUGH = "账户余额不足，支付失败";
    public static final String SMILEPAY_TXT_FAIL = "抱歉未支付成功，请重新支付";
    public static final String SMILEPAY_TXT_SUCCESS = "刷脸支付成功";

    public Constants() {
    }
}
