package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.entity.dto.SysAttachmentInfoFeign;
import com.ahsj.smartparkcore.entity.sys.SysAttachmentInfo;
import com.ahsj.smartparkcore.feign.ISysAttachmentInfoService;
import com.ahsj.smartparkcore.services.SysAttachmentInfoService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: SysAttachmentInfoServiceImpl
 * <p>
 * Date:     2019/7/21 9:49
 *
 * @author XJP
 * @create 2019/7/21
 * @since 1.0.0
 */

@Service
public class SysAttachmentInfoServiceImpl implements SysAttachmentInfoService {

    @Autowired
    ISysAttachmentInfoService iSysAttachmentInfoService;


    /**
     * @return core.message.Message
     * @功能说明 单一上传
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:38
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addSaveSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception {
        iSysAttachmentInfoService.addSaveSysAttachmentInfo(sysAttachmentInfo);
        return MessageUtil.createMessage(true, "上传成功!");
    }

    /**
     * @return com.ahsj.hiscore.entity.sys.SysAttachmentInfo
     * @功能说明 主键查询id
     * @Params [id]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:38
     **/
    @Override
    @Transactional(readOnly = true)
    public SysAttachmentInfo selectByPrimaryKey(Long id) throws Exception {
        SysAttachmentInfo sysAttachmentInfo = iSysAttachmentInfoService.selectByPrimaryKey(id);
        return sysAttachmentInfo;
    }

    /**
     * @return core.message.Message
     * @功能说明 批量上传附件
     * @Params [list]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:39
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addSaveSysAttachmentInfoList(List<SysAttachmentInfo> list) throws Exception {
        if (CollectionUtils.isEmpty(list)) {
            return MessageUtil.createMessage(false, "上传失败 ! 请选择上传文件！！！！");
        } else {
            SysAttachmentInfoFeign sysAttachmentInfoFeign = new SysAttachmentInfoFeign();
            sysAttachmentInfoFeign.setAttachmentInfoList(list);
            iSysAttachmentInfoService.addSaveSysAttachmentInfoList(sysAttachmentInfoFeign);
            return MessageUtil.createMessage(true, "上传成功!");
        }
    }

    /**
     * @return java.util.List<com.ahsj.hiscore.entity.sys.SysAttachmentInfo>
     * @功能说明 通过附件关联key和附件来源地址查询集合
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:39
     **/
    @Override
    @Transactional(readOnly = true)
    public List<SysAttachmentInfo> querySysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo)) {
            return null;
        } else {
            List<SysAttachmentInfo> sysAttachmentInfos = iSysAttachmentInfoService.querySysAttachmentInfo(sysAttachmentInfo);
            if (CollectionUtils.isEmpty(sysAttachmentInfos)) {
                return null;
            } else {
                return sysAttachmentInfos;
            }
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 通过主键更新（单一更新）
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:41
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSysAttachmentInfo(SysAttachmentInfo sysAttachmentInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo)) {
            return MessageUtil.createMessage(false, "修改附件失败。");
        } else {
            iSysAttachmentInfoService.updateSysAttachmentInfo(sysAttachmentInfo);
            return MessageUtil.createMessage(true, "修改附件成功。");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 对关联附件的key进行修改操作
     * @Params [sysAttachmentInfo]
     * @Author XJP
     * @Date 2019/7/23
     * @Time 10:58
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateSysAttachmentInfoRelatekey(SysAttachmentInfo sysAttachmentInfo) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo) || EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo.getId()) || EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo.getRelateKey())) {
            return MessageUtil.createMessage(false, "修改附件失败。");
        } else {
            SysAttachmentInfo sysAttachmentInfo1 = iSysAttachmentInfoService.selectByPrimaryKey(sysAttachmentInfo.getId());
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo1.getRelateKey()) || EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo1.getRelatePage())) {
                return MessageUtil.createMessage(false, "修改附件失败。 修改附件不存在");
            } else {
                String relateKey = sysAttachmentInfo1.getRelateKey();
                String[] split = relateKey.split(",");
                for (String s : split) {
                    if (s.equals(sysAttachmentInfo.getRelateKey())) {
                        //return MessageUtil.createMessage(false, "修改附件失败。 修改附件关联值已存在");
                        List<String> list = Arrays.asList(split);
                        List<String> newList = new ArrayList<>(list);
                        boolean remove = newList.remove(sysAttachmentInfo.getRelateKey());
                        if (remove){
                            String relates= "";
                            for (String relate : newList) {
                                relates += relate + ",";
                            }
                            String relatesOne = StringUtils.substringBeforeLast(relates, ",");
                            sysAttachmentInfo.setRelateKey(relatesOne);
                            iSysAttachmentInfoService.updateSysAttachmentInfo(sysAttachmentInfo);
                            return MessageUtil.createMessage(true, "修改附件成功 ！！！");
                        }
                    }
                }
                List<String> list = Arrays.asList(split);
                List<String> newList = new ArrayList<>(list);
                newList.add(sysAttachmentInfo.getRelateKey());
                String relate = "";
                for (String s : newList) {
                    relate += s + ",";
                }
                String relates = StringUtils.substringBeforeLast(relate, ",");
                sysAttachmentInfo.setRelateKey(relates);
                iSysAttachmentInfoService.updateSysAttachmentInfo(sysAttachmentInfo);
                return MessageUtil.createMessage(true, "修改附件成功 ！！！");
            }
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
    public Message deleteSysAttachmentInfoById(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)){
            return MessageUtil.createMessage(false, "删除参数不能为空。");
        }else {
            SysAttachmentInfo sysAttachmentInfo = iSysAttachmentInfoService.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(sysAttachmentInfo)){
                return MessageUtil.createMessage(false, "删除记录本不存在 ！！！！");
            }else {
                iSysAttachmentInfoService.deleteSysAttachmentInfoById(id);
                return MessageUtil.createMessage(true, "删除成功。");
            }
        }
    }
}
