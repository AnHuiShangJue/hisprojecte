package com.ahsj.translate.service;

import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateInfo;
import com.ahsj.translate.entity.TranslateModel.TranslateInfoModels;
import com.ahsj.translate.entity.model.TranslateInfoModel;
import com.ahsj.translate.entity.model.TranslateModel;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateInfoService
 * <p>
 * Date:     2019/8/8 16:14
 *
 * @author XJP
 * @create 2019/8/8
 * @since 1.0.0
 */

public interface TranslateInfoService {

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:32
     **/
    Message insert(TranslateInfo translateInfo);

    /**
     * @return com.ahsj.translate.entity.TranslateInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:32
     **/
    TranslateInfo selectByPrimaryKey(Long id);

    /**
     * @return core.message.Message
     * @功能说明 批量添加  调用服务版
     * @Params [translateInfoModel]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:32
     **/
    Message addTranslateList(TranslateInfoModels translateInfoModels) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 批量添加 消息队列  定时任务
     * @Params [TranslateInfoModel]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 10:54
     **/
    Message addTranslateInfoList(TranslateInfoModel TranslateInfoModel) throws Exception;


    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    List<TranslateInfo> queryTranslateList(TranslateInfo translateInfo);

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    List<TranslateInfo> queryTranslateListAll();

    /**
     * @return com.ahsj.translate.entity.TranslateInfo
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    TranslateInfo queryTranslate(TranslateInfo translateInfo);

    /**
     * @return int
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/8
     * @Time 16:24
     **/
    Message deleteByTranslate(TranslateInfo translateInfo);


    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    List<TranslateInfo> selectByTranslateType();

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    List<TranslateInfo> selectByTranslateTypeAndTranslateId(TranslateInfo translateInfo);


}
