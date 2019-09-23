package com.ahsj.smartparkcore.common.exception;

public enum SysErrorEnums implements IErrorCode {


    EMPTY_PARAME("A11001","排班设置时间不能超过8小时"),
    HIS_TYPOGRAPHY_STARTDATE_ENDDATE("A11003","请求值班时间范围异常"),
    HIS_NURSE_IS_NOT_NULL("A11004","查询护士列表失败"),
    HIS_TYPOGRAPHY_USER_INFO("A11002","在该时间内没有安排值班人员");

    
    private String errorCode;
    private String errorMessage;
    
    private SysErrorEnums(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}