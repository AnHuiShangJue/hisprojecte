package com.ahsj.smartparkcore.entity.po;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class StationInfo extends BaseEntity {

    private Long id;

    private String title;

    private String location;

    private BigDecimal price;

    private Double area;

    private String name;

    private String phoneNumber;

    private String description;

    private String environmentalLabel;

    private Date createDate;

    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_verify", typeName = "verifyName")
    private Integer isVerify;
    private String verifyName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "leaseName")
    private Integer isLease;
    private String leaseName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enableName")
    private Short isEnable;
    private String enableName;

    private Long enterpriseId;

    private Integer number;

    private Date startTime;

    private Date endTime;

    private String remark;

    private Integer bookType;

    private String capacity;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }

    public String getLeaseName() {
        return leaseName;
    }

    public void setLeaseName(String leaseName) {
        this.leaseName = leaseName;
    }

    public String getEnableName() {
        return enableName;
    }

    public void setEnableName(String enableName) {
        this.enableName = enableName;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Short isEnable) {
        this.isEnable = isEnable;
    }

    public String getEnvironmentalLabel() {
        return environmentalLabel;
    }

    public void setEnvironmentalLabel(String environmentalLabel) {
        this.environmentalLabel = environmentalLabel == null ? null : environmentalLabel.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}