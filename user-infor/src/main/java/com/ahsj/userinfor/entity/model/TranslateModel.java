package com.ahsj.userinfor.entity.model;


import com.ahsj.userinfor.entity.Translate;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateModel
 * <p>
 * Date:     2019/7/24 11:05
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

public class TranslateModel {

    List<Translate> translateList;

    Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TranslateModel() {
    }

    public List<Translate> getTranslateList() {
        return translateList;
    }

    public void setTranslateList(List<Translate> translateList) {
        this.translateList = translateList;
    }

    @Override
    public String toString() {
        return "TranslateModel{" +
                "translateList=" + translateList +
                ", userId=" + userId +
                '}';
    }
}
