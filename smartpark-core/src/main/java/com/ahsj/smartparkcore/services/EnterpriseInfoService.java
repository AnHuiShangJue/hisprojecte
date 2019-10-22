package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.dto.EnterpriseInfoDTO;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO;
import core.entity.PageBean;
import core.message.Message;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    Message auditEnterpriseInfo(Long id) throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.po.EnterpriseInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/11
     * @Time 17:57
     **/
    EnterpriseInfo selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.vo.EnterpriseInfoVO
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 14:02
     **/
    EnterpriseInfoVO selectById(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [enterpriseInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 10:51
     **/
    Message updateIsEnable(EnterpriseInfoDTO enterpriseInfoDTO) throws Exception;

    /**
     * @return java.util.List<com.ahsj.smartparkcore.entity.po.EnterpriseInfo>
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/22
     * @Time 15:33
     **/
    List<EnterpriseInfo> enterpriseInfoAll() throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.po.EnterpriseInfo
     * @功能说明
     * @Params [enterpriseInfo]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 15:34
     **/
    EnterpriseInfo queryEnterpriseInfo(EnterpriseInfo enterpriseInfo) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/22
     * @Time 17:54
     **/
    Message reviewFail(Long id) throws Exception;
}
