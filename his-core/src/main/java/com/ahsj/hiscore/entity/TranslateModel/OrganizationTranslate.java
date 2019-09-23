package com.ahsj.hiscore.entity.TranslateModel;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: OrganizationTranslate
 * <p>
 * Date:     2019/8/1 19:18
 *
 * @author XJP
 * @create 2019/8/1
 * @since 1.0.0
 */

public class OrganizationTranslate implements Serializable {

    private static final long serialVersionUID = 1300692024792268261L;
    private Long id;

    private String name;

    private String responsible;

    private String address;

    public OrganizationTranslate() {
    }

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

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
