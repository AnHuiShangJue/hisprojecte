package com.ahsj.smartparkcore.entity.dto;

import com.ahsj.smartparkcore.entity.po.JoinEnterpriseReview;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: JoinEnterpriseReviewDTO
 * <p>
 * Date:     2019/10/22 15:13
 *
 * @author XJP
 * @create 2019/10/22
 * @since 1.0.0
 */

public class JoinEnterpriseReviewDTO extends JoinEnterpriseReview {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
