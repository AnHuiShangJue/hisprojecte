package com.ahsj.hiscore.fallback;

import com.ahsj.hiscore.entity.Translate;
import com.ahsj.hiscore.entity.model.TranslateModel;
import com.ahsj.hiscore.entity.sys.TranslateInfo;
import com.ahsj.hiscore.feign.ITranslateService;
import core.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ITranslateServiceFallnack
 * <p>
 * Date:     2019/7/24 15:41
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */

@RestController
public class ITranslateServiceFallnack implements ITranslateService {

    Logger logger = LoggerFactory.getLogger(ITranslateServiceFallnack.class.getSimpleName());

    /**
     * @return void
     * @功能说明
     * @Params [translateModel]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public void addTranslate(TranslateModel translateModel) {
        logger.error("服务获取异常,不能够访问翻译服务");
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.Translate>
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public List<Translate> queryTranslate(@RequestBody Translate translate) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.Translate>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public List<Translate> queryTranslateAll() {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return com.ahsj.hiscore.entity.Translate
     * @功能说明
     * @Params [translate]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public Translate queryTranslateClo(Translate translate) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new Translate();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public List<TranslateInfo> queryTranslateInfoAll() throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public List<TranslateInfo> queryTranslate(TranslateInfo translateInfo) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public List<TranslateInfo> selectByTranslateType() throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.TranslateInfo>
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public List<TranslateInfo> selectByTranslateTypeAndTranslateId(TranslateInfo translateInfo) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    /**
     * @return void
     * @功能说明
     * @Params [translateInfo]
     * @Author XJP
     * @Date 2019/8/11
     * @Time 13:56
     **/
    @Override
    public void deleteByTranslate(TranslateInfo translateInfo) throws Exception {
        logger.error("服务获取异常,不能够访问组织服务");
    }
}
