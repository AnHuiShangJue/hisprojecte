package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisAnkleDetail extends BaseEntity {
    private Long id;

    private String number;

    private String name;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_skin_test", typeName = "isSkinTestName")
    private Integer isSkinTest;
    private String isSkinTestName;

    private String specification;

    private String unit;

    private String usages;

    private String dosage;

    private String remarks;

//    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "intervals", typeName = "intervalsName")
    private String intervals;
    private String intervalsName;

    private Long proofreadingNurseId;
    private String proofreadingNurseName;

    private Long approvedNurseId;
    private String approvedNurseName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_stop", typeName = "isStopName")
    private Integer isStop;
    private String isStopName;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date stopDate;

    private Long stopUserId;
    private String stopUserName;

    private Long stopPrfingNurseId;
    private String stopPrfingNurseName;

    private Long stopApdNurseId;
    private String stopApdNurseName;

    private Double totalAmount;

    private Integer orderNum;

//    private Integer isProofreading;
//    private Integer isApproved;
@CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_proofreading", typeName = "isProofreadingName")
private Integer isProofreading;//校对
    private String isProofreadingName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_approved", typeName = "isApprovedName")
    private Integer isApproved;//核准
    private String isApprovedName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_stop_proofreading", typeName = "isStopProofreadingName")
    private Integer isStopProofreading;
    private String isStopProofreadingName;

    private Integer isstopapproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }



    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getIsProofreading() {
        return isProofreading;
    }

    public void setIsProofreading(Integer isProofreading) {
        this.isProofreading = isProofreading;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public String getProofreadingNurseName() {
        return proofreadingNurseName;
    }

    public void setProofreadingNurseName(String proofreadingNurseName) {
        this.proofreadingNurseName = proofreadingNurseName;
    }

    public String getApprovedNurseName() {
        return approvedNurseName;
    }

    public void setApprovedNurseName(String approvedNurseName) {
        this.approvedNurseName = approvedNurseName;
    }

    public String getStopUserName() {
        return stopUserName;
    }

    public void setStopUserName(String stopUserName) {
        this.stopUserName = stopUserName;
    }

    public String getStopPrfingNurseName() {
        return stopPrfingNurseName;
    }

    public void setStopPrfingNurseName(String stopPrfingNurseName) {
        this.stopPrfingNurseName = stopPrfingNurseName;
    }

    public String getStopApdNurseName() {
        return stopApdNurseName;
    }

    public void setStopApdNurseName(String stopApdNurseName) {
        this.stopApdNurseName = stopApdNurseName;
    }

    public String getIsProofreadingName() {
        return isProofreadingName;
    }

    public void setIsProofreadingName(String isProofreadingName) {
        this.isProofreadingName = isProofreadingName;
    }

    public String getIsApprovedName() {
        return isApprovedName;
    }

    public void setIsApprovedName(String isApprovedName) {
        this.isApprovedName = isApprovedName;
    }

    public String getIntervalsName() {
        return intervalsName;
    }

    public void setIntervalsName(String intervalsName) {
        this.intervalsName = intervalsName;
    }

    public String getIsSkinTestName() {
        return isSkinTestName;
    }

    public void setIsSkinTestName(String isSkinTestName) {
        this.isSkinTestName = isSkinTestName;
    }

    public Integer getIsStopProofreading() {
        return isStopProofreading;
    }

    public void setIsStopProofreading(Integer isStopProofreading) {
        this.isStopProofreading = isStopProofreading;
    }

    public String getIsStopName() {
        return isStopName;
    }

    public void setIsStopName(String isStopName) {
        this.isStopName = isStopName;
    }

    public String getIsStopProofreadingName() {
        return isStopProofreadingName;
    }

    public void setIsStopProofreadingName(String isStopProofreadingName) {
        this.isStopProofreadingName = isStopProofreadingName;
    }

    public Integer getIsstopapproved() {
        return isstopapproved;
    }

    public void setIsstopapproved(Integer isstopapproved) {
        this.isstopapproved = isstopapproved;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }
}
