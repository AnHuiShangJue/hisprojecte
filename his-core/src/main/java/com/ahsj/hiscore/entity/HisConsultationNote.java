package com.ahsj.hiscore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class HisConsultationNote extends BaseEntity {
    private Long id;

    private Date updateDate;

    private Date createDate;

    private Long hisHospitalManageId;

    private Long applicantDepartmentId;

    private Long inviteeDepartmentId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date applicantDate;

    private Long updateUserId;

    private Long createUserId;


    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "meeting_format", typeName = "meetingFormats")
    private Integer meetingFormat;
    private String meetingFormats;

    private String medicalHistory;

    private String consultationPurposes;

    private String admittingDiagnosis;

    private Long applicantDoctorId;

    private String consultationOpinion;

    private Long inviteeDoctorId;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date consultationDate;

    public String getMeetingFormats() {
        return meetingFormats;
    }

    public void setMeetingFormats(String meetingFormats) {
        this.meetingFormats = meetingFormats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getHisHospitalManageId() {
        return hisHospitalManageId;
    }

    public void setHisHospitalManageId(Long hisHospitalManageId) {
        this.hisHospitalManageId = hisHospitalManageId;
    }

    public Long getApplicantDepartmentId() {
        return applicantDepartmentId;
    }

    public void setApplicantDepartmentId(Long applicantDepartmentId) {
        this.applicantDepartmentId = applicantDepartmentId;
    }

    public Long getInviteeDepartmentId() {
        return inviteeDepartmentId;
    }

    public void setInviteeDepartmentId(Long inviteeDepartmentId) {
        this.inviteeDepartmentId = inviteeDepartmentId;
    }

    public Date getApplicantDate() {
        return applicantDate;
    }

    public void setApplicantDate(Date applicantDate) {
        this.applicantDate = applicantDate;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getMeetingFormat() {
        return meetingFormat;
    }

    public void setMeetingFormat(Integer meetingFormat) {
        this.meetingFormat = meetingFormat;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory == null ? null : medicalHistory.trim();
    }

    public String getConsultationPurposes() {
        return consultationPurposes;
    }

    public void setConsultationPurposes(String consultationPurposes) {
        this.consultationPurposes = consultationPurposes == null ? null : consultationPurposes.trim();
    }

    public String getAdmittingDiagnosis() {
        return admittingDiagnosis;
    }

    public void setAdmittingDiagnosis(String admittingDiagnosis) {
        this.admittingDiagnosis = admittingDiagnosis == null ? null : admittingDiagnosis.trim();
    }

    public Long getApplicantDoctorId() {
        return applicantDoctorId;
    }

    public void setApplicantDoctorId(Long applicantDoctorId) {
        this.applicantDoctorId = applicantDoctorId;
    }

    public String getConsultationOpinion() {
        return consultationOpinion;
    }

    public void setConsultationOpinion(String consultationOpinion) {
        this.consultationOpinion = consultationOpinion == null ? null : consultationOpinion.trim();
    }

    public Long getInviteeDoctorId() {
        return inviteeDoctorId;
    }

    public void setInviteeDoctorId(Long inviteeDoctorId) {
        this.inviteeDoctorId = inviteeDoctorId;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }
}