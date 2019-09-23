package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.EnterpriseInfo;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: EnterpriseInfoService
 * <p>
 * Date:     2019/9/2 15:41
 *
 * @author XJP
 * @create 2019/9/2
 * @since 1.0.0
 */

public interface EnterpriseInfoService {
    /**
     * @return core.message.Message
     * @功能说明
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:48
     **/
    Message addOrUpdateEnterpriseInfo(EnterpriseInfo enterpriseInfo, MultipartFile[] file, String relateKet, String relatePage) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.EnterpriseInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 16:48
     **/
    PageBean<EnterpriseInfo> queryList(PageBean<EnterpriseInfo> pageBean);

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/9/2
     * @Time 17:40
     **/
    Message auditEnterpriseInfo(EnterpriseInfo enterpriseInfo,String audit);
}
