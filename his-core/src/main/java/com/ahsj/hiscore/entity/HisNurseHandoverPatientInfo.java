package com.ahsj.hiscore.entity;

import core.entity.BaseEntity;

import java.util.Date;

public class HisNurseHandoverPatientInfo extends BaseEntity {
    private Long id;

    private Long handoverId;

    private Integer type;

    private Long patientId;

    private String name;

    private Integer sex;

    private Long bed;

    private String hospitalmanage;

    private String diagnosis;

    private String content;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHandoverId() {
        return handoverId;
    }

    public void setHandoverId(Long handoverId) {
        this.handoverId = handoverId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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

    public Long getBed() {
        return bed;
    }

    public void setBed(Long bed) {
        this.bed = bed;
    }

    public String getHospitalmanage() {
        return hospitalmanage;
    }

    public void setHospitalmanage(String hospitalmanage) {
        this.hospitalmanage = hospitalmanage == null ? null : hospitalmanage.trim();
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


}