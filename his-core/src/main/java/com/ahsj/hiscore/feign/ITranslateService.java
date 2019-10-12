package com.ahsj.hiscore.feign;

import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.model.TranslateModel;
import com.ahsj.hiscore.entity.sys.TranslateInfo;
import com.ahsj.hiscore.fallback.factory.ITranslateServiceFallnackFactotory;
import com.ahsj.hiscore.feign.config.FeignConfiguration;
import core.message.Message;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ITranslateService
 * <p>
 * Date:     2019/7/24 15:38
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

@FeignClient(name = "eureka-zuul-inner", fallbackFactory = ITranslateServiceFallnackFactotory.class,path="histranslate/api/translate", configuration = FeignConfiguration.class)
public interface ITranslateService {


    /**
     * @return void
     * @功能说明
     * @Params [translateModel]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:09
     **/
    @PostMapping("/add.ahsj")
    @LoadBalanced
    void addTranslate(@RequestBody TranslateModel translateModel) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.Translate>
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:09
     **/
    @PostMapping("/query/translate.ahsj")
    @LoadBalanced
    List<Translate> queryTranslate(@RequestBody Translate translate) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.Translate>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/7/24
     * @Time 16:09
     **/
    @PostMapping("/query/All/translate.ahsj")
    @LoadBalanced
    List<Translate> queryTranslateAll() throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.Translate
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:55
     **/
    @PostMapping("/query/translatess.ahsj")
    @LoadBalanced
    Translate queryTranslateClo(@RequestBody Translate translate) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:54
     **/
    @PostMapping("/api/translateInfo/select/all.ahsj")
    @LoadBalanced
    List<TranslateInfo> queryTranslateInfoAll() throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:54
     **/
    @PostMapping("/api/translateInfo/query/translateInfo.ahsj")
    @LoadBalanced
    List<TranslateInfo> queryTranslate(@RequestBody TranslateInfo translateInfo) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:54
     **/
    @PostMapping("/api/translateInfo/query/selectByTranslateType.ahsj")
    @LoadBalanced
    List<TranslateInfo> selectByTranslateType() throws Exception;

    /**
     * @return java.util.List<com.ahsj.translate.entity.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:28
     **/
    @PostMapping("/api/translateInfo/query/selectByTranslateTypeAndTranslateId.ahsj")
    @LoadBalanced
    List<TranslateInfo> selectByTranslateTypeAndTranslateId(@RequestBody TranslateInfo translateInfo) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:54
     **/
    @PostMapping("/api/translateInfo/query/deleteByTranslate.ahsj")
    @LoadBalanced
    void deleteByTranslate(@RequestBody TranslateInfo translateInfo) throws Exception;

}
