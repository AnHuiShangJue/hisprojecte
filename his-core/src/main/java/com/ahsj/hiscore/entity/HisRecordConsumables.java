package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class HisRecordConsumables extends BaseEntity implements Serializable {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date updateDate;

    private String consumablesCode;

    private Integer comsumablesNum;

    private Integer refundNum;

    private Double price;

    private Double consumablesSumPrice;

    private Integer isDelete;

    private String name;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "assit_project_type", typeName = "assitTypeName")
    private Integer type;
    private String assitTypeName;

    private String spec;

    private Short isPayed;

    private Long hisHospitalManagerId;

    //就诊编号
    private String medicalRecordNumber;

    //住院编号
    private String hisHospitalManagerCode;

    private String recordConsumablesCode;

    //交易流水号
    private String tollRecordNumber;

    private Short isBack;

    private String voucher;

    private String patientName;

    private String remarks;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Double getConsumablesSumPrice() {
        return consumablesSumPrice;
    }

    public void setConsumablesSumPrice(Double consumablesSumPrice) {
        this.consumablesSumPrice = consumablesSumPrice;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getAssitTypeName() {
        return assitTypeName;
    }

    public void setAssitTypeName(String assitTypeName) {
        this.assitTypeName = assitTypeName;
    }

    public Short getIsBack() {
        return isBack;
    }

    public void setIsBack(Short isBack) {
        this.isBack = isBack;
    }

    public String getTollRecordNumber() {
        return tollRecordNumber;
    }

    public void setTollRecordNumber(String tollRecordNumber) {
        this.tollRecordNumber = tollRecordNumber;
    }

    public String getRecordConsumablesCode() {
        return recordConsumablesCode;
    }

    public void setRecordConsumablesCode(String recordConsumablesCode) {
        this.recordConsumablesCode = recordConsumablesCode;
    }

    public String getHisHospitalManagerCode() {
        return hisHospitalManagerCode;
    }

    public void setHisHospitalManagerCode(String hisHospitalManagerCode) {
        this.hisHospitalManagerCode = hisHospitalManagerCode;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumablesCode() {
        return consumablesCode;
    }

    public void setConsumablesCode(String consumablesCode) {
        this.consumablesCode = consumablesCode == null ? null : consumablesCode.trim();
    }

    public Integer getComsumablesNum() {
        return comsumablesNum;
    }

    public void setComsumablesNum(Integer comsumablesNum) {
        this.comsumablesNum = comsumablesNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public Short getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Short isPayed) {
        this.isPayed = isPayed;
    }

    public Long getHisHospitalManagerId() {
        return hisHospitalManagerId;
    }

    public void setHisHospitalManagerId(Long hisHospitalManagerId) {
        this.hisHospitalManagerId = hisHospitalManagerId;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber == null ? null : medicalRecordNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", consumablesCode=").append(consumablesCode);
        sb.append(", comsumablesNum=").append(comsumablesNum);
        sb.append(", price=").append(price);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", spec=").append(spec);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", hisHospitalManagerId=").append(hisHospitalManagerId);
        sb.append(", medicalRecordNumber=").append(medicalRecordNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}