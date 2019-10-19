package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.Site;
import core.code.CodeValueColumn;
import core.code.Constants;
import lombok.Data;

import java.math.BigDecimal;


public class SiteDTO extends Site {
    private Long id;

    private String siteName;

    private String title;

    private String location;

    private BigDecimal price;

    private BigDecimal upPrice;//上区间

    private BigDecimal lowPrice;//下区间

    private Double area;

    private String name;

    private String phoneNumber;

    private String description;

    private Long provinceId;

    private Long cityId;

    private Long areaId;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enableName")
    private Short isEnable;
    private String enableName;

    private String capacity;

    private String environmentalLabel;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "leaseName")
    private Integer isLease;
    private String leaseName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_verify", typeName = "verifyName")
    private Integer isVerify;
    private String verifyName;

    private Long enterpriseId;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

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

    public BigDecimal getUpPrice() {
        return upPrice;
    }

    public void setUpPrice(BigDecimal upPrice) {
        this.upPrice = upPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
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

    public String getEnableName() {
        return enableName;
    }

    public void setEnableName(String enableName) {
        this.enableName = enableName;
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

    public String getLeaseName() {
        return leaseName;
    }

    public void setLeaseName(String leaseName) {
        this.leaseName = leaseName;
    }

    @Override
    public Integer getIsVerify() {
        return isVerify;
    }

    @Override
    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
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