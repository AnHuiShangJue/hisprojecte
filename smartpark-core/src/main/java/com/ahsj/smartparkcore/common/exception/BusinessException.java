package com.ahsj.smartparkcore.common.exception;

import java.util.Map;

public class BusinessException extends RuntimeException {
    
   private static final long serialVersionUID = 1L;
    
    private IErrorCode iErrorCode;
    
    private String errorCode;
    private String errorMessage;
    private Map<String, Object> errorData;
        
        public BusinessException(IErrorCode iErrorCode) {
        super();
        this.iErrorCode = iErrorCode;
        this.errorCode = iErrorCode.getErrorCode();
        this.errorMessage = iErrorCode.getErrorMessage();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public IErrorCode getiErrorCode() {
        return iErrorCode;
    }

    public void setiErrorCode(IErrorCode iErrorCode) {
        this.iErrorCode = iErrorCode;
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

    public Map<String, Object> getErrorData() {
        return errorData;
    }

    public void setErrorData(Map<String, Object> errorData) {
        this.errorData = errorData;
    }
}