package com.ahsj.hiscore.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisPrescriptionMedicineTranslate
 * <p>
 * Date:     2019/8/30 14:44
 *
 * @author XJP
 * @create 2019/8/30
 * @since 1.0.0
 */

public class HisPrescriptionMedicineTranslate implements Serializable {

    private static final long serialVersionUID = -5842693954120882346L;

    private Long id;

    private String description;

    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
