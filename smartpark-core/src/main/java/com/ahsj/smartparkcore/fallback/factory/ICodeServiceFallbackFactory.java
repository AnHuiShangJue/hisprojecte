package com.ahsj.smartparkcore.fallback.factory;

import com.ahsj.smartparkcore.fallback.ICodeServiceFallback;
import com.ahsj.smartparkcore.feign.ICodeService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: his
 * @description:ICodeServiceFallbackFactory
 * @author: chenzhicai
 * @create: 2019-06-19-15-11
 **/
@Component
public class ICodeServiceFallbackFactory implements FallbackFactory<ICodeService> {
    private static final Logger LOG = LoggerFactory.getLogger(ICodeServiceFallbackFactory.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";
    @Override
    public ICodeService create(Throwable cause) {
        String msg = cause == null ? "" : cause.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new ICodeServiceFallback();
    }
}

    