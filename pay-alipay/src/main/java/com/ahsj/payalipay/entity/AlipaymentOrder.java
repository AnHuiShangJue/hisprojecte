package com.ahsj.payalipay.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AlipaymentOrder {
    private Long id;

    private Long updateUserId;

    private Date updateDate;

    private Long createUserId;

    private Date createDate;

    private String appId;

    private Date notifyTime;

    private Date gmtCreate;

    private Date gmtPayment;

    private Date gmtRefund;

    private Date gmtClose;

    private String tradeNo;

    private String outTradeNo;

    private String outBizNo;

    private String buyerLogonId;

    private String sellerId;

    private String sellerEmail;

    private BigDecimal totalAmount;

    private BigDecimal receiptAmount;

    private BigDecimal invoiceAmount;

    private BigDecimal buyerPayAmount;

    private Integer tradeStatus;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo == null ? null : outBizNo.trim();
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId == null ? null : buyerLogonId.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(BigDecimal buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Override
    public String toString() {
        return "AlipaymentOrder{" +
                "id=" + id +
                ", updateUserId=" + updateUserId +
                ", updateDate=" + updateDate +
                ", createUserId=" + createUserId +
                ", createDate=" + createDate +
                ", appId='" + appId + '\'' +
                ", notifyTime=" + notifyTime +
                ", gmtCreate=" + gmtCreate +
                ", gmtPayment=" + gmtPayment +
                ", gmtRefund=" + gmtRefund +
                ", gmtClose=" + gmtClose +
                ", tradeNo='" + tradeNo + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", outBizNo='" + outBizNo + '\'' +
                ", buyerLogonId='" + buyerLogonId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                ", totalAmount=" + totalAmount +
                ", receiptAmount=" + receiptAmount +
                ", invoiceAmount=" + invoiceAmount +
                ", buyerPayAmount=" + buyerPayAmount +
                ", tradeStatus=" + tradeStatus +
                '}';
    }
}