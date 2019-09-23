package com.ahsj.hiscore.services;

import com.ahsj.hiscore.entity.sys.SysAttachmentInfo;
import core.message.Message;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoService
 * <p>
 * Date:     2019/7/21 9:48
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

public interface SysAttachmentInfoService {

    /**
     * @return core.message.Message
     * @功能说明 单一上传
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:38
     **/
    Message addSaveSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     * @return com.ahsj.hiscore.entity.sys.SysAttachmentInfo
     * @功能说明 主键查询id
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:38
     **/
    SysAttachmentInfo selectByPrimaryKey(Long id) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 批量上传附件
     * @Params [list]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:39
     **/
    Message addSaveSysAttachmentInfoList(List<SysAttachmentInfo> list) throws Exception;

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysAttachmentInfo>
     * @功能说明 通过附件关联key和附件来源地址查询集合
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:39
     **/
    List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     * @return core.message.Message
     * @功能说明 通过主键更新（单一更新）
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:41
     **/
    Message updateSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     *@功能说明 对关联附件的key进行修改操作
     *@Params [sysAttachmentInfo]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/23
     *@Time 10:58
     **/
    Message updateSysAttachmentInfoRelatekey(SysAttachmentInfo sysAttachmentInfo) throws Exception;

    /**
     * @return void
     * @功能说明 删除
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 13:57
     **/
    Message deleteSysAttachmentInfoById(Long id) throws Exception;
}
