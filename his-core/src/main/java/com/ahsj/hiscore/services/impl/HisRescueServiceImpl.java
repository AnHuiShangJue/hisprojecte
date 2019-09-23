package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisRescueMapper;
import com.ahsj.hiscore.entity.*;
import com.ahsj.hiscore.feign.IUserService;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import java.util.Date;
import java.util.List;


/**
 * Copyright (C), 2019-2019, 安徽商角有限公司
 * FileName: HisRescueServiceImpl
 * <p>
 * Date:     2019/9/15 10:51
 *
 * @author XJP
 * @create 2019/9/15
 * @since 1.0.0
 */

@Service
public class HisRescueServiceImpl implements HisRescueService {

    @Autowired
    HisRescueMapper hisRescueMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    HisPatientInfoService hisPatientInfoService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    /**
     * @return com.ahsj.hiscore.entity.HisRescue
     * @功能说明
     * @Params [id]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 17:18
     **/
    @Override
    @Transactional(readOnly = true)
    public HisRescue selectByPrimaryKey(Long id) throws Exception {
        HisRescue hisRescue = hisRescueMapper.selectByPrimaryKey(id);
        HisPatientInfo hisPatientInfo = hisPatientInfoService.selectByPrimaryKey(hisRescue.getPatientId());
        hisRescue.setPatientName(hisPatientInfo.getName());
        return hisRescue;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisRescue>
     * @功能说明
     * @Params [pageBean]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 11:03
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisRescue> queryList(PageBean<HisRescue> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisRescueMapper.queryList(pageBean)));
        return pageBean;
    }

    /**
     * @return core.message.Message
     * @功能说明 增添抢救人员信息
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/15
     * @Time 14:23
     **/
    @Override
    @Transactional(readOnly = false)
    public Message addOrUpdateHisRescue(HisRescue hisRescue) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRescue.getRecordNumber()) || EmptyUtil.Companion.isNullOrEmpty(hisRescue.getStartTime()) || EmptyUtil.Companion.isNullOrEmpty(hisRescue.getEndTime())) {
            return MessageUtil.createMessage(false, "操作失败 ！！！");
        } else {
            //    List<HisRescue> hisRescues = hisRescueMapper.queryHisRescue(hisRescue);
            if (EmptyUtil.Companion.isNullOrEmpty(hisRescue.getId())) {
                HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByNumber(hisRescue.getRecordNumber());
                hisRescue.setHospitalManageId(hisHospitalManage.getId());
                hisRescueMapper.insert(hisRescue);

                //新增大病历记录
                HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
                hisInpatientMedicalRecord.setMedicalRecordType(4);
                hisInpatientMedicalRecord.setTargetId(hisRescue.getId());
                hisInpatientMedicalRecord.setHospitalManageId(hisRescue.getHospitalManageId());
                HisMedicalRecord hisMedicalRecord =hisMedicalRecordService.selectByMedicalRecordId(hisRescue.getRecordNumber());
                hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
                hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);
                return MessageUtil.createMessage(true, "添加抢救信息成功 ！！！");
            } else {
                hisRescueMapper.updateByPrimaryKey(hisRescue);
                return MessageUtil.createMessage(true, "修改抢救信息成功 ！！！");
            }
        }
    }


    @Override
    @Transactional(readOnly = false)
    public Message updateHisRescue(HisRescue hisRescue) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRescue) || EmptyUtil.Companion.isNullOrEmpty(hisRescue.getRecordNumber())) {
            return MessageUtil.createMessage(false, "修改失败 ！！！");
        } else {
            hisRescueMapper.updateByPrimaryKey(hisRescue);
            return MessageUtil.createMessage(true, "修改抢救信息成功 ！！！");
        }
    }

    /**
     * @return core.message.Message
     * @功能说明 医生抢救签字
     * @Params [hisRescue]
     * @Author XJP
     * @Date 2019/9/16
     * @Time 9:35
     **/
    @Override
    @Transactional(readOnly = false)
    public Message signature(HisRescue hisRescue, Long userId) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisRescue) || EmptyUtil.Companion.isNullOrEmpty(hisRescue.getId()) || EmptyUtil.Companion.isNullOrEmpty(userId)) {
            return MessageUtil.createMessage(false, "签字失败！！！");
        } else {
            hisRescue = hisRescueMapper.selectByPrimaryKey(hisRescue.getId());
            int days = differentDaysByMillisecond(hisRescue.getCreateDate(), new Date());
            if (days>1){
                return MessageUtil.createMessage(false, "签字失败！ 签字时间不能超过一天！！");
            }
            if (EmptyUtil.Companion.isNullOrEmpty(hisRescue.getAlreadySignId())) {
                hisRescue.setAlreadySignId(userId.toString());
                hisRescueMapper.updateByPrimaryKey(hisRescue);
                return MessageUtil.createMessage(true, "添加抢救签字成功 ！！！");
            } else {
                String[] split = StringUtils.split(hisRescue.getAlreadySignId(), ",");
                if (getList(split, userId + "")) {
                    return MessageUtil.createMessage(false, "签字失败！ 您已经签过字！！");
                }
                hisRescue.setAlreadySignId(hisRescue.getAlreadySignId() + "," + userId);
                hisRescueMapper.updateByPrimaryKey(hisRescue);
                return MessageUtil.createMessage(true, "添加抢救签字成功 ！！！");
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

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     *@Description
     *@Params [targetId]
     *@return com.ahsj.hiscore.entity.HisRescue
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 11:08
    **/
    @Override
    @Transactional(readOnly = true)
    public HisRescue selectById(Long targetId) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisRescueMapper.selectByPrimaryKey(targetId));
    }
}
