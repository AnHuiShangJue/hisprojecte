package com.ahsj.userinfor.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisMedicineInfoTranslate
 * <p>
 * Date:     2019/8/1 19:19
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisMedicineInfoTranslate implements Serializable {

    private static final long serialVersionUID = -1570060120933087163L;
    private Long id;

    private String drugsNumb;

    private String drugsName;

    private String drugsAlias;

    private String drugsSpec;

    public HisMedicineInfoTranslate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDrugsSpec() {
        return drugsSpec;
    }

    public void setDrugsSpec(String drugsSpec) {
        this.drugsSpec = drugsSpec;
    }
}
