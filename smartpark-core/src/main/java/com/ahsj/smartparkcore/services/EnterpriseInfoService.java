package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.core.ResultModel;
import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.http.ResponseEntity;
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
    Message addEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception;

    /**
     * @return org.springframework.http.ResponseEntity<com.ahsj.smartparkcore.core.ResultModel>
     * @功能说明
     * @Params [enterpriseInfoDTO, file, relateKet, relatePage]
     * @Author XJP
     * @Date 2019/10/11
     * @Time 16:35
     **/
    Message updateEnterpriseInfo(EnterpriseInfoDTO enterpriseInfoDTO, MultipartFile[] file, String relateKet, String relatePage) throws Exception;

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
    Message auditEnterpriseInfo(EnterpriseInfo enterpriseInfo, String audit);

    /**
     * @return com.ahsj.smartparkcore.entity.po.EnterpriseInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/11
     * @Time 17:57
     **/
    EnterpriseInfo selectByPrimaryKey(Long id) throws Exception;
}
