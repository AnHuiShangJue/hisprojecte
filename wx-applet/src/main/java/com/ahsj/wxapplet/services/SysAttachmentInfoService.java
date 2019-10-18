package com.ahsj.wxapplet.services;


import com.ahsj.wxapplet.entity.SysAttachmentInfo;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoService
 * <p>
 * Date:     2019/7/21 9:20
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

public interface SysAttachmentInfoService {

    /**
     *@功能说明 
     *@Params [sysAttachmentInfo]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:00
    **/
    Message insert(SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     *@功能说明 
     *@Params [id]
     *@return com.ahsj.userinfor.entity.SysAttachmentInfo
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:00
    **/
    SysAttachmentInfo selectByPrimaryKey(Long id) throws Exception;


    /**
     *@功能说明 
     *@Params [attachmentInfoList]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/23
     *@Time 12:59
    **/
    Message insertList(List<SysAttachmentInfo> attachmentInfoList);

    /**
     *@功能说明 
     *@Params [sysAttachmentInfo]
     *@return java.util.List<com.ahsj.userinfor.entity.SysAttachmentInfo>
     *@Author XJP
     *@Date 2019/7/23
     *@Time 12:59
    **/
    List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo);

    /**
     * @return 
     * @功能说明 更新操作
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:34
     **/
    Message updateSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo);

    /**
     * @return void
     * @功能说明 删除
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 13:57
     **/
    Message deleteSysAttachmentInfoById(Long id);
}
