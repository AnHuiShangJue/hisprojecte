package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.dao.HisDiagnosisTreatmentMapper;
import com.ahsj.hiscore.entity.HisDiagnosisTreatment;
import com.ahsj.hiscore.services.HisDiagnosisTreatmentService;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisDiagnosisTreatmentServiceImpl
 * <p>
 * Date:     2019/9/16 16:16
 *
 * @author XJP
 * @create 2019/9/16
 * @since 1.0.0
 */

@Service
public class HisDiagnosisTreatmentServiceImpl implements HisDiagnosisTreatmentService {

    @Autowired
    HisDiagnosisTreatmentMapper hisDiagnosisTreatmentMapper;

    /**
     * @return core.message.Message
     * @功能说明 增添和修改有创治疗信息
     * @Params [hisDiagnosisTreatment]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:22
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addOrUpdateHisDiagnosisTreatment(HisDiagnosisTreatment hisDiagnosisTreatment) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment)) {
            return MessageUtil.createMessage(false, "操作失败 ！！！");
        } else {
            if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment.getId())) {
                hisDiagnosisTreatmentMapper.insert(hisDiagnosisTreatment);
                return MessageUtil.createMessage(true, "有创治疗信息新增成功 ！！！");
            } else {
                hisDiagnosisTreatmentMapper.updateByPrimaryKeySelective(hisDiagnosisTreatment);
                return MessageUtil.createMessage(true, "有创治疗信息修改成功 ！！！");
            }
        }
    }

    /**
     * @return com.ahsj.hiscore.entity.HisDiagnosisTreatment
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/17
     * @Time 10:29
     **/
    @Override
    @Transactional(readOnly = true)
    public HisDiagnosisTreatment selectByPrimaryKey(Long id) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(id)) {
            return new HisDiagnosisTreatment();
        } else {
            HisDiagnosisTreatment hisDiagnosisTreatment = hisDiagnosisTreatmentMapper.selectByPrimaryKey(id);
            if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment)) {
                return new HisDiagnosisTreatment();
            } else {
                return hisDiagnosisTreatment;
            }
        }
    }

    /**
     *@功能说明 医生有创治疗签字
     *@Params [hisRescue]
     *@return core.message.Message
     *@Author XJP
     *@Date 2019/9/16
     *@Time 9:26
     **/
    @Override
    @Transactional(readOnly = false)
    public Message signature(HisDiagnosisTreatment hisDiagnosisTreatment, Long userId) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment) || EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment.getId()) || EmptyUtil.Companion.isNullOrEmpty(userId)) {
            return MessageUtil.createMessage(false, "签字失败！！！");
        } else {
            hisDiagnosisTreatment = hisDiagnosisTreatmentMapper.selectByPrimaryKey(hisDiagnosisTreatment.getId());
            if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment)) {
                return MessageUtil.createMessage(false, "签字失败！无签字内容！！");
            } else {
                if (EmptyUtil.Companion.isNullOrEmpty(hisDiagnosisTreatment.getAlreadySignId())) {
                    hisDiagnosisTreatment.setAlreadySignId(userId.toString());
                    hisDiagnosisTreatmentMapper.updateByPrimaryKeySelective(hisDiagnosisTreatment);
                    return MessageUtil.createMessage(true, "签字成功！！！");
                } else {
                    String[] split = StringUtils.split(hisDiagnosisTreatment.getAlreadySignId(), ",");
                    if (getList(split, userId + "")) {
                        return MessageUtil.createMessage(false, "签字失败！ 您已经签过字！！");
                    }else {
                        hisDiagnosisTreatment.setAlreadySignId(hisDiagnosisTreatment.getAlreadySignId() + "," + userId);
                        hisDiagnosisTreatmentMapper.updateByPrimaryKey(hisDiagnosisTreatment);
                        return MessageUtil.createMessage(true, "签字成功！！！");
                    }
                }
            }
        }
    }

    /**
     * @return java.lang.Boolean
     * @功能说明
     * @Params [strs, str]
     * @Author XJP
     * @Date 2019/9/16
     * @Time 10:01
     **/
    public Boolean getList(String[] strs, String str) {
        for (String s : strs) {
            if (StringUtils.equals(s, str)) {
                return true;
            }
        }
        return false;
    }
}
