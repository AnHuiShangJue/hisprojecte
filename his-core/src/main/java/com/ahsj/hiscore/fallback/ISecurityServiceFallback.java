package com.ahsj.hiscore.fallback;

import com.ahsj.hiscore.feign.ISecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ISecurityServiceFallback implements ISecurityService {
    Logger logger = LoggerFactory.getLogger(ITranslateServiceFallnack.class.getSimpleName());

    @Override
    public void systemShutdownService() throws Exception {
        logger.error("");
    }
}
