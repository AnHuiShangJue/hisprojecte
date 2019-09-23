package com.ahsj.hiscore.entity;


import com.ahsj.hiscore.common.utils.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class HisTypographyUserInfo extends BaseEntity implements Serializable {



    @ExcelColumn(value = "序号", col = 1)
    private Integer id;
    /**
     * 值班人员的名称
     */
    @ExcelColumn(value = "姓名", col = 2)
    private String typographyUserName;
    /**
     * 科室名
     */
    @ExcelColumn(value = "科室", col = 3)
    private String departmentName;
    /**
     * 性别
     */
    @ExcelColumn(value = "性别", col = 4)
    private String sexName;
    /**
     * 年龄
     */
    //@ExcelColumn(value = "年龄", col = 5)
    private Integer age;
    /**
     * 电话
     */
    @ExcelColumn(value = "电话", col = 5)
    private String phone;
    /**
     * 用户登录名
     */
    @ExcelColumn(value = "用户登录账户", col = 6)
    private String userLogin;
    /**
     * 值班起始时间
     */
    @ExcelColumn(value = "值班起始时间", col = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    /**
     * 值班结束时间
     */
    @ExcelColumn(value = "值班结束时间", col = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    /**
     * 创建时间
     */
   // @ExcelColumn(value = "创建时间", col = 10)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 修改时间
     */
    //@ExcelColumn(value = "修改时间", col = 11)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    //@ExcelColumn(value = "修改人ID", col = 12)
    private String updateUserId;

   // @ExcelColumn(value = "创建人ID", col = 13)
    private String createUserId;




    @Override
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Override
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 备注
     */
    private String remark;

    /**
     * 值班的年月
     */
    private String typographyYearMonth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTypographyUserName() {
        return typographyUserName;
    }

    public void setTypographyUserName(String typographyUserName) {
        this.typographyUserName = typographyUserName == null ? null : typographyUserName.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName == null ? null : sexName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTypographyYearMonth() {
        return typographyYearMonth;
    }

    public void setTypographyYearMonth(String typographyYearMonth) {
        this.typographyYearMonth = typographyYearMonth == null ? null : typographyYearMonth.trim();
    }

    @Override
    public String toString() {
        return "HisTypographyUserInfo{" +
                "id=" + id +
                ", typographyUserName='" + typographyUserName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", sexName='" + sexName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", number=" + userLogin +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", updateUserId='" + updateUserId + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", remark='" + remark + '\'' +
                ", typographyYearMonth='" + typographyYearMonth + '\'' +
                '}';
    }
}