package com.ahsj.hiscore.fallback.factory;

import com.ahsj.hiscore.fallback.IOrganizationServiceFallback;
import com.ahsj.hiscore.feign.IOrganizationService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: his
 * @description:
 * @author: chenzhicai
 * @create: 2019-07-01-17-45
 **/
@Component
public class IOrganizationServiceFallbackFactory implements FallbackFactory<IOrganizationService> {
    private static final Logger LOG = LoggerFactory.getLogger(IOrganizationServiceFallbackFactory.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";
    @Override
    public IOrganizationService create(Throwable cause) {
        String msg = cause == null ? "" : cause.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new IOrganizationServiceFallback();
    }
}

    