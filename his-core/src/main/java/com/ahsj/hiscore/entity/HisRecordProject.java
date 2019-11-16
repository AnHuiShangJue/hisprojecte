package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class HisRecordProject extends BaseEntity {
    private Long id;

    private Long recordId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd ")
    private Date createDate;

    private String name;

    private String tname;

    private String tunit;

    private Long recordsId;

    private Long loginDepartmentId;

    private String medicalRecordNumber;

    private String patientName;

    private Integer patientAge;

    private String doctorName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "patientSexName")
    private Integer patientSex;
    private String patientSexName;

    private String description;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "assit_project_type", typeName = "assitTypeName")
    private Short type;
    private String assitTypeName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "project_sources_type", typeName = "typesName")
    private int types;
    private String typesName;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String number;

    private String pinyinCode;

    private String unit;

    private Date updateDate;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdName")
    private Long departmentId;
    private String departmentIdName;

    private String checkDescription;

    private Long checkNumber;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_checked", typeName = "isCheckedName")
    private Short isChecked;
    private String isCheckedName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_pay", typeName = "isPayedName")
    private Short isPayed;
    private String isPayedName;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_print", typeName = "isPrintName")
    private Short isPrint;
    private String isPrintName;

    private Long projectId;

    private Integer num;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_back", typeName = "isBackName")
    private Integer isBack;
    private String isBackName;

    private String medicalRecordId;

    private String voucher;

    private Integer refundNum;

    //交易流水号
    private String tollRecordNumber;

    //住院编号
    private String hospitalNumber;

    //就诊编号
    private String recordNumber;

    private BigDecimal projectSumPrice;

    private String remarks;

    private String patientNames;

    private String vouchers;

    private String tranName ;

    private String medicalNumber ;

    private Integer hisProjectNum;

    public String getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(String medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTranName() {
        return tranName;
    }

    public void setTranName(String tranName) {
        this.tranName = tranName;
    }

    public String getVouchers() {
        return vouchers;
    }

    public void setVouchers(String vouchers) {
        this.vouchers = vouchers;
    }

    public String getPatientNames() {
        return patientNames;
    }

    public void setPatientNames(String patientNames) {
        this.patientNames = patientNames;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getProjectSumPrice() {
        return projectSumPrice;
    }

    public void setProjectSumPrice(BigDecimal projectSumPrice) {
        this.projectSumPrice = projectSumPrice;
    }

    public String getTollRecordNumber() {
        return tollRecordNumber;
    }

    public void setTollRecordNumber(String tollRecordNumber) {
        this.tollRecordNumber = tollRecordNumber;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getCheckDescription() {
        return checkDescription;
    }

    public void setCheckDescription(String checkDescription) {
        this.checkDescription = checkDescription == null ? null : checkDescription.trim();
    }

    public Long getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Long checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Short getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Short isChecked) {
        this.isChecked = isChecked;
    }

    public Short getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Short isPayed) {
        this.isPayed = isPayed;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAssitTypeName() {
        return assitTypeName;
    }

    public void setAssitTypeName(String assitTypeName) {
        this.assitTypeName = assitTypeName;
    }

    public Long getRecordsId() {
        return recordsId;
    }

    public void setRecordsId(Long recordsId) {
        this.recordsId = recordsId;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public Integer getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Integer patientSex) {
        this.patientSex = patientSex;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPatientSexName() {
        return patientSexName;
    }

    public void setPatientSexName(String patientSexName) {
        this.patientSexName = patientSexName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    public Short getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Short isPrint) {
        this.isPrint = isPrint;
    }



    public String getIsBackName() {
        return isBackName;
    }

    public void setIsBackName(String isBackName) {
        this.isBackName = isBackName;
    }

    public String getIsCheckedName() {
        return isCheckedName;
    }

    public void setIsCheckedName(String isCheckedName) {
        this.isCheckedName = isCheckedName;
    }

    public String getIsPayedName() {
        return isPayedName;
    }

    public void setIsPayedName(String isPayedName) {
        this.isPayedName = isPayedName;
    }

    public String getIsPrintName() {
        return isPrintName;
    }

    public void setIsPrintName(String isPrintName) {
        this.isPrintName = isPrintName;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTunit() {
        return tunit;
    }

    public void setTunit(String tunit) {
        this.tunit = tunit;
    }

    @Override
    public String toString() {
        return "HisRecordProject{" +
                "id=" + id +
                ", recordId=" + recordId +
                ", createDate=" + createDate +
                ", name='" + name + '\'' +
                ", tname='" + tname + '\'' +
                ", tunit='" + tunit + '\'' +
                ", recordsId=" + recordsId +
                ", medicalRecordNumber='" + medicalRecordNumber + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", doctorName='" + doctorName + '\'' +
                ", patientSex=" + patientSex +
                ", patientSexName='" + patientSexName + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", assitTypeName='" + assitTypeName + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", pinyinCode='" + pinyinCode + '\'' +
                ", unit='" + unit + '\'' +
                ", updateDate=" + updateDate +
                ", departmentId=" + departmentId +
                ", departmentIdName='" + departmentIdName + '\'' +
                ", checkDescription='" + checkDescription + '\'' +
                ", checkNumber=" + checkNumber +
                ", isChecked=" + isChecked +
                ", isCheckedName='" + isCheckedName + '\'' +
                ", isPayed=" + isPayed +
                ", isPayedName='" + isPayedName + '\'' +
                ", isPrint=" + isPrint +
                ", isPrintName='" + isPrintName + '\'' +
                ", projectId=" + projectId +
                ", num=" + num +
                ", isBack=" + isBack +
                ", isBackName='" + isBackName + '\'' +
                ", medicalRecordId='" + medicalRecordId + '\'' +
                ", voucher='" + voucher + '\'' +
                ", refundNum=" + refundNum +
                ", tollRecordNumber='" + tollRecordNumber + '\'' +
                ", projectSumPrice=" + projectSumPrice +
                ", remarks='" + remarks + '\'' +
                ", patientNames='" + patientNames + '\'' +
                ", vouchers='" + vouchers + '\'' +
                ", tranName='" + tranName + '\'' +
                '}';
    }


    public Long getLoginDepartmentId() {
        return loginDepartmentId;
    }

    public void setLoginDepartmentId(Long loginDepartmentId) {
        this.loginDepartmentId = loginDepartmentId;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public String getTypesName() {
        return typesName;
    }

    public void setTypesName(String typesName) {
        this.typesName = typesName;
    }

    public Integer getHisProjectNum() {
        return hisProjectNum;
    }

    public void setHisProjectNum(Integer hisProjectNum) {
        this.hisProjectNum = hisProjectNum;
    }
}