package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.common.Constants;
import com.ahsj.hiscore.common.utils.JsonUtils;
import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisBloodTransfusionRecordMapper;
import com.ahsj.hiscore.entity.HisBloodTransfusionRecord;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.TranslateModel.TranslateDelete;
import com.ahsj.hiscore.services.*;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ahsj.hiscore.services.HisBloodTransfusionRecordService;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;

@Service
public class HisBloodTransfusionRecordServicelmpl implements HisBloodTransfusionRecordService {
    @Autowired
    HisBloodTransfusionRecordMapper hisBloodTransfusionRecordMapper;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    /**
     *@Description 根据住院ID分页查询
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisBloodTransfusionRecord>
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 10:26
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisBloodTransfusionRecord> listByHospitalManageId(PageBean<HisBloodTransfusionRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisBloodTransfusionRecordMapper.listByHospitalManageId(pageBean)));
        return pageBean;
    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisBloodTransfusionRecord
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:13
    **/
    @Override
    @Transactional(readOnly = true)
    public HisBloodTransfusionRecord selectById(Long id) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisBloodTransfusionRecordMapper.selectByPrimaryKey(id));
    }

    /**
     *@Description 新增或更新
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 11:44
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisBloodTransfusionRecord hisBloodTransfusionRecord) throws Exception {
        //ID空新增
       if(EmptyUtil.Companion.isNullOrEmpty(hisBloodTransfusionRecord.getId())){
           //新增输血记录
           hisBloodTransfusionRecord.setIsSign(2);
           HisHospitalManage hisHospitalManage = hisHospitalManageService.selectById(hisBloodTransfusionRecord.getHospitalManageId());
           HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
           hisBloodTransfusionRecord.setRecordId(hisMedicalRecord.getId());
           hisBloodTransfusionRecordMapper.insert(hisBloodTransfusionRecord);

           //新增大病历记录
           HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
           hisInpatientMedicalRecord.setMedicalRecordType(8);
           hisInpatientMedicalRecord.setTargetId(hisBloodTransfusionRecord.getId());
           hisInpatientMedicalRecord.setHospitalManageId(hisBloodTransfusionRecord.getHospitalManageId());
           hisInpatientMedicalRecord.setRecordId(hisBloodTransfusionRecord.getRecordId());
           hisInpatientMedicalRecord.setMustSignNumber(1);
           hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);
           return MessageUtil.createMessage(true,"新增输血记录成功（New blood transfusion record was successful）");
       }
       //ID不为空 更新
       else {
           hisBloodTransfusionRecordMapper.updateByPrimaryKeySelective(hisBloodTransfusionRecord);
           return MessageUtil.createMessage(true,"更新输血记录成功（Update blood transfusion record successfully）");
       }
    }
    
    /**
     *@Description 签字
     *@Params 
     *@return 
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:10
    **/
    @Override
    @Transactional(readOnly = false)
    public Message sign(Long id, Long loginUserId,String signName) throws Exception {
        HisBloodTransfusionRecord hisBloodTransfusionRecord = hisBloodTransfusionRecordMapper.selectByPrimaryKey(id);
        if(hisBloodTransfusionRecord.getIsSign() == 1){
            return MessageUtil.createMessage(false,"已签字成功，请勿重复签字（Signed successfully, please do not repeat the signature）");
        }
        hisBloodTransfusionRecord.setDoctorId(loginUserId);
        hisBloodTransfusionRecord.setIsSign(1);
        HisInpatientMedicalRecord check = new HisInpatientMedicalRecord();
        check.setTargetId(id);
        check.setMedicalRecordType(8);
        HisInpatientMedicalRecord hisInpatientMedicalRecord = hisInpatientMedicalRecordService.selectByTargetIdAndType(check);
        hisInpatientMedicalRecord.setActualSignNumber(1);
        hisInpatientMedicalRecord.setSignName(signName);
        hisInpatientMedicalRecordService.update(hisInpatientMedicalRecord);
        hisBloodTransfusionRecordMapper.updateByPrimaryKeySelective(hisBloodTransfusionRecord);
        return MessageUtil.createMessage(true,"签字成功（Signed successfully）");
    }

    /**
     *@Description 删除
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-15
     *@Time 15:53
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id : ids) {
            hisBloodTransfusionRecordMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true, "删除成功!");
    }
}
