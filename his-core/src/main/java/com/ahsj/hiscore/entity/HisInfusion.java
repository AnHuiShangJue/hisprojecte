package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisInfusion extends BaseEntity {
    private Long id;

    private String recordId;

    private String hosptalregistNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    private Long patientId;

    private String usages;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "intervals", typeName = "intervalsName")
    private Integer intervals;
    private String intervalsName;

    private String speed;

    private String duration;

    private String drugsNumb;

    private String drugname;

    private Integer singleDose;

    private String singleUnit;

    private Integer isSkinTest;

    private String dosage;

    private String unit;

    private BigDecimal price;

    private String medicalRecordNumber;



    private String remarks;

    private String number;

    private Integer type;

    private Long projectId;

    private Long nurseId;

    private String medicalRecordId;

    private BigDecimal salePrice;

    private String drugsSpec;

    private String startTimeVarchar;





    //别名
    private String nurseName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    private Long medicationId;


    private String smallUnit;
    private Integer countNumber;
    private Long medicineId;//药品基本表ID


    private String patientName;
    //翻译字段
    private String tdrugsName;
    private String tunit;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getHosptalregistNumber() {
        return hosptalregistNumber;
    }

    public void setHosptalregistNumber(String hosptalregistNumber) {
        this.hosptalregistNumber = hosptalregistNumber == null ? null : hosptalregistNumber.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages == null ? null : usages.trim();
    }

    public Integer getIntervals() {
        return intervals;
    }

    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb == null ? null : drugsNumb.trim();
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname == null ? null : drugname.trim();
    }



    public String getSingleUnit() {
        return singleUnit;
    }

    public void setSingleUnit(String singleUnit) {
        this.singleUnit = singleUnit == null ? null : singleUnit.trim();
    }

    public Integer getIsSkinTest() {
        return isSkinTest;
    }

    public void setIsSkinTest(Integer isSkinTest) {
        this.isSkinTest = isSkinTest;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSmallUnit() {
        return smallUnit;
    }

    public void setSmallUnit(String smallUnit) {
        this.smallUnit = smallUnit;
    }

    public Integer getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTdrugsName() {
        return tdrugsName;
    }

    public void setTdrugsName(String tdrugsName) {
        this.tdrugsName = tdrugsName;
    }

    public String getTunit() {
        return tunit;
    }

    public void setTunit(String tunit) {
        this.tunit = tunit;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getSingleDose() {
        return singleDose;
    }

    public void setSingleDose(Integer singleDose) {
        this.singleDose = singleDose;
    }


    public String getIntervalsName() {
        return intervalsName;
    }

    public void setIntervalsName(String intervalsName) {
        this.intervalsName = intervalsName;
    }

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getStartTimeVarchar() {
        return startTimeVarchar;
    }

    public void setStartTimeVarchar(String startTimeVarchar) {
        this.startTimeVarchar = startTimeVarchar;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                ", recordId='" + recordId + '\'' +
//                ", hosptalregistNumber='" + hosptalregistNumber + '\'' +
//                ", startTime=" + startTime +
//                ", patientId=" + patientId +
//                ", usages='" + usages + '\'' +
//                ", intervals=" + intervals +
//                ", intervalsName='" + intervalsName + '\'' +
//                ", speed='" + speed + '\'' +
//                ", duration='" + duration + '\'' +
//                ", drugsNumb='" + drugsNumb + '\'' +
//                ", drugname='" + drugname + '\'' +
//                ", singleDose=" + singleDose +
//                ", singleUnit='" + singleUnit + '\'' +
//                ", isSkinTest=" + isSkinTest +
//                ", dosage='" + dosage + '\'' +
//                ", unit='" + unit + '\'' +
//                ", price=" + price +
//                ", medicalRecordNumber='" + medicalRecordNumber + '\'' +
//                ", remarks='" + remarks + '\'' +
//                ", number='" + number + '\'' +
//                ", type=" + type +
//                ", projectId=" + projectId +
//                ", nurseId=" + nurseId +
//                ", medicalRecordId='" + medicalRecordId + '\'' +
//                ", salePrice=" + salePrice +
//                ", drugsSpec='" + drugsSpec + '\'' +
//                ", nurseName='" + nurseName + '\'' +
//                ", createDate=" + createDate +
//                ", updateDate=" + updateDate +
//                ", smallUnit='" + smallUnit + '\'' +
//                ", countNumber=" + countNumber +
//                ", patientName='" + patientName + '\'' +
//                ", tdrugsName='" + tdrugsName + '\'' +
//                ", tunit='" + tunit + '\'' +
//                '}';
//    }
}