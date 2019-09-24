package com.ahsj.hiscore.entity.TranslateModel;


import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisMedicationDetailsTranslate
 * <p>
 * Date:     2019/8/1 19:20
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisMedicationDetailsTranslate implements Serializable {

    private static final long serialVersionUID = -7486527751810265208L;

    private Long id;

    private String drugsNumb;

    private String drugsName;

    private String drugsAlias;

    private String description;

    public HisMedicationDetailsTranslate() {
    }

    public String getDrugsNumb() {
        return drugsNumb;
    }

    public void setDrugsNumb(String drugsNumb) {
        this.drugsNumb = drugsNumb;
    }

    public String getDrugsName() {
        return drugsName;
    }

    public void setDrugsName(String drugsName) {
        this.drugsName = drugsName;
    }

    public String getDrugsAlias() {
        return drugsAlias;
    }

    public void setDrugsAlias(String drugsAlias) {
        this.drugsAlias = drugsAlias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
