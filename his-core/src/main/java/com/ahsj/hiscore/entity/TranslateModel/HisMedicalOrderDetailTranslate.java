package com.ahsj.hiscore.entity.TranslateModel;

import core.code.CodeValueColumn;
import core.code.Constants;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisMedicalOrderDetailTranslate
 * <p>
 * Date:     2019/10/7 9:29
 *
 * @author XJP
 * @create 2019/10/7
 * @since 1.0.0
 */

public class HisMedicalOrderDetailTranslate implements Serializable {

    private Long id;

    private String name;

    private String usages;

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
        this.name = name;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }
}
