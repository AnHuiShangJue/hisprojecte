package com.ahsj.smartparkcore.core;

/**
 * 自定义请求状态码
 * @author ChengLian
 * @date 2018/10/15
 */
public enum ResultStatus {
    SUCCESS(1, "成功"),
    SUCCESS_SELECT(1001, "数据查询成功"),
    SUCCESS_INSERT(1002, "数据插入成功"),
    SUCCESS_UPDATE(1003, "数据更新成功"),
    SUCCESS_DELETE(1004, "数据删除成功"),
    SUCCESS_SET(1005,"设置成功"),
    SUCCESS_REVIEW(1006,"审核通过"),
    ERROR(-1, "失败"),
    ERROR_PARAM(-400, "参数错误"),
    UNAUTHORIZED(-401, "无权限"),
    ERROR_TOKEN(-402, "TOKEN校验错误"),
    REGISTER_USER_CHECKED(-501, "用户已存在"),
    REGISTER_VERIFYCODE_ERROR(-502, "验证码不正确"),
    ERROR_SELECT(-1001, "查询失败"),
    ERROR_INSERT(-1002, "插入失败"),
    ERROR_UPDATE(-1003, "更新失败"),
    ERROR_DELETE(-1004, "删除失败"),
    ERROR_SET(-1004, "设置失败"),
    DATA_NULL(-1005, "查询数据为空"),
    ERROR_REVIEW(-1006, "审核不通过"),
    ERROR_INFO_NOTCOMPELETE(-1006, "信息填写不完整");


    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
