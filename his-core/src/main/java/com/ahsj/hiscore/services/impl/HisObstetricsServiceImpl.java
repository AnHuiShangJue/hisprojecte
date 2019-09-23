package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisObstetricsMapper;
import com.ahsj.hiscore.entity.HisObstetrics;
import com.ahsj.hiscore.services.HisObstetricsService;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @Description
 * @Author dingli
 * @Date 2019/9/16
 * @Time 14:32
 **/
@Service
public class HisObstetricsServiceImpl implements HisObstetricsService {

    @Autowired
    HisObstetricsMapper hisObstetricsMapper;


    /**
     * @Description 孕科护理添加和修改
     * @Params: [hisObstetrics]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/16
     * @Time 15:44
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisObstetrics hisObstetrics) throws Exception {
        HisObstetrics ho = hisObstetricsMapper.selectByhisHospitalManageId(hisObstetrics.getHisHospitalManageId());
        if (EmptyUtil.Companion.isNullOrEmpty(ho)) {
            hisObstetricsMapper.insert(hisObstetrics);
            return MessageUtil.createMessage(true, "提交成功！");
        } else {
            hisObstetrics.setId(ho.getId());
            hisObstetricsMapper.updateByPrimaryKey(hisObstetrics);
            return MessageUtil.createMessage(true, "修改成功！");
        }
    }

    /**
     * @Description 护士签名
     * @Params: [hisObstetrics]
     * @Author: dingli
     * @Return: core.message.Message
     * @Date 2019/9/16
     * @Time 16:42
     **/
    @Override
    @Transactional(readOnly = false)
    public Message affirm(HisObstetrics hisObstetrics) throws Exception {
        HisObstetrics ho = hisObstetricsMapper.selectByhisHospitalManageId(hisObstetrics.getHisHospitalManageId());
        ho.setDoctorId(hisObstetrics.getDoctorId());
        hisObstetricsMapper.updateByPrimaryKey(ho);
        return MessageUtil.createMessage(true, "确认成功！");
    }

    /**
     * @Description 根据住院编号查询孕科护理
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/16
     * @Time 17:00
     **/
    @Override
    @Transactional(readOnly = true)
    public HisObstetrics selectByNumber(String number)throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisObstetricsMapper.selectByNumber(number));
    }

}
