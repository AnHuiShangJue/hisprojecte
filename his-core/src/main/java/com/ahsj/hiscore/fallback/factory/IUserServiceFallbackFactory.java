package com.ahsj.hiscore.fallback.factory;

import com.ahsj.hiscore.fallback.IUserServiceFallback;
import com.ahsj.hiscore.feign.IUserService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: his
 * @description:IUserServiceFallbackFactory
 * @author: chenzhicai
 * @create: 2019-06-19-15-23
 **/

@Component
public class IUserServiceFallbackFactory implements FallbackFactory<IUserService> {
    private static final Logger LOG = LoggerFactory.getLogger(IUserServiceFallbackFactory.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";
    @Override
    public IUserService create(Throwable cause) {
        String msg = cause == null ? "" : cause.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new IUserServiceFallback();
    }
}

    