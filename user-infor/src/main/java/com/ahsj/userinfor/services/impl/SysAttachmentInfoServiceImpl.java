package com.ahsj.userinfor.services.impl;

import com.ahsj.userinfor.dao.SysAttachmentInfoMapper;
import com.ahsj.userinfor.entity.SysAttachmentInfo;
import com.ahsj.userinfor.services.SysAttachmentInfoService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.Collection;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoServiceImpl
 * <p>
 * Date:     2019/7/21 9:21
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

@Service
public class SysAttachmentInfoServiceImpl implements SysAttachmentInfoService {


    @Autowired
    SysAttachmentInfoMapper sysAttachmentInfoMapper;


    /**
     *@功能说明 
     *@Params [sysAttachmentInfo]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:19
    **/
    @Override
    @Transactional(readOnly = false)
    public Message  insert(SysAttachmentInfo sysAttachmentInfo) {
        if (!EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo.getId())){
            return MessageUtil.createMessage(false, "文件上传失败。");
        }else {
            sysAttachmentInfoMapper.insert(sysAttachmentInfo);
            return MessageUtil.createMessage(true, "文件上传成功。");
        }
    }

    /**
     *@功能说明 
     *@Params [id]
     *@return com.ahsj.userinfor.entity.SysAttachmentInfo
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:00
    **/
    @Override
    @Transactional(readOnly = true)
    public SysAttachmentInfo selectByPrimaryKey(Long id) {
        SysAttachmentInfo sysAttachmentInfo = sysAttachmentInfoMapper.selectByPrimaryKey(id);
        return sysAttachmentInfo;
    }

    /**
     *@功能说明 
     *@Params [attachmentInfoList]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:00
    **/
    @Override
    @Transactional(readOnly = false)
    public Message insertList(List<SysAttachmentInfo> attachmentInfoList) {
        if (CollectionUtils.isEmpty(attachmentInfoList)){
            return MessageUtil.createMessage(false, "文件上传失败 ,  请选择上传文件。");
        }else {
            sysAttachmentInfoMapper.insertList(attachmentInfoList);
            return MessageUtil.createMessage(true, "文件上传成功。");
        }
    }

    /**
     *@功能说明 
     *@Params [sysAttachmentInfo]
     *@return java.util.List<com.ahsj.userinfor.entity.SysAttachmentInfo>
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:00
    **/
    @Override
    @Transactional(readOnly = true)
    public List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) {
        if(EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo)){
            return null;
        }else {
            List<SysAttachmentInfo> sysAttachmentInfos =   sysAttachmentInfoMapper.querySysAttachmentInfo(sysAttachmentInfo);
            if (CollectionUtils.isEmpty(sysAttachmentInfos)){
                return null;
            }else {
                return sysAttachmentInfos;
            }
        }
    }

    /**
     *@功能说明 
     *@Params [sysAttachmentInfo]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/7/23
     *@Time 13:00
    **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) {
        if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo)){
            return MessageUtil.createMessage(false, "修改附件失败。");
        }else {
            sysAttachmentInfoMapper.updateByPrimaryKeySelective(sysAttachmentInfo);
            return MessageUtil.createMessage(true, "修改附件成功。");
        }
    }

    /**
     * @return void
     * @功能说明 删除
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 13:57
     **/
    @Override
    @Transactional(readOnly = false)
    public Message deleteSysAttachmentInfoById(Long id) {
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            return MessageUtil.createMessage(false, "删除参数不能为空。");
        }else {
            SysAttachmentInfo sysAttachmentInfo = sysAttachmentInfoMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo)){
                return MessageUtil.createMessage(false, "删除记录本不存在 ！！！！");
            }else {
                sysAttachmentInfoMapper.deleteByPrimaryKey(id);
                return MessageUtil.createMessage(true, "删除成功。");

            }
        }
    }
}
