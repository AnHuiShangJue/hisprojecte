package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.ConferenceRoomInfo;
import core.code.CodeValueColumn;
import core.code.Constants;
import lombok.Data;

import java.math.BigDecimal;
public class ConferenceRoomInfoVO extends ConferenceRoomInfo {

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

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "isLeaseName")
    private Integer isLease;
    private String isLeaseName;

    private Long enterpriseId;

    private String conferenceName;

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
        this.location = location;
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
        this.environmentalLabel = environmentalLabel;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    @Override
    public String getIsEnableName() {
        return isEnableName;
    }

    @Override
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
