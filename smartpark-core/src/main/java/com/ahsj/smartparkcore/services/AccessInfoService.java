package com.ahsj.smartparkcore.services;

import com.ahsj.smartparkcore.entity.dto.AccessInfoDTO;
import com.ahsj.smartparkcore.entity.po.AccessInfo;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.vo.AccessInfoVO;
import core.entity.PageBean;
import core.message.Message;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: AccessInfoService
 * <p>
 * Date:     2019/10/14 15:07
 *
 * @author XJP
 * @create 2019/10/14
 * @since 1.0.0
 */

public interface AccessInfoService {
    /**
     * @return core.message.Message
     * @功能说明
     * @Params [accessInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 11:33
     **/
    Message addAccessinfo(AccessInfoDTO accessInfoDTO) throws Exception;

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.AccessInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 12:59
     **/
    PageBean<AccessInfo> queryList(PageBean<AccessInfo> pageBean);

    /**
     * @return com.ahsj.smartparkcore.entity.vo.AccessInfoVO
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 15:32
     **/
    AccessInfo selectById(Long id) throws Exception;

    Message updateAccessinfo(AccessInfoDTO accessInfoDTO)throws Exception;

    /**
     * @return com.ahsj.smartparkcore.entity.po.AccessInfo
     * @功能说明
     * @Params []
     * @Author XJP
     * @Date 2019/10/15
     * @Time 14:06
     **/
    /*  AccessInfo getaccessinfoAll() throws Exception;*/
}
