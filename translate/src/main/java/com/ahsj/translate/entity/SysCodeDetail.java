package com.ahsj.translate.entity;

import core.entity.BaseEntity;

public class SysCodeDetail extends BaseEntity {
    /** serialVersionUID */
    private static final long serialVersionUID = -1878353485090649044L;

    private Integer id;

    private String code;

    private String value;

    private String remark;

    private Integer typeId;

    private Integer orderNum;

    private String type;

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

    @Override
    public String toString() {
        return "SysCodeDetail{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                ", typeId=" + typeId +
                ", orderNum=" + orderNum +
                ", type='" + type + '\'' +
                '}';
    }
}