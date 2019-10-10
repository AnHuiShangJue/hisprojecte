package com.ahsj.hiscore.fallback.factory;

import com.ahsj.hiscore.fallback.ISecurityServiceFallback;
import com.ahsj.hiscore.fallback.ITranslateServiceFallnack;
import com.ahsj.hiscore.feign.ISecurityService;
import com.ahsj.hiscore.feign.ITranslateService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ISecurityServiceFallbackFactory implements FallbackFactory<ISecurityService> {
    private static final Logger LOG = LoggerFactory.getLogger(ISecurityServiceFallbackFactory.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";

    @Override
    public ISecurityService create(Throwable cause) {
        String msg = cause == null ? "" : cause.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new ISecurityServiceFallback();
    }
}
