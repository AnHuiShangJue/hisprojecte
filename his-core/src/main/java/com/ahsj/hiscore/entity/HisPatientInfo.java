package com.ahsj.hiscore.entity;

import com.ahsj.hiscore.common.excel.ColumnCheckAnnotation;
import com.ahsj.hiscore.common.excel.DataType;
import com.ahsj.hiscore.common.utils.ExcelColumn;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

public class HisPatientInfo extends BaseEntity {
    @ColumnCheckAnnotation(index = 0, length = 64, isRepeat = false, nullable = false)
    private Long id;

    @ExcelColumn(value = "姓名", col = 1)
    @ColumnCheckAnnotation(index = 1, length = 64, isRepeat = false, nullable = false)
    private String name;

    private Integer birthday;

    @ExcelColumn(value = "地址", col = 9)
    @ColumnCheckAnnotation(index = 3, length = 64, isRepeat = false, nullable = false)
    private String location;

    @ExcelColumn(value = "年龄", col = 4)
    @ColumnCheckAnnotation(index = 4, length = 64, isRepeat = false, nullable = false)
    private Integer age;

    @ExcelColumn(value = "手机号", col = 5)
    @ColumnCheckAnnotation(index = 5, length = 64, isRepeat = false, nullable = false)
    private Long phonenumber;

    @ExcelColumn(value = "身份证号", col = 2)
    @ColumnCheckAnnotation(index = 2, length = 64, isRepeat = false, nullable = false)
    private String idcard;

    private String remarks;

    @ExcelColumn(value = "身高", col = 6)
    @ColumnCheckAnnotation(index = 6, length = 64, isRepeat = false, nullable = false)
    private Integer height;

    @ExcelColumn(value = "体重", col = 7)
    @ColumnCheckAnnotation(index = 7, length = 64, isRepeat = false, nullable = false)
    private Double weight;


    @ColumnCheckAnnotation(index = 8, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "is_married")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "is_married", typeName = "married")
    private Integer isMarried;
    @ExcelColumn(value = "婚烟状态", col = 8)
    String married;

    private String job;

    private String chiefcomplaint;

    private Long marriedId;

    private Long sexId;

//    private Long departmentName;

    public Long getMarriedId() {
        return marriedId;
    }

    public void setMarriedId(Long marriedId) {
        this.marriedId = marriedId;
    }

    public Long getSexId() {
        return sexId;
    }

    public void setSexId(Long sexId) {
        this.sexId = sexId;
    }

    @Override
    public String toString() {
        return "HisPatientInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", location='" + location + '\'' +
                ", age=" + age +
                ", phonenumber=" + phonenumber +
                ", idcard='" + idcard + '\'' +
                ", remarks='" + remarks + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", isMarried=" + isMarried +
                ", married='" + married + '\'' +
                ", job='" + job + '\'' +
                ", sex=" + sex +
                ", sexName='" + sexName + '\'' +
                '}';
    }


    @ColumnCheckAnnotation(index = 9, length = 1, isRepeat = true, nullable = false, dataType = DataType.Code, codeKey = "sex")
    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "sex", typeName = "sexName")
    private Integer sex;
    @ExcelColumn(value = "性别", col = 3)
    String sexName;


    private String departmentName;


    public HisPatientInfo() {
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

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

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Integer isMarried) {
        this.isMarried = isMarried;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }


    public HisPatientInfo toPatientEntity() {
        HisPatientInfo patientEntity = new HisPatientInfo();
        patientEntity.setId(this.id);
        patientEntity.setName(this.name);
        patientEntity.setBirthday(this.birthday);
        patientEntity.setLocation(this.location);
        patientEntity.setAge(this.age);
        patientEntity.setPhonenumber(this.phonenumber);
        patientEntity.setIdcard(this.idcard);
        patientEntity.setRemarks(this.remarks);
        patientEntity.setHeight(this.height);
        patientEntity.setWeight(this.weight);
        patientEntity.setIsMarried(this.isMarried);
        patientEntity.setJob(this.job);
        patientEntity.setSex(this.sex);
        return patientEntity;
    }

    public String getChiefcomplaint() {
        return chiefcomplaint;
    }

    public void setChiefcomplaint(String chiefcomplaint) {
        this.chiefcomplaint = chiefcomplaint;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


}
