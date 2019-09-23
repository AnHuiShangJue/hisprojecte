package com.ahsj.hiscore.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisPrescriptionTranslate
 * <p>
 * Date:     2019/8/30 14:43
 *
 * @author XJP
 * @create 2019/8/30
 * @since 1.0.0
 */

public class HisPrescriptionTranslate implements Serializable {

    private static final long serialVersionUID = -4892602157566576564L;

    private Long id;

    private String name;

    private String descrption;

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

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public String toString() {
        return "HisPrescriptionTranslate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descrption='" + descrption + '\'' +
                '}';
    }
}
