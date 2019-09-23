package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisSuccessionMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisSuccession;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisSuccessionService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

/**
 * @program: hisprojecte
 * @description:
 * @author: chenzhicai
 * @create: 2019-09-16-22-29
 **/
@Service
public class HisSuccessionServiceImpl implements HisSuccessionService {

    @Autowired
    HisSuccessionMapper hisSuccessionMapper;
    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisSuccession hisSuccession) throws Exception {
        if(EmptyUtil.Companion.isNullOrEmpty(hisSuccession.getId())){
            hisSuccessionMapper.insert(hisSuccession);
            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(7);
            hisInpatientMedicalRecord.setTargetId(hisSuccession.getId());
            HisHospitalManage hisHospitalManage = hisHospitalManageService.selectByHospNumber(hisSuccession.getHospitalNumber());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setHospitalManageId(hisHospitalManage.getId());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

            return MessageUtil.createMessage(true,"创建成功！");
        }else{
            hisSuccessionMapper.updateByPrimaryKey(hisSuccession);
            return MessageUtil.createMessage(true,"修改成功！");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<HisSuccession> list(PageBean<HisSuccession> pageBean) throws Exception {
//        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisSuccessionMapper.list(pageBean)) );
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public HisSuccession selectById(Long id) {
        return CodeHelper.getInstance().setCodeValue(hisSuccessionMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional(readOnly = true)
    public HisSuccession selectByHosNumber(String number) {
        return CodeHelper.getInstance().setCodeValue(hisSuccessionMapper.selectByHosNumber(number));

    }
}

    