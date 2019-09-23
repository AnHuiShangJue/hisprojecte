package com.ahsj.hiscore.entity;

import core.code.CodeValueColumn;
import core.code.Constants;
import core.entity.BaseEntity;

import java.util.Date;

public class HisMedicalTemplate extends BaseEntity {
    private Long id;

    private Date createDate;

    private Date updateDate;

    private String nowCondition;

    private String hiscondition;

    private String chiefcomplaint;

    private String personalHistory;

    private String allergies;

    private String familyHistory;

    private String other;

    private String prescriptionId;

    private String treeId;

    private String parentId;

    private String name;

    private String descrption;

    private Integer isLastCode;

    private Integer combineId;

    @CodeValueColumn(type = Constants.TYPE_CODE, typeKey = "permission_type", typeName = "permissionTypeName")
    private Integer permissionType;
    private String permissionTypeName;

    private Long targetId;

    private Long loginUserId;//登录人ID
    private Long departmentId;//登录人部门ID
    private String canUserName;//可使用者
    private String prescriptionName;//药方名称
    private String prescriptionDescrption;//药方描述
    private String combineName;//组合项目名称
    private String combineDescrption;//组合项目描述
    private String prescriptionIsusable;//药方是否可用
    private String combineIsusable;//组合项目是否可用

    public String getPrescriptionIsusable() {
        return prescriptionIsusable;
    }

    public void setPrescriptionIsusable(String prescriptionIsusable) {
        this.prescriptionIsusable = prescriptionIsusable;
    }

    public String getCombineIsusable() {
        return combineIsusable;
    }

    public void setCombineIsusable(String combineIsusable) {
        this.combineIsusable = combineIsusable;
    }

    public String getCanUserName() {
        return canUserName;
    }

    public void setCanUserName(String canUserName) {
        this.canUserName = canUserName;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getPrescriptionDescrption() {
        return prescriptionDescrption;
    }

    public void setPrescriptionDescrption(String prescriptionDescrption) {
        this.prescriptionDescrption = prescriptionDescrption;
    }

    public String getCombineName() {
        return combineName;
    }

    public void setCombineName(String combineName) {
        this.combineName = combineName;
    }

    public String getCombineDescrption() {
        return combineDescrption;
    }

    public void setCombineDescrption(String combineDescrption) {
        this.combineDescrption = combineDescrption;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getNowCondition() {
        return nowCondition;
    }

    public void setNowCondition(String nowCondition) {
        this.nowCondition = nowCondition == null ? null : nowCondition.trim();
    }

    public String getHiscondition() {
        return hiscondition;
    }

    public void setHiscondition(String hiscondition) {
        this.hiscondition = hiscondition == null ? null : hiscondition.trim();
    }

    public String getChiefcomplaint() {
        return chiefcomplaint;
    }

    public void setChiefcomplaint(String chiefcomplaint) {
        this.chiefcomplaint = chiefcomplaint == null ? null : chiefcomplaint.trim();
    }

    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory == null ? null : personalHistory.trim();
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies == null ? null : allergies.trim();
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId == null ? null : prescriptionId.trim();
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId == null ? null : treeId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption == null ? null : descrption.trim();
    }

    public Integer getIsLastCode() {
        return isLastCode;
    }

    public void setIsLastCode(Integer isLastCode) {
        this.isLastCode = isLastCode;
    }

    public Integer getCombineId() {
        return combineId;
    }

    public void setCombineId(Integer combineId) {
        this.combineId = combineId;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getPermissionTypeName() {
        return permissionTypeName;
    }

    public void setPermissionTypeName(String permissionTypeName) {
        this.permissionTypeName = permissionTypeName;
    }

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}