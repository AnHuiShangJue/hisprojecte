package com.ahsj.userinfor.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysCodeTranslate
 * <p>
 * Date:     2019/8/1 20:46
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class SysCodeTranslate implements Serializable {
    private static final long serialVersionUID = -6741612595099572575L;

    private Long id;

    private String type;

    private String name;

    public SysCodeTranslate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
