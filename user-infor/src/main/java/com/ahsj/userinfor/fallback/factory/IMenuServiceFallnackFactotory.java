package com.ahsj.userinfor.fallback.factory;

import com.ahsj.userinfor.fallback.IMenuServiceFallnack;
import com.ahsj.userinfor.fallback.ITranslateServiceFallnack;
import com.ahsj.userinfor.feign.IMenuService;
import com.ahsj.userinfor.feign.ITranslateService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ITranslateServiceFallnackFactotory
 * <p>
 * Date:     2019/7/24 15:43
 *
 * @author XJP
 * @create 2019/7/24
 * @since 1.0.0
 */
@Component
public class IMenuServiceFallnackFactotory implements FallbackFactory<IMenuService> {

    private static final Logger LOG = LoggerFactory.getLogger(IMenuServiceFallnackFactotory.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";

    @Override
    public IMenuService create(Throwable cause) {
        String msg = cause == null ? "" : cause.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new IMenuServiceFallnack();
    }
}
