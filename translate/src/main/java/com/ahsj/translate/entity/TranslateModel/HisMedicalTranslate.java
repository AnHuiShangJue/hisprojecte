package com.ahsj.translate.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisMedicalTranslate
 * <p>
 * Date:     2019/9/7 11:01
 *
 * @author XJP
 * @create 2019/9/7
 * @since 1.0.0
 */

public class HisMedicalTranslate implements Serializable {


    private static final long serialVersionUID = -3044937556339481449L;

    private Long id;

    private String patientIdcard;

    private String patientName;

    private String nowCondition;

    private String hiscondition;

    private String chiefcomplaint;

    private String personalHistory;

    private String allergies;

    private String familyHistory;

    private String other;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getNowCondition() {
        return nowCondition;
    }

    public void setNowCondition(String nowCondition) {
        this.nowCondition = nowCondition;
    }

    public String getHiscondition() {
        return hiscondition;
    }

    public void setHiscondition(String hiscondition) {
        this.hiscondition = hiscondition;
    }

    public String getChiefcomplaint() {
        return chiefcomplaint;
    }

    public void setChiefcomplaint(String chiefcomplaint) {
        this.chiefcomplaint = chiefcomplaint;
    }

    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
