package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisDeath extends BaseEntity {
    private Long id;

    private Long hisHospitalManageId;

    private String pathology;

    private String examine;

    private String admittingDiagnosis;

    private String postmortemDiagnsis;

    private String situation;

    private String details;

    private String autopsy;

    private String signatory;

    private Date signatoryDate;

    private Long createUserId;

    private Date createDate;

    private Date updateDate;

    private Long updateUserId;

    private Long majorId;

    private Long doctorId;

    private Long archiaterId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date deathTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHisHospitalManageId() {
        return hisHospitalManageId;
    }

    public void setHisHospitalManageId(Long hisHospitalManageId) {
        this.hisHospitalManageId = hisHospitalManageId;
    }

    public String getPathology() {
        return pathology;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology == null ? null : pathology.trim();
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine == null ? null : examine.trim();
    }

    public String getAdmittingDiagnosis() {
        return admittingDiagnosis;
    }

    public void setAdmittingDiagnosis(String admittingDiagnosis) {
        this.admittingDiagnosis = admittingDiagnosis == null ? null : admittingDiagnosis.trim();
    }

    public String getPostmortemDiagnsis() {
        return postmortemDiagnsis;
    }

    public void setPostmortemDiagnsis(String postmortemDiagnsis) {
        this.postmortemDiagnsis = postmortemDiagnsis == null ? null : postmortemDiagnsis.trim();
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation == null ? null : situation.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public String getAutopsy() {
        return autopsy;
    }

    public void setAutopsy(String autopsy) {
        this.autopsy = autopsy == null ? null : autopsy.trim();
    }

    public String getSignatory() {
        return signatory;
    }

    public void setSignatory(String signatory) {
        this.signatory = signatory == null ? null : signatory.trim();
    }

    public Date getSignatoryDate() {
        return signatoryDate;
    }

    public void setSignatoryDate(Date signatoryDate) {
        this.signatoryDate = signatoryDate;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getArchiaterId() {
        return archiaterId;
    }

    public void setArchiaterId(Long archiaterId) {
        this.archiaterId = archiaterId;
    }

    public Date getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(Date deathTime) {
        this.deathTime = deathTime;
    }
}