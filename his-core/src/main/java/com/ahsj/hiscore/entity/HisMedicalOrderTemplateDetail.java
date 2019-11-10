package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisMedicalOrderTemplateDetail extends BaseEntity {
    private Long id;

    private String templateNumber;

    private String templateName;

    private Integer orderNum;

    private String name;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_skin_test", typeName = "isSkinTestName")
    private Integer isSkinTest;
    private String isSkinTestName;

    private String specification;

    private String unit;

    private String usages;

    private String dosage;

    private Double totalAmount;

    private String remarks;

//    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "intervals", typeName = "intervalsName")
    private String intervals;
    private String intervalsName;

    private Long proofreadingNurseId;

    private Long approvedNurseId;

    private Integer isStop;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date stopDate;

    private Long stopUserId;

    private Long stopPrfingNurseId;

    private Long stopApdNurseId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber == null ? null : templateNumber.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsSkinTest() {
        return isSkinTest;
    }

    public void setIsSkinTest(Integer isSkinTest) {
        this.isSkinTest = isSkinTest;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages == null ? null : usages.trim();
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

//    public Integer getIntervals() {
//        return intervals;
//    }
//
//    public void setIntervals(Integer intervals) {
//        this.intervals = intervals;
//    }

    public Long getProofreadingNurseId() {
        return proofreadingNurseId;
    }

    public void setProofreadingNurseId(Long proofreadingNurseId) {
        this.proofreadingNurseId = proofreadingNurseId;
    }

    public Long getApprovedNurseId() {
        return approvedNurseId;
    }

    public void setApprovedNurseId(Long approvedNurseId) {
        this.approvedNurseId = approvedNurseId;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Long getStopUserId() {
        return stopUserId;
    }

    public void setStopUserId(Long stopUserId) {
        this.stopUserId = stopUserId;
    }

    public Long getStopPrfingNurseId() {
        return stopPrfingNurseId;
    }

    public void setStopPrfingNurseId(Long stopPrfingNurseId) {
        this.stopPrfingNurseId = stopPrfingNurseId;
    }

    public Long getStopApdNurseId() {
        return stopApdNurseId;
    }

    public void setStopApdNurseId(Long stopApdNurseId) {
        this.stopApdNurseId = stopApdNurseId;
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

    public String getIsSkinTestName() {
        return isSkinTestName;
    }

    public void setIsSkinTestName(String isSkinTestName) {
        this.isSkinTestName = isSkinTestName;
    }

    public String getIntervalsName() {
        return intervalsName;
    }

    public void setIntervalsName(String intervalsName) {
        this.intervalsName = intervalsName;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }
}