package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisImportInfo extends BaseEntity {
    private Long id;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private String uploadErrorInfo;

    private String uploadFileName;

    private String voucher;

    private Long updateUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUploadErrorInfo() {
        return uploadErrorInfo;
    }

    public void setUploadErrorInfo(String uploadErrorInfo) {
        this.uploadErrorInfo = uploadErrorInfo == null ? null : uploadErrorInfo.trim();
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName == null ? null : uploadFileName.trim();
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher == null ? null : voucher.trim();
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}