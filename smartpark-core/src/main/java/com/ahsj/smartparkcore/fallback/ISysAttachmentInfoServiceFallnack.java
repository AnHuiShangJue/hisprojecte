package com.ahsj.smartparkcore.fallback;

import com.ahsj.smartparkcore.entity.dto.SysAttachmentInfoFeign;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.feign.ISysAttachmentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: ISysAttachmentInfoServiceFallnack
 * <p>
 * Date:     2019/7/21 9:52
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

@RestController
public class ISysAttachmentInfoServiceFallnack implements ISysAttachmentInfoService {

    Logger logger = LoggerFactory.getLogger(ISysAttachmentInfoServiceFallnack.class.getSimpleName());


    @Override
    public void addSaveSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) {
            logger.error("服务获取异常,不能够访问组织服务");
    }

    @Override
    public void addSaveSysAttachmentInfoList(SysAttachmentInfoFeign sysAttachmentInfoFeign) {
        logger.error("服务获取异常,不能够访问组织服务");
    }

    @Override
    public SysAttachmentInfo selectByPrimaryKey(Long id){
        logger.error("服务获取异常,不能够访问组织服务");
        return new SysAttachmentInfo();
    }

    @Override
    public List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) {
        logger.error("服务获取异常,不能够访问组织服务");
        return new ArrayList<>();
    }

    @Override
    public void updateSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) {
        logger.error("服务获取异常,不能够访问组织服务");
    }

    @Override
    public void deleteSysAttachmentInfoById(Long id) {
        logger.error("服务获取异常,不能够访问组织服务");

    }

}
