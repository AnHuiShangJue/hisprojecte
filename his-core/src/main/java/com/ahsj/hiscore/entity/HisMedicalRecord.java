package com.ahsj.hiscore.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.api.client.util.DateTime;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class HisMedicalRecord extends BaseEntity {
    private Long id;
    private Long ids;
    private String medicalRecordId;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "regist", typeName = "registName")

    private Integer regist;
    private String registName;

    private String serials;

    private String numbers;
    private String hosNumber;


    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;
    private Date lowCreateDate;
    private Date highCreateDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date highTime;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date lowTime;

    private Integer patientAge;

    private Integer isToday;

    private Integer type;

    public Integer getIsToday() {
        return isToday;
    }

    public void setIsToday(Integer isToday) {
        this.isToday = isToday;
    }

    private String idCard;
    private String patientName;

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_re",typeName = "isReName")
    private Integer isRe;
    private String isReName;


    private Long patientId;

    private Long nurseId;

    private Long doctorId;

    private Long registeredId;

    private String chiefComplaint;

    private String currentCondition;

    private String pastCondition;

    //以下字段是连表查询时所定义的字段，库中并非实际存在
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer sex;
    private String sexName;


    private String nurseName;
    private String nurseNumber;
    private String doctorName;
    private String doctorNumber;
    private String pName;
    private String nowCondition;
    private String hisCondition;
    private String chiefComplaintForMedical;
    private String receiveDoctorName;
    private Double sumPrice;//计算项目总价，用于门诊收费模块，请忽视此字段

    private Long bid;

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdForTollName")
    private Long departmentIdForToll;
    private String departmentIdForTollName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_discharged", typeName = "isDischargeName")
    private Integer isDischarge;
    private String isDischargeName;

    private Long fnurseId;


    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Long getDepartmentIdForToll() {
        return departmentIdForToll;
    }

    public void setDepartmentIdForToll(Long departmentIdForToll) {
        this.departmentIdForToll = departmentIdForToll;
    }

    public String getDepartmentIdForTollName() {
        return departmentIdForTollName;
    }

    public void setDepartmentIdForTollName(String departmentIdForTollName) {
        this.departmentIdForTollName = departmentIdForTollName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNowCondition() {
        return nowCondition;
    }

    public void setNowCondition(String nowCondition) {
        this.nowCondition = nowCondition;
    }

    public String getHisCondition() {
        return hisCondition;
    }

    public void setHisCondition(String hisCondition) {
        this.hisCondition = hisCondition;
    }

    public String getChiefComplaintForMedical() {
        return chiefComplaintForMedical;
    }

    public void setChiefComplaintForMedical(String chiefComplaintForMedical) {
        this.chiefComplaintForMedical = chiefComplaintForMedical;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "is_rel",typeName = "isRelName")
    private Integer isRel;
    private String isRelName;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "department_id", typeName = "departmentIdName")
    private Long departmentId;
    private String departmentIdName;

    private Long hospitalManageId;

    public Long getHospitalManageId() {
        return hospitalManageId;
    }

    public void setHospitalManageId(Long hospitalManageId) {
        this.hospitalManageId = hospitalManageId;
    }

    public Date getLowCreateDate() {
        return lowCreateDate;
    }

    public void setLowCreateDate(Date lowCreateDate) {
        this.lowCreateDate = lowCreateDate;
    }

    public Date getHighCreateDate() {
        return highCreateDate;
    }

    public void setHighCreateDate(Date highCreateDate) {
        this.highCreateDate = highCreateDate;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getRegisteredId() {
        return registeredId;
    }

    public void setRegisteredId(Long registeredId) {
        this.registeredId = registeredId;
    }

    private HisPatientInfo hisPatientInfo;

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getPastCondition() {
        return pastCondition;
    }

    public void setPastCondition(String pastCondition) {
        this.pastCondition = pastCondition;
    }

    public HisPatientInfo getHisPatientInfo() {
        return hisPatientInfo;
    }

    public void setHisPatientInfo(HisPatientInfo hisPatientInfo) {
        this.hisPatientInfo = hisPatientInfo;
    }

    public String getRegistName() {
        return registName;
    }

    public void setRegistName(String registName) {
        this.registName = registName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getRegist() {
        return regist;
    }

    public void setRegist(Integer regist) {
        this.regist = regist;
    }


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getSerials() {
        return serials;
    }

    public void setSerials(String serials) {
        this.serials = serials;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    public Integer getIsRe() {
        return isRe;
    }

    public void setIsRe(Integer isRe) {
        this.isRe = isRe;
    }

    public String getIsReName() {
        return isReName;
    }

    public void setIsReName(String isReName) {
        this.isReName = isReName;
    }

    public Integer getIsRel() {
        return isRel;
    }

    public void setIsRel(Integer isRel) {
        this.isRel = isRel;
    }

    public String getIsRelName() {
        return isRelName;
    }

    public void setIsRelName(String isRelName) {
        this.isRelName = isRelName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getHighTime() {
        return highTime;
    }

    public void setHighTime(Date highTime) {
        this.highTime = highTime;
    }

    public Date getLowTime() {
        return lowTime;
    }

    public void setLowTime(Date lowTime) {
        this.lowTime = lowTime;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNurseNumber() {
        return nurseNumber;
    }

    public void setNurseNumber(String nurseNumber) {
        this.nurseNumber = nurseNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHosNumber() {
        return hosNumber;
    }

    public void setHosNumber(String hosNumber) {
        this.hosNumber = hosNumber;
    }


    public Long getFnurseId() {
        return fnurseId;
    }

    public void setFnurseId(Long fnurseId) {
        this.fnurseId = fnurseId;
    }

    public String getReceiveDoctorName() {
        return receiveDoctorName;
    }

    public void setReceiveDoctorName(String receiveDoctorName) {
        this.receiveDoctorName = receiveDoctorName;
    }

    public Integer getIsDischarge() {
        return isDischarge;
    }

    public void setIsDischarge(Integer isDischarge) {
        this.isDischarge = isDischarge;
    }

    public String getIsDischargeName() {
        return isDischargeName;
    }

    public void setIsDischargeName(String isDischargeName) {
        this.isDischargeName = isDischargeName;
    }
}