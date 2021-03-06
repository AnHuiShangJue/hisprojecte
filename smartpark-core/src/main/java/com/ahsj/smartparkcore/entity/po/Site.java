package com.ahsj.smartparkcore.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Site extends BaseEntity {

    private Long id;

    private String siteName;

    private String title;

    private String location;

    private BigDecimal price;

    private Double area;

    private String name;

    private String phoneNumber;

    private String description;

    private String capacity;

    private String environmentalLabel;

    private Long enterpriseId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_verify", typeName = "verifyName")
    private Integer isVerify;
    private String verifyName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_lease", typeName = "leaseName")
    private Integer isLease;
    private String leaseName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enableName")
    private Short isEnable;
    private String enableName;

    private Integer bookType;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    private Date updateDate;

    private String filePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getEnvironmentalLabel() {
        return environmentalLabel;
    }

    public void setEnvironmentalLabel(String environmentalLabel) {
        this.environmentalLabel = environmentalLabel;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public String getLeaseName() {
        return leaseName;
    }

    public void setLeaseName(String leaseName) {
        this.leaseName = leaseName;
    }

    public Short getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Short isEnable) {
        this.isEnable = isEnable;
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

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", siteName='" + siteName + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", capacity='" + capacity + '\'' +
                ", environmentalLabel='" + environmentalLabel + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", isVerify=" + isVerify +
                ", verifyName='" + verifyName + '\'' +
                ", isLease=" + isLease +
                ", leaseName='" + leaseName + '\'' +
                ", isEnable=" + isEnable +
                ", enableName='" + enableName + '\'' +
                ", bookType=" + bookType +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}