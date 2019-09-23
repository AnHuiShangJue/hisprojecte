package com.ahsj.translate.controller;

import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.TranslateInfo;
import com.ahsj.translate.entity.TranslateModel.TranslateInfoModels;
import com.ahsj.translate.entity.model.TranslateInfoModel;
import com.ahsj.translate.entity.model.TranslateModel;
import com.ahsj.translate.service.TranslateInfoService;
import core.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateInfoController
 * <p>
 * Date:     2019/8/8 16:15
 *
 * @author XJP
 * @create 2019/8/8
 * @since 1.0.0
 */

@RestController
@RequestMapping("/api/translateInfo")
public class TranslateInfoController {

    @Autowired
    TranslateInfoService translateInfoService;

    /**
     * @return core.message.Message
     * @功能说明 批量插入 并翻译
     * @Params [translateModel]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 11:08
     **/
    @PostMapping("/add.ahsj")
    @ResponseBody
    public void addTranslate(@RequestBody TranslateInfoModels translateInfoModels) throws Exception {
        translateInfoService.addTranslateList(translateInfoModels);
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/9
     * @Time 13:24
     **/
    @PostMapping("/select/all.ahsj")
    @ResponseBody
    public List<TranslateInfo> queryTranslateInfoAll() throws Exception {
        return translateInfoService.queryTranslateListAll();
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 通过 模块id type Column 查询
     * @Params [translate]
     * @Author XJP
     * @Date 2019/8/9
     * @Time 13:33
     **/
    @PostMapping("/query/translateInfo.ahsj")
    @ResponseBody
    public List<TranslateInfo> queryTranslate(@RequestBody TranslateInfo translateInfo) throws Exception {
        return translateInfoService.queryTranslateList(translateInfo);
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    @PostMapping("/query/selectByTranslateType.ahsj")
    @ResponseBody
    public List<TranslateInfo> selectByTranslateType() {
        List<TranslateInfo> translateInfos = translateInfoService.selectByTranslateType();
        return translateInfos;
    }

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    @PostMapping("/query/selectByTranslateTypeAndTranslateId.ahsj")
    @ResponseBody
    public List<TranslateInfo> selectByTranslateTypeAndTranslateId(@RequestBody TranslateInfo translateInfo) {
        List<TranslateInfo> translateInfos = translateInfoService.selectByTranslateTypeAndTranslateId(translateInfo);
        return translateInfos;
    }

    @PostMapping("/query/deleteByTranslate.ahsj")
    @ResponseBody
    public void deleteByTranslate(@RequestBody TranslateInfo translateInfo) {
        translateInfoService.deleteByTranslate(translateInfo);
    }

}
