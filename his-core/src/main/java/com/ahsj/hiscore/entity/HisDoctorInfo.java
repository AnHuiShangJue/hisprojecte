package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisDoctorInfo extends BaseEntity{

    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = true, nullable = false)
    @ExcelColumn( value = "序号", col = 1)
    private Long id;

    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = true, nullable = false)
    @ExcelColumn( value = "医生名称", col = 2)
    private String name;

    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "sex")
    @CodeValueColumn(type = Constants.TYPE_CODE,typeKey = "sex",typeName = "sexName")
    private Integer sex;

    @ExcelColumn( value = "性别", col = 3)
    private String sexName;

    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = true, nullable = false)
    private String numb;

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = true, nullable = false)
    @ExcelColumn( value = "身份证号", col = 4)
    private String idcard;

    private String relam;

    private String remarks;

    private Long sysCodeId;

    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = true, nullable = false)
    @ExcelColumn( value = "部门", col = 5)
    private String deptName;

    private Date endDate;

    private Date startDate;

    private String introduction;

    @CodeValueColumn(type = Constants.GLOBAL_DATA_ORANGIATION, typeKey = "", typeName = "departmentIdName")
    private Long department_id;

    private String departmentIdName;

    private String imageUrl;

    private Long imageId;

    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = true, nullable = false )
    @ExcelColumn( value = "入职时间", col = 6)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date joinDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date joinLowDate;

    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = true, nullable = false)
    @ExcelColumn( value = "离职时间", col = 7)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date leaveDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date leaveLowDate;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date graduationDate;

    private String graduationMajor;

    private String graduatedSchool;

    private Short isLeave;




    private String number;

    private String phone;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb == null ? null : numb.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getRelam() {
        return relam;
    }

    public void setRelam(String relam) {
        this.relam = relam == null ? null : relam.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationMajor() {
        return graduationMajor;
    }

    public void setGraduationMajor(String graduationMajor) {
        this.graduationMajor = graduationMajor == null ? null : graduationMajor.trim();
    }

    public String getGraduatedSchool() {
        return graduatedSchool;
    }

    public void setGraduatedSchool(String graduatedSchool) {
        this.graduatedSchool = graduatedSchool == null ? null : graduatedSchool.trim();
    }

    public Short getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(Short isLeave) {
        this.isLeave = isLeave;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDepartmentIdName() {
        return departmentIdName;
    }

    public void setDepartmentIdName(String departmentIdName) {
        this.departmentIdName = departmentIdName;
    }

    public Date getJoinLowDate() {
        return joinLowDate;
    }

    public void setJoinLowDate(Date joinLowDate) {
        this.joinLowDate = joinLowDate;
    }

    public Date getLeaveLowDate() {
        return leaveLowDate;
    }

    public void setLeaveLowDate(Date leaveLowDate) {
        this.leaveLowDate = leaveLowDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getSysCodeId() {
        return sysCodeId;
    }

    public void setSysCodeId(Long sysCodeId) {
        this.sysCodeId = sysCodeId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}