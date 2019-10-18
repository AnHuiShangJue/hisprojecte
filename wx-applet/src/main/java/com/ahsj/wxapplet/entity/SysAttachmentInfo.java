package com.ahsj.wxapplet.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class SysAttachmentInfo extends BaseEntity {
    private Long id;

    private Long relateId;

    private String relatePage;

    private String relateKey;

    private String fileName;

    private String fileOrgName;

    private String location;

    private String fileSize;

    private String fileType;

    private Long uploadUser;

    private Date uploadDate;

    private String fileTypeKey;

    private String fileTypeValue;

    private Integer orderNo;

    private String uploadType;

    private String companyId;

    private String delFlag;

    private String createUserId;

    private Date createDate;

    private String updateUserId;

    private Date updateDate;

    private Integer versionNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public String getRelatePage() {
        return relatePage;
    }

    public void setRelatePage(String relatePage) {
        this.relatePage = relatePage == null ? null : relatePage.trim();
    }

    public String getRelateKey() {
        return relateKey;
    }

    public void setRelateKey(String relateKey) {
        this.relateKey = relateKey == null ? null : relateKey.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileOrgName() {
        return fileOrgName;
    }

    public void setFileOrgName(String fileOrgName) {
        this.fileOrgName = fileOrgName == null ? null : fileOrgName.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public Long getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(Long uploadUser) {
        this.uploadUser = uploadUser;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFileTypeKey() {
        return fileTypeKey;
    }

    public void setFileTypeKey(String fileTypeKey) {
        this.fileTypeKey = fileTypeKey == null ? null : fileTypeKey.trim();
    }

    public String getFileTypeValue() {
        return fileTypeValue;
    }

    public void setFileTypeValue(String fileTypeValue) {
        this.fileTypeValue = fileTypeValue == null ? null : fileTypeValue.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType == null ? null : uploadType.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public String toString() {
        return "SysAttachmentInfo{" +
                "id=" + id +
                ", relateId=" + relateId +
                ", relatePage='" + relatePage + '\'' +
                ", relateKey='" + relateKey + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileOrgName='" + fileOrgName + '\'' +
                ", location='" + location + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadUser=" + uploadUser +
                ", uploadDate=" + uploadDate +
                ", fileTypeKey='" + fileTypeKey + '\'' +
                ", fileTypeValue='" + fileTypeValue + '\'' +
                ", orderNo=" + orderNo +
                ", uploadType='" + uploadType + '\'' +
                ", companyId='" + companyId + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createDate=" + createDate +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateDate=" + updateDate +
                ", versionNo=" + versionNo +
                '}';
    }
}