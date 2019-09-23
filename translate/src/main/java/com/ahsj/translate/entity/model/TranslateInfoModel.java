package com.ahsj.translate.entity.model;

import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateInfo;

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

public class TranslateInfoModel {

    List<TranslateInfo> translateList;


    public TranslateInfoModel() {
    }

    public List<TranslateInfo> getTranslateList() {
        return translateList;
    }

    public void setTranslateList(List<TranslateInfo> translateList) {
        this.translateList = translateList;
    }


}
