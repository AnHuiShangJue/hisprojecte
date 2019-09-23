package com.ahsj.userinfor.entity;

import core.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class SysCodeDetail extends BaseEntity implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = -1878353485090649044L;

    private Integer id;

    private String code;

    private String value;

    private String remark;

    private Integer typeId;

    private Integer orderNum;

    private String type;

    private String sysCodeType;

    public String getSysCodeType() {
        return sysCodeType;
    }

    public void setSysCodeType(String sysCodeType) {
        this.sysCodeType = sysCodeType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}