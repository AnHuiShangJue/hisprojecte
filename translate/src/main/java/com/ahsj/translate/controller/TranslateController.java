package com.ahsj.translate.controller;

import com.ahsj.translate.entity.Translate;
import com.ahsj.translate.entity.model.TranslateModel;
import com.ahsj.translate.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: TranslateController
 * <p>
 * Date:     2019/7/24 10:35
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

@Controller
@RequestMapping("/api/translate")
public class TranslateController {

    @Autowired
    TranslateService translateService;


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
    public void addTranslate(@RequestBody TranslateModel translateModel) throws Exception {
        translateService.addTranslateList(translateModel);
    }


    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 通过 模块id type Column 查询
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:03
     **/
    @PostMapping("/query/translate.ahsj")
    @ResponseBody
    public List<Translate> queryTranslate(@RequestBody Translate translate) throws Exception {
        return translateService.queryTranslateList(translate);
    }



    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 通过 模块id type Column 查询
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:03
     **/
    @PostMapping("/query/translatess.ahsj")
    @ResponseBody
    public Translate queryTranslateClo(@RequestBody Translate translate){
        return translateService.queryTranslate(translate);
    }


    /**
     * @return java.util.List<com.ahsj.translate.entity.Translate>
     * @功能说明 获得翻译表所有数据
     * @Params []
     * @Author XJP
     * @Date 2019/7/26
     * @Time 14:19
     **/
    @PostMapping("/query/All/translate.ahsj")
    @ResponseBody
    public List<Translate> queryTranslateAll() throws Exception {
        return translateService.queryTranslateListAll();
    }


}
