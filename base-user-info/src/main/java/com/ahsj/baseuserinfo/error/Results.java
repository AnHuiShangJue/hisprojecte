package com.ahsj.baseuserinfo.error;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: Results
 * <p>
 * Date:     2019/8/3 18:07
 *
 * @author XJP
 * @create 2019/8/3
 * @since 1.0.0
 */

public class Results<T> {
    /**
     * 不支持的参数值
     */
    public static final int ERROR_CODE_UN_SUPPORT_ARGUMENTS_VALUE = -3;
    public static final int ERROR_CODE_UN_SUPPORT_ACTION = -4;
    public static Results SYSTEM_ERR = errorOf(-1, "系统错误");

    int resCode;
    String resMessage;
    T data;

    public static <O> Results<O> sucessOf(O object) {
        Results<O> result = new Results<O>();
        result.resCode = 0;
        result.resMessage = "成功";
        result.data = object;
        return result;
    }

    public static Results errorOf(int errorCode, String message) {
        Results result = new Results();
        result.resCode = errorCode;
        result.resMessage = message;
        return result;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
