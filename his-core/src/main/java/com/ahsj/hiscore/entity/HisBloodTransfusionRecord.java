package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisBloodTransfusionRecord extends BaseEntity {
    private Long id;

    private Long recordId;

    private Long doctorId;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_agree",typeName = "isAgreeName")
    private Integer isAgree;
    private String isAgreeName;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_approve",typeName = "isApproveName")
    private Integer isApprove;
    private String isApproveName;

    private Long approveId;

    private String purpose;

    private String indicationAssessment;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "blood_variety",typeName = "bloodVarietyName")
    private Integer bloodVariety;
    private String bloodVarietyName;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "blood_type",typeName = "bloodTypeName")
    private Integer bloodType;
    private String bloodTypeName;

    private Double num;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_bad",typeName = "isBadName")
    private Integer isBad;
    private String isBadName;

    private String adverseReactions;

    private String evaluation;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_sign",typeName = "isSignName")
    private Integer isSign;
    private String isSignName;

    private Date createDate;

    private Date updateDate;

    private Long hospitalManageId;

    private String mainDoctorName;//主治医师姓名

    private String approveName;//批准负责人姓名

    private Integer isOperate;//是否可操作  24小时内可操作

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Integer isAgree) {
        this.isAgree = isAgree;
    }

    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    public Long getApproveId() {
        return approveId;
    }

    public void setApproveId(Long approveId) {
        this.approveId = approveId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getIndicationAssessment() {
        return indicationAssessment;
    }

    public void setIndicationAssessment(String indicationAssessment) {
        this.indicationAssessment = indicationAssessment == null ? null : indicationAssessment.trim();
    }

    public Integer getBloodVariety() {
        return bloodVariety;
    }

    public void setBloodVariety(Integer bloodVariety) {
        this.bloodVariety = bloodVariety;
    }

    public Integer getBloodType() {
        return bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Integer getIsBad() {
        return isBad;
    }

    public void setIsBad(Integer isBad) {
        this.isBad = isBad;
    }

    public String getAdverseReactions() {
        return adverseReactions;
    }

    public void setAdverseReactions(String adverseReactions) {
        this.adverseReactions = adverseReactions == null ? null : adverseReactions.trim();
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
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

    public Long getHospitalManageId() {
        return hospitalManageId;
    }

    public void setHospitalManageId(Long hospitalManageId) {
        this.hospitalManageId = hospitalManageId;
    }

    public String getIsAgreeName() {
        return isAgreeName;
    }

    public void setIsAgreeName(String isAgreeName) {
        this.isAgreeName = isAgreeName;
    }

    public String getIsApproveName() {
        return isApproveName;
    }

    public void setIsApproveName(String isApproveName) {
        this.isApproveName = isApproveName;
    }

    public String getBloodVarietyName() {
        return bloodVarietyName;
    }

    public void setBloodVarietyName(String bloodVarietyName) {
        this.bloodVarietyName = bloodVarietyName;
    }

    public String getBloodTypeName() {
        return bloodTypeName;
    }

    public void setBloodTypeName(String bloodTypeName) {
        this.bloodTypeName = bloodTypeName;
    }

    public String getIsBadName() {
        return isBadName;
    }

    public void setIsBadName(String isBadName) {
        this.isBadName = isBadName;
    }

    public String getIsSignName() {
        return isSignName;
    }

    public void setIsSignName(String isSignName) {
        this.isSignName = isSignName;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public String getMainDoctorName() {
        return mainDoctorName;
    }

    public void setMainDoctorName(String mainDoctorName) {
        this.mainDoctorName = mainDoctorName;
    }

    public Integer getIsOperate() {
        return isOperate;
    }

    public void setIsOperate(Integer isOperate) {
        this.isOperate = isOperate;
    }
}