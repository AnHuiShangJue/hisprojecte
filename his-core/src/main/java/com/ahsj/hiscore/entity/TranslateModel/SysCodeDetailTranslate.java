package com.ahsj.hiscore.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysCodeDetailTranslate
 * <p>
 * Date:     2019/8/1 19:19
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class SysCodeDetailTranslate implements Serializable {

    private static final long serialVersionUID = -5971776508874595859L;
    private Integer id;

    private String code;

    private String value;

    public SysCodeDetailTranslate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
