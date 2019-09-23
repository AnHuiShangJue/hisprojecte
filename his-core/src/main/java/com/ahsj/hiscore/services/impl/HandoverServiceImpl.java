package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisHandoverMapper;
import com.ahsj.hiscore.entity.HisHandover;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.services.HandoverService;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @program: hisprojecte
 * @description:交班管理
 * @author: chenzhicai
 * @create: 2019-09-15-13-22
 **/
@Service
public class HandoverServiceImpl implements HandoverService {

    @Autowired
    HisHandoverMapper hisHandoverMapper;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;


    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisHandover hisHandover) throws Exception {
        if(EmptyUtil.Companion.isNullOrEmpty(hisHandover.getId())){
            hisHandoverMapper.insert(hisHandover);
            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(6);
            hisInpatientMedicalRecord.setTargetId(hisHandover.getId());
            HisHospitalManage hisHospitalManage =hisHospitalManageService.selectByHospNumber(hisHandover.getHospitalNumber());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

            return MessageUtil.createMessage(true,"创建成功！");
        }else{
            hisHandoverMapper.updateByPrimaryKey(hisHandover);
            return MessageUtil.createMessage(true,"修改成功！");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisHandover> list(PageBean<HisHandover> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisHandoverMapper.list(pageBean)) );
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public HisHandover selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisHandoverMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description 根据住院编号查找
     *@Params [number]
     *@return com.ahsj.hiscore.entity.HisHandover
     *@Author chenzhicai
     *@Date 2019-09-16
     *@Time 12:35
     **/
    @Override
    @Transactional(readOnly = true)
    public HisHandover selectByHosNumber(String number) {
        return CodeHelper.getInstance().setCodeValue(hisHandoverMapper.selectByHosNumber(number));
    }
}

    