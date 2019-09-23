package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisApplicationForDrugReturnDetails extends BaseEntity {
    private Long id;

    private String drugsNumb;

    private String drugsName;

    private String tdrugsName;//翻译字段

    private String drugsSpec;

    private String tdrugsSpec;//翻译字段

    private String recordNumber;

    private String patientName;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date validityPeriod;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "placeOrigin", typeName = "placeoriginName")
    private String placeorigin;
    private String placeoriginName;

    private Integer returnCount;

    private String remarks;

    private String voucher;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Date updateDate;

    private Integer measures;

    private Long medicationDetailId;

    private String number;

    private BigDecimal totalPrice;

    private BigDecimal price;

    private Long relatedId;

    private Long mId;//药品基本表id

    private Long mediEnterId;//药品入库表ID

    private BigDecimal recoverTheFee;//实际找零金额

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_payed", typeName = "isPayeds")
    private Integer isPayed;
    private String isPayeds;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_review", typeName = "isReviewName")
    private Integer isReview;
    private String isReviewName;

    public BigDecimal getRecoverTheFee() {
        return recoverTheFee;
    }

    public void setRecoverTheFee(BigDecimal recoverTheFee) {
        this.recoverTheFee = recoverTheFee;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMeasures() {
        return measures;
    }

    public void setMeasures(Integer measures) {
        this.measures = measures;
    }

    public Long getMedicationDetailId() {
        return medicationDetailId;
    }

    public void setMedicationDetailId(Long medicationDetailId) {
        this.medicationDetailId = medicationDetailId;
    }

    public String getPlaceoriginName() {
        return placeoriginName;
    }

    public void setPlaceoriginName(String placeoriginName) {
        this.placeoriginName = placeoriginName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb == null ? null : drugsNumb.trim();
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName == null ? null : drugsName.trim();
    }

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec == null ? null : drugsSpec.trim();
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getPlaceorigin() {
        return placeorigin;
    }

    public void setPlaceorigin(String placeorigin) {
        this.placeorigin = placeorigin == null ? null : placeorigin.trim();
    }

    public Integer getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Integer returnCount) {
        this.returnCount = returnCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher == null ? null : voucher.trim();
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

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getTdrugsName() {
        return tdrugsName;
    }

    public void setTdrugsName(String tdrugsName) {
        this.tdrugsName = tdrugsName;
    }

    public String getTdrugsSpec() {
        return tdrugsSpec;
    }

    public void setTdrugsSpec(String tdrugsSpec) {
        this.tdrugsSpec = tdrugsSpec;
    }

    public Long getMediEnterId() {
        return mediEnterId;
    }

    public void setMediEnterId(Long mediEnterId) {
        this.mediEnterId = mediEnterId;
    }

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public String getIsPayeds() {
        return isPayeds;
    }

    public void setIsPayeds(String isPayeds) {
        this.isPayeds = isPayeds;
    }

    public Integer getIsReview() {
        return isReview;
    }

    public void setIsReview(Integer isReview) {
        this.isReview = isReview;
    }

    public String getIsReviewName() {
        return isReviewName;
    }

    public void setIsReviewName(String isReviewName) {
        this.isReviewName = isReviewName;
    }
}