package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.Site;

import java.math.BigDecimal;

public class SiteVo extends Site {
    private Long id;

    private String siteName;

    private String title;

    private String location;

    private BigDecimal price;

    private Double area;

    private String name;

    private String phoneNumber;

    private String description;

    private Short isEnable;

    private String capacity;

    private String environmentalLabel;

    private Integer isLease;

    private Integer isVerify;

    private Long enterpriseId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getSiteName() {
        return siteName;
    }

    @Override
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Double getArea() {
        return area;
    }

    @Override
    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Short getIsEnable() {
        return isEnable;
    }

    @Override
    public void setIsEnable(Short isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getEnvironmentalLabel() {
        return environmentalLabel;
    }

    @Override
    public void setEnvironmentalLabel(String environmentalLabel) {
        this.environmentalLabel = environmentalLabel;
    }

    @Override
    public Integer getIsLease() {
        return isLease;
    }

    @Override
    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    @Override
    public Integer getIsVerify() {
        return isVerify;
    }

    @Override
    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    @Override
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    @Override
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}