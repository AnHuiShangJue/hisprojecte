package com.ahsj.hiscore.services.impl;


import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.*;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisPatientInfo;
import com.ahsj.hiscore.entity.HisRegistered;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisPatientService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class HisMedicalRecordImpl implements HisMedicalRecordService {
    @Autowired
    HisMedicalRecordMapper hisMedicalRecordMapper;

    @Autowired
    HisRegisteredMapper hisRegisteredMapper;

    @Autowired
    HisPatientInfoMapper hisPatientInfoMapper;

    @Autowired
    HisDoctorInfoMapper hisDoctorInfoMapper;

    @Autowired
    HisMedicalMapper hisMedicalMapper;

    @Autowired
    HisPatientService hisPatientService;

    /**
     * @Description 新增
     * @Author muxu
     * @Date 2019/6/27
     * @Time 15:31
     * @Return core.message.Message
     * @Params [hisMedicalRecord, request]
     **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisMedicalRecord hisMedicalRecord, HttpServletRequest request) throws Exception {

        HisMedicalRecord checkIfExit = hisMedicalRecordMapper.selectByPrimaryKey(hisMedicalRecord.getId());
        //check out 身份证和工号是否唯一
        if (EmptyUtil.Companion.isNullOrEmpty(checkIfExit)) {
            return MessageUtil.createMessage(false, "更新失败!用户模块服务访问失败!请联系管理员!");
        } else {
            // 如果返回俩条数据则意味着工号和身份证已经存在

            hisMedicalRecordMapper.updateByPrimaryKeySelective(hisMedicalRecord);
            return MessageUtil.createMessage(true, "更新成功");
        }
    }

    /**
     * @Description list
     * @Author muxu
     * @Date 2019/6/27
     * @Time 16:22
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalRecord> list(PageBean<HisMedicalRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalRecordMapper.list(pageBean)));
        return pageBean;

    }

    /**
     * @Description 就诊记录表list
     * @Author muxu
     * @Date 2019/7/8
     * @Time 11:12
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Params [pageBean]
     **/

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalRecord> medicalrecordlist(PageBean<HisMedicalRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalRecordMapper.medicalrecordlist(pageBean)));

        return pageBean;

    }


    /**
     * @Description 就诊记录新增
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:04
     * @Return core.message.Message
     * @Params [hisMedicalRecord]
     **/
    @Override
    @Transactional(readOnly = false)
    public Message recEnter(Long id, String userId, Integer type, Long patientId) throws Exception {
        HisRegistered hisRegistered;
        HisMedicalRecord check;
        String name = "HM";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String createdate = sdf.format(date);
        int count = hisMedicalRecordMapper.selectMedicalCount(createdate) + 1;
        HisMedicalRecord hisMedicalRecord = new HisMedicalRecord();
        if (EmptyUtil.Companion.isNullOrEmpty(type)) {
            type = 1;
            switch (type) {
                case 1:
                    hisRegistered = hisRegisteredMapper.selectByPrimaryKey(id);
                    HisRegistered hisRegistered1 = hisRegisteredMapper.selectByPrimaryKey(id);
                    hisRegistered1.setIsReceived(1);
                    hisRegisteredMapper.updateByPrimaryKey(hisRegistered1);
                    if (hisRegistered.getIsObsolete().equals(1))
                        return MessageUtil.createMessage(false, "接诊失败！该挂号单已经作废");
                    check = hisMedicalRecordMapper.selectByRegisterId(id);
                    if (!EmptyUtil.Companion.isNullOrEmpty(check))
                        return MessageUtil.createMessage(false, "接诊失败！该病人已经被人接诊了");
                    hisMedicalRecord.setPatientId(hisRegistered.getPatientId());
                    hisMedicalRecord.setRegisteredId(hisRegistered.getId());
                    break;
                case 2:
                    name = "HHM";
                    HisPatientInfo hisPatientInfo = hisPatientService.selectById(patientId);
                    hisMedicalRecord.setPatientId(hisPatientInfo.getId());
                    if (EmptyUtil.Companion.isNullOrEmpty(patientId))
                        return MessageUtil.createMessage(false, "接诊失败！该病人无建档信息");

                    break;
                default:
                    hisRegistered = hisRegisteredMapper.selectByPrimaryKey(id);
                    if (hisRegistered.getIsObsolete().equals(1))
                        return MessageUtil.createMessage(false, "接诊失败！该挂号单已经作废");
                    check = hisMedicalRecordMapper.selectByRegisterId(id);
                    if (!EmptyUtil.Companion.isNullOrEmpty(check))
                        return MessageUtil.createMessage(false, "接诊失败！该病人已经被人接诊了");
                    hisMedicalRecord.setPatientId(hisRegistered.getPatientId());
                    hisMedicalRecord.setRegisteredId(hisRegistered.getId());
                    break;
            }
        } else {
            switch (type) {
                case 1:
                    hisRegistered = hisRegisteredMapper.selectByPrimaryKey(id);
                    if (hisRegistered.getIsObsolete().equals(1))
                        return MessageUtil.createMessage(false, "接诊失败！该挂号单已经作废");
                    check = hisMedicalRecordMapper.selectByRegisterId(id);
                    if (!EmptyUtil.Companion.isNullOrEmpty(check))
                        return MessageUtil.createMessage(false, "接诊失败！该病人已经被人接诊了");
                    hisMedicalRecord.setPatientId(hisRegistered.getPatientId());
                    hisMedicalRecord.setRegisteredId(hisRegistered.getId());
                    break;
                case 2:
                    name = "HHM";
                    HisPatientInfo hisPatientInfo = hisPatientService.selectById(patientId);
                    hisMedicalRecord.setPatientId(hisPatientInfo.getId());
                    if (EmptyUtil.Companion.isNullOrEmpty(patientId))
                        return MessageUtil.createMessage(false, "接诊失败！该病人无建档信息");

                    break;
                default:
                    hisRegistered = hisRegisteredMapper.selectByPrimaryKey(id);
                    if (hisRegistered.getIsObsolete().equals(1))
                        return MessageUtil.createMessage(false, "接诊失败！该挂号单已经作废");
                    check = hisMedicalRecordMapper.selectByRegisterId(id);
                    if (!EmptyUtil.Companion.isNullOrEmpty(check))
                        return MessageUtil.createMessage(false, "接诊失败！该病人已经被人接诊了");
                    hisMedicalRecord.setPatientId(hisRegistered.getPatientId());
                    hisMedicalRecord.setRegisteredId(hisRegistered.getId());
                    break;
            }
        }
        //编号
        String number = name + createdate + String.format("%03d", count);
        //序号
        hisMedicalRecord.setMedicalRecordId(number);
        hisMedicalRecord.setDoctorId(new Long(userId));
        hisMedicalRecord.setType(type);
        hisMedicalRecordMapper.insert(hisMedicalRecord);
        return MessageUtil.createMessage(true, "接诊成功");
    }


    /**
     * @Description 根据id查询
     * @Author muxu
     * @Date 2019/7/1
     * @Time 23:04
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [patientId]
     **/

    @Override
    public HisMedicalRecord selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisMedicalRecordMapper.selectByPrimaryKey(id));
    }

    /**
     * @Description 删除
     * @Author muxu
     * @Date 2019/7/8
     * @Time 11:02
     * @Return core.message.Message
     * @Params [ids]
     **/
    @Override
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisMedicalRecordMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功！");
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/9
     * @Time 9:54
     * @Return
     * @Params
     **/

    @Override
    @Transactional(readOnly = true)
    public HisMedicalRecord selectByMedicalRecordId(String medicalRecordId) {
        return hisMedicalRecordMapper.selectByMedicalRecordId(medicalRecordId);
    }

    /**
     * @Description
     * @Author muxu
     * @Date 2019/7/12
     * @Time 11:37
     * @Return com.ahsj.hiscore.entity.HisMedicalRecord
     * @Params [id]
     **/
    @Override
    public HisMedicalRecord selectByRegisterId(Long id) {
        return hisMedicalRecordMapper.selectByRegisterId(id);
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Description 查看过往病历（由就诊记录表medical_record,住院信息表hospital_manage,护嘱表ankle以及医嘱表medical_order中的信息组合而成）根据PatientId来查找此病人病历
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-07-15
     * @Time 16:09
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalRecord> listForMedicalHistoryByPatientId(PageBean<HisMedicalRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalRecordMapper.listForMedicalHistoryByPatientId(pageBean)));
        return pageBean;
    }

    /**
     * @return core.entity.PageBean<com.ahsj.hiscore.entity.HisMedicalRecord>
     * @Description 查看病人所有历史病历不包括正在住院的那一条
     * @Params [pageBean]
     * @Author zhushixiang
     * @Date 2019-08-17
     * @Time 15:06
     **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisMedicalRecord> listForAllMedicalHistoryByPatientId(PageBean<HisMedicalRecord> pageBean) {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisMedicalRecordMapper.listForAllMedicalHistoryByPatientId(pageBean)));
        return pageBean;
    }

    /**
     * @Description
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisMedicalRecord
     * @Date 2019/9/7
     * @Time 14:04
     **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalRecord selectPrint(String number) throws Exception {
        return hisMedicalRecordMapper.selectPrint(number);
    }

    /**
     *@Description 根据病人ID查询出最近一条的病人就诊编号
     *@Params [patientId]
     *@return com.ahsj.hiscore.entity.HisMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-10
     *@Time 17:20
    **/
    @Override
    @Transactional(readOnly = true)
    public HisMedicalRecord selectTheLastRecordByPatientId(Long patientId) throws Exception {
        return hisMedicalRecordMapper.selectTheLastRecordByPatientId(patientId);
    }
}


