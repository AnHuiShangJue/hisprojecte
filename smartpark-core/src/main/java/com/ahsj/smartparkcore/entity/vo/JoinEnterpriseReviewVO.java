package com.ahsj.smartparkcore.entity.vo;

import com.ahsj.smartparkcore.entity.po.JoinEnterpriseReview;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: JoinEnterpriseReviewVO
 * <p>
 * Date:     2019/10/22 15:14
 *
 * @author XJP
 * @create 2019/10/22
 * @since 1.0.0
 */

public class JoinEnterpriseReviewVO  extends JoinEnterpriseReview {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
