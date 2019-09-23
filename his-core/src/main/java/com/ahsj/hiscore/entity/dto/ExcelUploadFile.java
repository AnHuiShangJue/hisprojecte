package com.ahsj.hiscore.entity.dto;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ExcelUploadFile
 * <p>
 * Date:     2019/8/31 15:39
 *
 * @author XJP
 * @create 2019/8/31
 * @since 1.0.0
 */

public class ExcelUploadFile {

    private Integer faildNum; // 未通过数量

    private Integer successNum; // 验证通过数量

    private Integer importListSize; // 导入数量

    private List<String> errorList; // 错误日志

    private Integer errorListSize; // 错误日志个数

    private String vouchers; // 错误日志个数

    public String getVouchers() {
        return vouchers;
    }

    public void setVouchers(String vouchers) {
        this.vouchers = vouchers;
    }

    public ExcelUploadFile() {
    }

    public Integer getFaildNum() {
        return faildNum;
    }

    public void setFaildNum(Integer faildNum) {
        this.faildNum = faildNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getImportListSize() {
        return importListSize;
    }

    public void setImportListSize(Integer importListSize) {
        this.importListSize = importListSize;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public Integer getErrorListSize() {
        return errorListSize;
    }

    public void setErrorListSize(Integer errorListSize) {
        this.errorListSize = errorListSize;
    }
}
