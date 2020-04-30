package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class HisTollDetails extends BaseEntity {
    private Long id;

    private Long tollRecordId;

    private String name;
    private Long phonenumber;

    private String idcard;

    private String records;

    private Long targetId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "type", typeName = "recordTypes")
    private Integer type;
    private String recordTypes;

    private BigDecimal money;

    private BigDecimal moneys;

    private BigDecimal price;


    private BigDecimal recoverTheFee;

    private BigDecimal toll;

    private String description;
    private String medicalRecordNumber;

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String MedicalReocordNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private String doctorName;

    private String patientName;

    private Integer age;

    private Integer count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexName")
    private Integer sex;
    private String sexName;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdName")
    private Long departmentId;
    private String departmentIdName;

    private BigDecimal nursingFee;//护理

    private BigDecimal examinationFee;//检查


    private BigDecimal observeFee;//药品费

    private BigDecimal drugFee;//药品费


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_back", typeName = "isBackName")
    private Integer isBack;
    private String isBackName;

    private Double allDeposit;



    public BigDecimal getDrugFee() {
        return drugFee;
    }

    public void setDrugFee(BigDecimal drugFee) {
        this.drugFee = drugFee;
    }

    public BigDecimal getNursingFee() {
        return nursingFee;
    }

    public BigDecimal getMoneys() {
        return moneys;
    }

    public void setMoneys(BigDecimal moneys) {
        this.moneys = moneys;
    }

    public void setNursingFee(BigDecimal nursingFee) {
        this.nursingFee = nursingFee;
    }

    public BigDecimal getExaminationFee() {
        return examinationFee;
    }

    public void setExaminationFee(BigDecimal examinationFee) {
        this.examinationFee = examinationFee;
    }

    public BigDecimal getObserveFee() {
        return observeFee;
    }

    public void setObserveFee(BigDecimal observeFee) {
        this.observeFee = observeFee;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public void setDepartmentIdName(String departmentIdName) {

        this.departmentIdName = departmentIdName;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private String createName;
    private BigDecimal totalPrices;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "payName")
    private Integer isPay;
    private String payName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_settlement", typeName = "isSettlements")
    private Integer isSettlement;
    private String isSettlements;

    private String tranName;//翻译名称

    private String commonNumber;//公共编号 可表示挂号编号，就诊编号，住院编号 服务于通用刷卡模块

    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }

    public Integer getIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(Integer isSettlement) {
        this.isSettlement = isSettlement;
    }

    public String getIsSettlements() {
        return isSettlements;
    }

    public void setIsSettlements(String isSettlements) {
        this.isSettlements = isSettlements;
    }

    private String MedicalRecordId;

    private Integer num;

    private String registerNumber;//挂号编号

    private String tdrugsSpec;

    private String drugsSpec;

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec;
    }

    public String getTdrugsSpec() {
        return tdrugsSpec;
    }

    public void setTdrugsSpec(String tdrugsSpec) {
        this.tdrugsSpec = tdrugsSpec;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMedicalRecordId() {
        return MedicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        MedicalRecordId = medicalRecordId;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTollRecordId() {
        return tollRecordId;
    }

    public void setTollRecordId(Long tollRecordId) {
        this.tollRecordId = tollRecordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRecoverTheFee() {
        return recoverTheFee;
    }

    public void setRecoverTheFee(BigDecimal recoverTheFee) {
        this.recoverTheFee = recoverTheFee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedicalReocordNumber() {
        return MedicalReocordNumber;
    }

    public void setMedicalReocordNumber(String medicalReocordNumber) {
        MedicalReocordNumber = medicalReocordNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(String recordTypes) {
        this.recordTypes = recordTypes;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public BigDecimal getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getCommonNumber() {
        return commonNumber;
    }

    public void setCommonNumber(String commonNumber) {
        this.commonNumber = commonNumber;
    }


    public String getIsBackName() {
        return isBackName;
    }

    public void setIsBackName(String isBackName) {
        this.isBackName = isBackName;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getToll() {
        return toll;
    }

    public void setToll(BigDecimal toll) {
        this.toll = toll;
    }

    public Double getAllDeposit() {
        return allDeposit;
    }

    public void setAllDeposit(Double allDeposit) {
        this.allDeposit = allDeposit;
    }
}
