package com.ahsj.smartparkcore.services.impl;

import com.ahsj.smartparkcore.dao.LegalPersonMapper;
import com.ahsj.smartparkcore.entity.po.LegalPerson;
import com.ahsj.smartparkcore.services.LegalPersonService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: LegalPersonServiceImpl
 * <p>
 * Date:     2019/10/14 13:40
 *
 * @author XJP
 * @create 2019/10/14
 * @since 1.0.0
 */

@Service
public class LegalPersonServiceImpl implements LegalPersonService {

    @Autowired
    LegalPersonMapper legalPersonMapper;

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 13:43
     **/
    @Override
    @Transactional(readOnly = false)
    public Message insert(LegalPerson legalPerson) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(legalPerson)) {
            return MessageUtil.createMessage(false, "法人信息新增失败 ！！！");
        } else {
            legalPersonMapper.insert(legalPerson);
            return MessageUtil.createMessage(true, "法人信息新增成功 ！！！");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 16:54
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByPrimaryKeySelective(LegalPerson legalPerson) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(legalPerson)) {
            return MessageUtil.createMessage(false, "法人信息更新失败 ！！！");
        } else {
            legalPersonMapper.updateByPrimaryKeySelective(legalPerson);
            return MessageUtil.createMessage(true, "法人信息新增成功 ！！！");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明
     * @Params [legalPerson]
     * @Author XJP
     * @Date 2019/10/14
     * @Time 17:25
     **/
    @Override
    @Transactional(readOnly = false)
    public Message updateByCompanyId(LegalPerson legalPerson) {
        if (EmptyUtil.Companion.isNullOrEmpty(legalPerson)) {
            return MessageUtil.createMessage(false, "法人信息更新失败 ！！！");
        } else {
            legalPersonMapper.updateByCompanyId(legalPerson);
            return MessageUtil.createMessage(true, "法人信息新增成功 ！！！");
        }
    }
}
