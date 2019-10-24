package com.ahsj.payalipay.alipay.api.request;


import com.ahsj.payalipay.alipay.api.AlipayHashMap;
import com.alipay.api.AlipayObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.response.ZolozAuthenticationCustomerSmilepayInitializeResponse;

import java.util.Map;

public class ZolozAuthenticationCustomerSmilepayInitializeRequest
        implements AlipayRequest<ZolozAuthenticationCustomerSmilepayInitializeResponse> {
    private AlipayHashMap udfParams;
    private String apiVersion = "1.0";
    private String bizContent;
    private String terminalType;
    private String terminalInfo;
    private String prodCode;
    private String notifyUrl;
    private String returnUrl;

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getBizContent() {
        return this.bizContent;
    }

    private boolean needEncrypt = false;
    private AlipayObject bizModel = null;

    @Override
    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    @Override
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String getReturnUrl() {
        return this.returnUrl;
    }

    @Override
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    @Override
    public String getApiVersion() {
        return this.apiVersion;
    }

    @Override
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    @Override
    public String getTerminalType() {
        return this.terminalType;
    }

    @Override
    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    @Override
    public String getTerminalInfo() {
        return this.terminalInfo;
    }

    @Override
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    @Override
    public String getProdCode() {
        return this.prodCode;
    }

    @Override
    public String getApiMethodName() {
        return "zoloz.authentication.customer.smilepay.initialize";
    }

    @Override
    public Map<String, String> getTextParams() {
        AlipayHashMap txtParams = new AlipayHashMap();
        txtParams.put("biz_content", this.bizContent);
        if (this.udfParams != null) {
            txtParams.putAll(this.udfParams);
        }
        return txtParams;
    }

    public void putOtherTextParam(String key, String value) {
        if (this.udfParams == null) {
            this.udfParams = new AlipayHashMap();
        }
        this.udfParams.put(key, value);
    }

    @Override
    public Class<ZolozAuthenticationCustomerSmilepayInitializeResponse> getResponseClass() {
        return ZolozAuthenticationCustomerSmilepayInitializeResponse.class;
    }

    @Override
    public boolean isNeedEncrypt() {
        return this.needEncrypt;
    }

    @Override
    public void setNeedEncrypt(boolean needEncrypt) {
        this.needEncrypt = needEncrypt;
    }

    @Override
    public AlipayObject getBizModel() {
        return this.bizModel;
    }

    @Override
    public void setBizModel(AlipayObject bizModel) {
        this.bizModel = bizModel;
    }
}
