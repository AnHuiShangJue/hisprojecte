package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.common.Constants;
import com.ahsj.smartparkcore.core.CodeHelper;
import com.ahsj.smartparkcore.dao.AccessInfoMapper;
import com.ahsj.smartparkcore.entity.dto.AccessInfoDTO;
import com.ahsj.smartparkcore.entity.po.AccessInfo;
import com.ahsj.smartparkcore.entity.po.EnterpriseInfo;
import com.ahsj.smartparkcore.entity.po.LegalPerson;
import com.ahsj.smartparkcore.entity.vo.AccessInfoVO;
import com.ahsj.smartparkcore.services.AccessInfoService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: AccessInfoServiceImpl
 * <p>
 * Date:     2019/10/14 15:08
 *
 * @author XJP
 * @create 2019/10/14
 * @since 1.0.0
 */

@Service
public class AccessInfoServiceImpl implements AccessInfoService {

    @Autowired
    AccessInfoMapper accessInfoMapper;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [accessInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 12:55
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addAccessinfo(AccessInfoDTO accessInfoDTO) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(accessInfoDTO)) {
            return MessageUtil.createMessage(false, "访客申请失败 ！！！");
        } else {
            AccessInfo accessInfo = new AccessInfo();
            BeanUtils.copyProperties(accessInfoDTO, accessInfo);
            accessInfo.setIsVerify(Constants.TWO);
            accessInfoMapper.insert(accessInfo);
            return MessageUtil.createMessage(true, "访客申请成功 ！！！");
        }
    }

    /**
     * @return core.entity.PageBean<com.ahsj.smartparkcore.entity.po.AccessInfo>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 13:04
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<AccessInfo> queryList(PageBean<AccessInfo> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(accessInfoMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return com.ahsj.smartparkcore.entity.po.AccessInfo
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 16:53
     **/
    @Override
    @Transactional(readOnly = true)
    public AccessInfo selectById(Long id) throws Exception {
        return accessInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [accessInfoDTO]
     * @Author XJP
     * @Date 2019/10/15
     * @Time 16:53
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateAccessinfo(AccessInfoDTO accessInfoDTO) throws Exception {
        AccessInfo accessInfo = new AccessInfo();
        BeanUtils.copyProperties(accessInfoDTO, accessInfo);
        if (EmptyUtil.Companion.isNullOrEmpty(accessInfo)) {
            return MessageUtil.createMessage(false, "访客申请信息修改失败 ！！！");
        } else {
            AccessInfo info = accessInfoMapper.queryAccessInfo(accessInfo);
            if (EmptyUtil.Companion.isNullOrEmpty(info)) {
                accessInfoMapper.updateByPrimaryKeySelective(accessInfo);
                return MessageUtil.createMessage(true, "访客申请信息成功 ！！！");
            } else {
                if (StringUtils.equals(info.getAccessIdcard(), accessInfo.getAccessIdcard())) {
                    if (info.getId().longValue() == accessInfo.getId().longValue()) {
                        accessInfoMapper.updateByPrimaryKeySelective(accessInfo);
                        return MessageUtil.createMessage(true, "访客申请信息成功 ！！！");
                    } else {
                        return MessageUtil.createMessage(false, "访客申请信息失败 ！ 该企业已存在  ！！");
                    }
                } else {
                    return MessageUtil.createMessage(false, "访客申请信息失败 ！ 该企业已存在   ！");
                }
            }
        }

    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/10/23
     * @Time 10:08
     **/
    @Override
    @Transactional(readOnly = false)
    public Message reviewSuccess(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return MessageUtil.createMessage(false, "访客申请信息审核失败 ！！！");
        }
        AccessInfo accessInfo = accessInfoMapper.selectByPrimaryKey(id);
        if (accessInfo.getIsVerify().equals(Constants.ONE)) {
            return MessageUtil.createMessage(false, "该访客申请信息审核已经成功！ 无法继续审核 ！！！！！");
        } else {
            accessInfo.setIsVerify(Constants.ONE);
            accessInfoMapper.updateByPrimaryKey(accessInfo);
            return MessageUtil.createMessage(true, "访客申请信息审核成功 ！该访客申请信息验证通过 ！！");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Message reviewFail(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return MessageUtil.createMessage(false, "访客申请信息审核失败 ！！！");
        }
        AccessInfo accessInfo = accessInfoMapper.selectByPrimaryKey(id);
        if (accessInfo.getIsVerify().equals(Constants.ONE)) {
            return MessageUtil.createMessage(false, "该访客申请信息审核已经成功！ 无法继续审核 ！！！！！");
        } else {
            accessInfo.setIsVerify(Constants.THREE);
            accessInfoMapper.updateByPrimaryKey(accessInfo);
            return MessageUtil.createMessage(true, "访客申请信息审核成功 ！该访客申请信息验证通过 ！！");
        }
    }
}
