package com.ahsj.userinfor.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateDelete
 * <p>
 * Date:     2019/8/2 10:14
 *
 * @author XJP
 * @create 2019/8/2
 * @since 1.0.0
 */

public class TranslateDelete implements Serializable {

    private static final long serialVersionUID = 7352659409792443157L;

    Long id ;

    String model;

    public TranslateDelete() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
