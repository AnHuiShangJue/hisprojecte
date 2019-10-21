package com.ahsj.smartparkcore.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ConferenceRoomInfo  extends BaseEntity {
    private Long id;

    private String location;

    private BigDecimal price;

    private Double area;

    private String environmentalLabel;

    private String capacity;

    private String description;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "isEnableName")
    private Integer isEnable;
    private String isEnableName;

    private String name;

    private String phoneNumber;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "isLeaseName")
    private Integer isLease;
    private String isLeaseName;

    private Long enterpriseId;

    private Long userId;

    private String conferenceName;

    private Integer bookType;

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

    public String getEnvironmentalLabel() {
        return environmentalLabel;
    }

    public void setEnvironmentalLabel(String environmentalLabel) {
        this.environmentalLabel = environmentalLabel == null ? null : environmentalLabel.trim();
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity == null ? null : capacity.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName == null ? null : conferenceName.trim();
    }

    public String getIsEnableName() {
        return isEnableName;
    }

    public void setIsEnableName(String isEnableName) {
        this.isEnableName = isEnableName;
    }

    public String getIsLeaseName() {
        return isLeaseName;
    }

    public void setIsLeaseName(String isLeaseName) {
        this.isLeaseName = isLeaseName;
    }
}