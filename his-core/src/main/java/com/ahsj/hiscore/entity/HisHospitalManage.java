package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisHospitalManage extends BaseEntity {
    private Long id;

    private Long hosptalRegistId;

    private Long patientId;

    private Long doctorId;

    private int count;

    private String diseaseRegion;

    private Double price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date highTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date lowTime;

    private Long nurseId;

    private Long bedId;

    private Long wardId;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;

    private String remarks;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "care_level", typeName = "careLevels")
    private Integer careLevel;
    private String careLevels;


    private Long ids;

    private String name;

    private String patientName;
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexName")
    private Integer sex;
    private String sexName;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexsName")
    private Integer sexs;
    private String sexsName;

    private Integer age;

    private String idcard;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_discharged", typeName = "isDischargedName")
    private Integer isDischarged;
    private String isDischargedName;

    private String nurseName;

    private String number;

    private String wardNumber;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdName")
    private Long departmentId;
    private String departmentIdName;

    private String numbers;
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_re", typeName = "isReName")
    private Integer isRe;
    private String isReName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_hos", typeName = "isHosName")
    private Integer isHos;
    private String isHosName;

    private String medicalNumber;

    private String medicalNumbers;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date joinDate;

    private String inpatientDiagnosis;

    private String outpatientDiagnosis;

    private String names;

    private String hospitalizationDay;

    private String payHospitalizationDay;

    private String tollDetailsId;

    private String idcards;

    private Integer ages;

    private String doctorName;

    private String doctorNumber;

    private Long phoneNumber;

    private String nurseIdcard;

    private Integer wardsNumber;

    private Integer bedsNumber;

    private String nurseNumber;

    private String nurseNames;

    private BigDecimal restDeposit;
    private String departmentName;

    private BigDecimal depositWarning;

    private BigDecimal retrunPrice;//退药总价


    private String location;

    private Integer height;

    private Double Weight;

    private Integer isMarried;

    private Integer medicalOrderType;//医嘱明细类型 1.普通医嘱 2.用药医嘱 3.项目医嘱   对应medical_order_detail_type

    private Long targetId;//用药医嘱或项目医嘱 对应的药库表ID 或向项目表ID

    private BigDecimal totalAmount;//医嘱用药或项目总量

    private String usages;//医嘱用法

    private Long medicalOrderDetailId;//对应的医嘱明细表ID

    public String getNurseNames() {
        return nurseNames;
    }

    public void setNurseNames(String nurseNames) {
        this.nurseNames = nurseNames;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHosptalRegistId() {
        return hosptalRegistId;
    }

    public void setHosptalRegistId(Long hosptalRegistId) {
        this.hosptalRegistId = hosptalRegistId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Long getBedId() {
        return bedId;
    }

    public void setBedId(Long bedId) {
        this.bedId = bedId;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }


    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
        this.wardNumber = wardNumber;
    }

    public Integer getIsDischarged() {
        return isDischarged;
    }

    public void setIsDischarged(Integer isDischarged) {
        this.isDischarged = isDischarged;
    }

    public String getInpatientDiagnosis() {
        return inpatientDiagnosis;
    }

    public void setInpatientDiagnosis(String inpatientDiagnosis) {
        this.inpatientDiagnosis = inpatientDiagnosis;
    }

    public String getOutpatientDiagnosis() {
        return outpatientDiagnosis;
    }

    public void setOutpatientDiagnosis(String outpatientDiagnosis) {
        this.outpatientDiagnosis = outpatientDiagnosis;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getIdcards() {
        return idcards;
    }

    public void setIdcards(String idcards) {
        this.idcards = idcards;
    }

    public Integer getAges() {
        return ages;
    }

    public void setAges(Integer ages) {
        this.ages = ages;
    }

    public Integer getSexs() {
        return sexs;
    }

    public void setSexs(Integer sexs) {
        this.sexs = sexs;
    }

    public String getSexsName() {
        return sexsName;
    }

    public void setSexsName(String sexsName) {
        this.sexsName = sexsName;
    }

    public String getMedicalNumbers() {
        return medicalNumbers;
    }

    public void setMedicalNumbers(String medicalNumbers) {
        this.medicalNumbers = medicalNumbers;
    }

    public Integer getIsHos() {
        return isHos;
    }

    public void setIsHos(Integer isHos) {
        this.isHos = isHos;
    }

    public String getIsHosName() {
        return isHosName;
    }

    public void setIsHosName(String isHosName) {
        this.isHosName = isHosName;
    }

    public String getIsDischargedName() {
        return isDischargedName;
    }

    public void setIsDischargedName(String isDischargedName) {
        this.isDischargedName = isDischargedName;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public Integer getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(Integer careLevel) {
        this.careLevel = careLevel;
    }

    public String getCareLevels() {
        return careLevels;
    }

    public void setCareLevels(String careLevels) {
        this.careLevels = careLevels;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNurseIdcard() {
        return nurseIdcard;
    }

    public void setNurseIdcard(String nurseIdcard) {
        this.nurseIdcard = nurseIdcard;
    }

    public Integer getWardsNumber() {
        return wardsNumber;
    }

    public void setWardsNumber(Integer wardsNumber) {
        this.wardsNumber = wardsNumber;
    }

    public Integer getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(Integer bedsNumber) {
        this.bedsNumber = bedsNumber;
    }

    public String getHospitalizationDay() {
        return hospitalizationDay;
    }

    public void setHospitalizationDay(String hospitalizationDay) {
        this.hospitalizationDay = hospitalizationDay;
    }

    public String getPayHospitalizationDay() {
        return payHospitalizationDay;
    }

    public void setPayHospitalizationDay(String payHospitalizationDay) {
        this.payHospitalizationDay = payHospitalizationDay;
    }

    public String getTollDetailsId() {
        return tollDetailsId;
    }

    public void setTollDetailsId(String tollDetailsId) {
        this.tollDetailsId = tollDetailsId;
    }


    public BigDecimal getRestDeposit() {
        return restDeposit;
    }

    public void setRestDeposit(BigDecimal restDeposit) {
        this.restDeposit = restDeposit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BigDecimal getDepositWarning() {
        return depositWarning;
    }

    public void setDepositWarning(BigDecimal depositWarning) {
        this.depositWarning = depositWarning;
    }

    public String getDiseaseRegion() {
        return diseaseRegion;
    }

    public void setDiseaseRegion(String diseaseRegion) {
        this.diseaseRegion = diseaseRegion;
    }

    public BigDecimal getRetrunPrice() {
        return retrunPrice;
    }

    public void setRetrunPrice(BigDecimal retrunPrice) {
        this.retrunPrice = retrunPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
    }

    public Integer getMedicalOrderType() {
        return medicalOrderType;
    }

    public void setMedicalOrderType(Integer medicalOrderType) {
        this.medicalOrderType = medicalOrderType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public Long getMedicalOrderDetailId() {
        return medicalOrderDetailId;
    }

    public void setMedicalOrderDetailId(Long medicalOrderDetailId) {
        this.medicalOrderDetailId = medicalOrderDetailId;
    }
}