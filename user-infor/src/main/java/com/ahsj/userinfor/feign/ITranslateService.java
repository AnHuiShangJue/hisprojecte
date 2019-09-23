package com.ahsj.userinfor.feign;


import com.ahsj.userinfor.entity.Translate;
import com.ahsj.userinfor.entity.model.TranslateModel;
import com.ahsj.userinfor.fallback.factory.ITranslateServiceFallnackFactotory;
import com.ahsj.userinfor.feign.config.FeignConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

@FeignClient(name = "his-translate", fallbackFactory = ITranslateServiceFallnackFactotory.class, configuration = FeignConfiguration.class)
public interface ITranslateService {


    /**
     *@功能说明
     *@Params [translateModel]
     *@return void
     *@Author XJP
     *@Date 2019/7/24
     *@Time 16:09
    **/
    @PostMapping("/api/translate/add.ahsj")
    @LoadBalanced
    void addTranslate(@RequestBody TranslateModel translateModel);

    /**
     *@功能说明
     *@Params [translate]
     *@return java.util.List<com.ahsj.hiscore.entity.Translate>
     *@Author XJP
     *@Date 2019/7/24
     *@Time 16:09
    **/
    @PostMapping("/api/translate/query/translate.ahsj")
    @LoadBalanced
    List<Translate> queryTranslate(@RequestBody Translate translate);

    /**
     *@功能说明
     *@Params []
     *@return java.util.List<com.ahsj.hiscore.entity.Translate>
     *@Author XJP
     *@Date 2019/7/24
     *@Time 16:09
    **/
    @PostMapping("/api/translate/query/All/translate.ahsj")
    @LoadBalanced
    List<Translate> queryTranslateAll();

}
