package com.ahsj.hiscore.entity.TranslateModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.code.CodeValueColumn;
import core.code.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisConsumablesBuyplanTranslate
 * <p>
 * Date:     2019/8/1 19:20
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class HisConsumablesBuyplanTranslate implements Serializable {

    private static final long serialVersionUID = -5139265679625802502L;
    private Long id;

    private Long buyplanId;

    private String personInCharge;

    private String completionName;

    public HisConsumablesBuyplanTranslate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyplanId() {
        return buyplanId;
    }

    public void setBuyplanId(Long buyplanId) {
        this.buyplanId = buyplanId;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getCompletionName() {
        return completionName;
    }

    public void setCompletionName(String completionName) {
        this.completionName = completionName;
    }
}

