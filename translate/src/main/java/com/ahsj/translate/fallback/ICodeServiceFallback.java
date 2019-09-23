package com.ahsj.translate.fallback;

import com.ahsj.translate.entity.SysCodeDetail;
import com.ahsj.translate.feign.ICodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ICodeServiceFallback implements ICodeService {

     Logger logger = LoggerFactory.getLogger(ICodeServiceFallback.class.getSimpleName());

    @Override
    public List<SysCodeDetail> lstSysCodeDetail() throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new ArrayList<SysCodeDetail>();
    }

    @Override
    public SysCodeDetail SysCodeDetail(SysCodeDetail sysCodeDetail) throws Exception {
        logger.error("服务获取异常,不能够访问字典服务");
        return new SysCodeDetail();
    }
}
