package com.ahsj.baseuserinfo.entity.dto;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: OrganizationModel
 * <p>
 * Date:     2019/7/29 14:22
 *
 * @author XJP
 * @create 2019/7/29
 * @since 1.0.0
 */

public class OrganizationModel implements Serializable {
    private static final long serialVersionUID = 5008806124149772670L;

    private Long id;

/*    private String treeId;

    private String parentId;

    private String type;

    private String departKbn;*/

    private String name;

    /*    private String creditCode;*/

    private String responsible;

    private String address;
/*

    private String telPhone;

    private Integer orderNo;

    private String isInitData;

    private String zipCode;

    private String remark;

    private String companyId;

    private String delFlag;

    private String createUserId;

    private Date createDate;

    private String updateUserId;

    private Date updateDate;

    private String perms;
*/

  /*  public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId == null ? null : treeId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDepartKbn() {
        return departKbn;
    }

    public void setDepartKbn(String departKbn) {
        this.departKbn = departKbn == null ? null : departKbn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible == null ? null : responsible.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone == null ? null : telPhone.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getIsInitData() {
        return isInitData;
    }

    public void setIsInitData(String isInitData) {
        this.isInitData = isInitData == null ? null : isInitData.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "id=" + id +
                ", treeId='" + treeId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", type='" + type + '\'' +
                ", departKbn='" + departKbn + '\'' +
                ", name='" + name + '\'' +
                ", creditCode='" + creditCode + '\'' +
                ", responsible='" + responsible + '\'' +
                ", address='" + address + '\'' +
                ", telPhone='" + telPhone + '\'' +
                ", orderNo=" + orderNo +
                ", isInitData='" + isInitData + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", remark='" + remark + '\'' +
                ", companyId='" + companyId + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createDate=" + createDate +
                ", updateUserId='" + updateUserId + '\'' +
                ", updateDate=" + updateDate +
                ", perms='" + perms + '\'' +
                '}';
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", responsible='" + responsible + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
