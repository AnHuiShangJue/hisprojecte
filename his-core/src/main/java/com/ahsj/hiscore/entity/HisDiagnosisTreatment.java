package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisDiagnosisTreatment  extends BaseEntity {
    private Long id;

    private Date createDate;

    private Long createUserId;

    private Date updateDate;

    private String diagnosisTreatmentTechnology;

    private Date recordDate;

    private String invasiveDiagnosisTreatmentName;

    private String interventionalTherapy;

    private String physicianName;

    private String results;

    private String note;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "yes_or_no", typeName = "isName")//is_enable  disable
    private Integer peopleIsSign;
    private String isName;

    private String hospitalManageNumber;

    private String alreadySignId ;

    private Long updateUserId;

    public String getAlreadySignId() {
        return alreadySignId;
    }

    public void setAlreadySignId(String alreadySignId) {
        this.alreadySignId = alreadySignId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDiagnosisTreatmentTechnology() {
        return diagnosisTreatmentTechnology;
    }

    public void setDiagnosisTreatmentTechnology(String diagnosisTreatmentTechnology) {
        this.diagnosisTreatmentTechnology = diagnosisTreatmentTechnology == null ? null : diagnosisTreatmentTechnology.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getInvasiveDiagnosisTreatmentName() {
        return invasiveDiagnosisTreatmentName;
    }

    public void setInvasiveDiagnosisTreatmentName(String invasiveDiagnosisTreatmentName) {
        this.invasiveDiagnosisTreatmentName = invasiveDiagnosisTreatmentName == null ? null : invasiveDiagnosisTreatmentName.trim();
    }

    public String getInterventionalTherapy() {
        return interventionalTherapy;
    }

    public void setInterventionalTherapy(String interventionalTherapy) {
        this.interventionalTherapy = interventionalTherapy == null ? null : interventionalTherapy.trim();
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName == null ? null : physicianName.trim();
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results == null ? null : results.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getPeopleIsSign() {
        return peopleIsSign;
    }

    public void setPeopleIsSign(Integer peopleIsSign) {
        this.peopleIsSign = peopleIsSign;
    }

    public String getHospitalManageNumber() {
        return hospitalManageNumber;
    }

    public void setHospitalManageNumber(String hospitalManageNumber) {
        this.hospitalManageNumber = hospitalManageNumber == null ? null : hospitalManageNumber.trim();
    }


    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getIsName() {
        return isName;
    }

    public void setIsName(String isName) {
        this.isName = isName;
    }
}