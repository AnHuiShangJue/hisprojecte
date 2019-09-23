package com.ahsj.userinfor.fallback;


import com.ahsj.userinfor.entity.Translate;
import com.ahsj.userinfor.entity.model.TranslateModel;
import com.ahsj.userinfor.feign.ITranslateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public void addTranslate(TranslateModel translateModel){
        logger.error("服务获取异常,不能够访问组织服务");
    }

    @Override
    public List<Translate> queryTranslate(Translate translate){
        logger.error("服务获取异常,不能够访问组织服务");
        return  new ArrayList<>();
    }

    @Override
    public List<Translate> queryTranslateAll()  {
        logger.error("服务获取异常,不能够访问组织服务");
        return  new ArrayList<>();
    }
}
