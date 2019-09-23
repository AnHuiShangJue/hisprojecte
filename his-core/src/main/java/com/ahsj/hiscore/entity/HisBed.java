package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisBed extends BaseEntity {
    private Long id;

    private Integer number;

    private Long wardId;

    private Double price;

    private Date createDate;

    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "hospital_bed", typeName = "hospitalbed")
    private Integer type;

    private String hospitalbed;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_enable", typeName = "enable")
    private Integer isEnable;
    private String enable;

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

    public String getHospitalbed() {
        return hospitalbed;
    }

    public void setHospitalbed(String hospitalbed) {
        this.hospitalbed = hospitalbed;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

}