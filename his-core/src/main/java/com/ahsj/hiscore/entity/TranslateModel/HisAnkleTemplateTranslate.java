package com.ahsj.hiscore.entity.TranslateModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisAnkleTemplateTranslate
 * <p>
 * Date:     2019/8/1 19:20
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisAnkleTemplateTranslate implements Serializable {

    private static final long serialVersionUID = -3569238714229781285L;

    private Long id;

    private String templateNumber;

    private String templateName;

    private String name;

    public HisAnkleTemplateTranslate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
