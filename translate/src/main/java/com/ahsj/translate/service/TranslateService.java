package com.ahsj.translate.service;


import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateModel.TranslateDelete;
import com.ahsj.translate.entity.model.TranslateModel;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateService
 * <p>
 * Date:     2019/7/24 10:26
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

public interface TranslateService {

    /**
     * @return int
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 10:28
     **/
    Message insert(Translate translate);

    /**
     * @return com.ahsj.translate.entity.Translate
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 10:29
     **/
    Translate selectByPrimaryKey(Long id);

    /**
     * @return core.message.Message
     * @功能说明 批量插入 并翻译
     * @Params [translateModel]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 11:08
     **/
    Message addTranslateList(TranslateModel translateModel) throws Exception;

    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 通过 模块id type Column 查询
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:03
     **/
    List<Translate> queryTranslateList(Translate translate) throws Exception;

    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 获得翻译表所有数据
     * @Params []
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:34
     **/
    List<Translate> queryTranslateListAll();

    /**
     * @return com.ahsj.translate.entity.Translate
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/29
     * @Time 18:28
     **/
    Translate queryTranslate(Translate translate);

    /**
     * @return void
     * @功能说明
     * @Params [translateDelete]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:58
     **/
    void deleteTranslate(TranslateDelete translateDelete) throws Exception;
}
