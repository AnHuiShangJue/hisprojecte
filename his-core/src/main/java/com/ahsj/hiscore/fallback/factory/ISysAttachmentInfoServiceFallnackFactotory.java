package com.ahsj.hiscore.fallback.factory;

import com.ahsj.hiscore.fallback.IOrganizationServiceFallback;
import com.ahsj.hiscore.fallback.ISysAttachmentInfoServiceFallnack;
import com.ahsj.hiscore.feign.IOrganizationService;
import com.ahsj.hiscore.feign.ISysAttachmentInfoService;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ISysAttachmentInfoServiceFallnackFactotory
 * <p>
 * Date:     2019/7/21 10:04
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

@Component
public class ISysAttachmentInfoServiceFallnackFactotory  implements FallbackFactory<ISysAttachmentInfoService> {

    private static final Logger LOG = LoggerFactory.getLogger(ISysAttachmentInfoServiceFallnackFactotory.class);
    public static final String ERR_MSG = "Test接口暂时不可用: ";

    @Override
    public ISysAttachmentInfoService create(Throwable cause) {
        String msg = cause == null ? "" : cause.getMessage();
        if (!StringUtils.isEmpty(msg)) {
            LOG.error(msg);
        }
        return new ISysAttachmentInfoServiceFallnack();
    }
}
