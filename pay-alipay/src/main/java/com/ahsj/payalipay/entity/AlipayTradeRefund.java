package com.ahsj.payalipay.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AlipayTradeRefund {
    private Long id;

    private Long updateUserId;

    private Date updateDate;

    private Long createUserId;

    private Date createDate;

    private String buyerLogonId;

    private Date gmtRefundPay;

    private String outTradeNo;

    private BigDecimal refundFee;

    private BigDecimal sendBackFee;

    private String tradeNo;

    private String requestNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId == null ? null : buyerLogonId.trim();
    }

    public Date getGmtRefundPay() {
        return gmtRefundPay;
    }

    public void setGmtRefundPay(Date gmtRefundPay) {
        this.gmtRefundPay = gmtRefundPay;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public BigDecimal getSendBackFee() {
        return sendBackFee;
    }

    public void setSendBackFee(BigDecimal sendBackFee) {
        this.sendBackFee = sendBackFee;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo == null ? null : requestNo.trim();
    }
}