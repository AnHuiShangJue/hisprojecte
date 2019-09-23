package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisMedicalOrderDetail extends BaseEntity {
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

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "intervals", typeName = "intervalsName")
    private Integer intervals;
    private String intervalsName;

    private Long proofreadingNurseId;

    private Long approvedNurseId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stopDate;

    private Long stopUserId;

    private Long stopPrfingNurseId;

    private Long stopApdNurseId;

    private BigDecimal totalAmount;


    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;


    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    private Integer orderNum;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_stop", typeName = "isStopName")
    private Integer isStop;
    private String isStopName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_proofreading", typeName = "isProofreadingName")
    private Integer isProofreading;
    private String isProofreadingName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_approved", typeName = "isApprovedName")
    private Integer isApproved;
    private String isApprovedName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "medical_order_detail_type", typeName = "medicalOrderTypeName")
    private Integer medicalOrderType;
    private String medicalOrderTypeName;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private Long targetId;
    //1代表是第一次编辑 可以进行编辑  其他代表不可以 无需设置字典
    private Integer isFirstEdit;

    private String stopUserName;//停嘱人姓名

    private Integer isStopProofreading;

    private Integer isstopapproved;



    //以下字段是查询出来为显示要求所声明的，在数据库本表中并非实际存在
    private String pnNumber;//校对护士编号
    private String pnName;//校对护士姓名
    private String anNumber;//核准护士编号
    private String anName;//核准护士姓名
    private String userName;//停嘱人姓名
    private Integer userType;//停嘱人身份类型
    private String spnNumber;//停嘱校对护士编号
    private String spnName;//停嘱校对护士姓名
    private String sanNumber;//核准校对护士编号
    private String sanName;//核准校对护士姓名


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

    public String getIsStopName() {
        return isStopName;
    }

    public void setIsStopName(String isStopName) {
        this.isStopName = isStopName;
    }

    public String getIsSkinTestName() {
        return isSkinTestName;
    }

    public void setIsSkinTestName(String isSkinTestName) {
        this.isSkinTestName = isSkinTestName;
    }


    public String getPnNumber() {
        return pnNumber;
    }

    public void setPnNumber(String pnNumber) {
        this.pnNumber = pnNumber;
    }

    public String getPnName() {
        return pnName;
    }

    public void setPnName(String pnName) {
        this.pnName = pnName;
    }

    public String getAnNumber() {
        return anNumber;
    }

    public void setAnNumber(String anNumber) {
        this.anNumber = anNumber;
    }

    public String getAnName() {
        return anName;
    }

    public void setAnName(String anName) {
        this.anName = anName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSpnNumber() {
        return spnNumber;
    }

    public void setSpnNumber(String spnNumber) {
        this.spnNumber = spnNumber;
    }

    public String getSpnName() {
        return spnName;
    }

    public void setSpnName(String spnName) {
        this.spnName = spnName;
    }

    public String getSanNumber() {
        return sanNumber;
    }

    public void setSanNumber(String sanNumber) {
        this.sanNumber = sanNumber;
    }

    public String getSanName() {
        return sanName;
    }

    public void setSanName(String sanName) {
        this.sanName = sanName;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public Integer getIntervals() {
        return intervals;
    }

    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }

    public String getIntervalsName() {
        return intervalsName;
    }

    public void setIntervalsName(String intervalsName) {
        this.intervalsName = intervalsName;
    }

    public Integer getMedicalOrderType() {
        return medicalOrderType;
    }

    public void setMedicalOrderType(Integer medicalOrderType) {
        this.medicalOrderType = medicalOrderType;
    }

    public String getMedicalOrderTypeName() {
        return medicalOrderTypeName;
    }

    public void setMedicalOrderTypeName(String medicalOrderTypeName) {
        this.medicalOrderTypeName = medicalOrderTypeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getIsFirstEdit() {
        return isFirstEdit;
    }

    public void setIsFirstEdit(Integer isFirstEdit) {
        this.isFirstEdit = isFirstEdit;
    }

    public String getStopUserName() {
        return stopUserName;
    }

    public void setStopUserName(String stopUserName) {
        this.stopUserName = stopUserName;
    }

    public Integer getIsStopProofreading() {
        return isStopProofreading;
    }

    public void setIsStopProofreading(Integer isStopProofreading) {
        this.isStopProofreading = isStopProofreading;
    }

    public Integer getIsstopapproved() {
        return isstopapproved;
    }

    public void setIsstopapproved(Integer isstopapproved) {
        this.isstopapproved = isstopapproved;
    }
}