package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisTransferRecordMapper;
import com.ahsj.hiscore.entity.HisHospitalManage;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.entity.HisMedicalRecord;
import com.ahsj.hiscore.entity.HisTransferRecord;
import com.ahsj.hiscore.services.HisHospitalManageService;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import com.ahsj.hiscore.services.HisMedicalRecordService;
import com.ahsj.hiscore.services.HisTransferRecordService;
import core.entity.PageBean;
import core.message.Message;
import core.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.EmptyUtil;


@Service
public class HisTransferRecordServiceImpl implements HisTransferRecordService {

    @Autowired
    HisTransferRecordMapper hisTransferRecordMapper;

    @Autowired
    HisHospitalManageService hisHospitalManageService;

    @Autowired
    HisMedicalRecordService hisMedicalRecordService;

    @Autowired
    HisInpatientMedicalRecordService hisInpatientMedicalRecordService;



    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 15:51
     * @Return core.entity.PageBean<com.ahsj.hiscore.entity.HisTransferRecord>
     * @Params [pageBean]
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisTransferRecord> list(PageBean<HisTransferRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisTransferRecordMapper.list(pageBean)));
        return pageBean;
    }


    /**
     * @Description 转出记录save&update
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 15:51
     * @Return core.message.Message
     * @Params [hisTransferRecord]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message saveOrUpdate(HisTransferRecord hisTransferRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTransferRecord.getId()))
        {
            hisTransferRecord.setTransferType(2);
            hisTransferRecord.setIsSign(2);
        hisTransferRecordMapper.insert(hisTransferRecord);

            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(24);
            hisInpatientMedicalRecord.setTargetId(hisTransferRecord.getId());
            hisInpatientMedicalRecord.setHospitalManageId(hisTransferRecord.getHosptalManageId());
            HisHospitalManage hisHospitalManage =hisHospitalManageService.selectById(hisTransferRecord.getHosptalManageId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);

        return MessageUtil.createMessage(true,"新增成功！");
        }else {
            hisTransferRecordMapper.updateByPrimaryKey(hisTransferRecord);
            return MessageUtil.createMessage(true,"更新成功！");
        }
    }


    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 15:51
     * @Return core.message.Message
     * @Params [ids]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message delete(Long[] ids) throws Exception {
        for (Long id:ids){
            hisTransferRecordMapper.deleteByPrimaryKey(id);
        }
        return MessageUtil.createMessage(true,"删除成功");
    }


    /**
     * @Description 转入记录save&update
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 15:51
     * @Return core.message.Message
     * @Params [hisTransferRecord]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message InSaveOrUpdate(HisTransferRecord hisTransferRecord) throws Exception {
        if (EmptyUtil.Companion.isNullOrEmpty(hisTransferRecord.getId()))
        {
            hisTransferRecord.setTransferType(1);
            hisTransferRecord.setIsSign(2);
            hisTransferRecordMapper.insert(hisTransferRecord);
            //新增大病历记录
            HisInpatientMedicalRecord hisInpatientMedicalRecord = new HisInpatientMedicalRecord();
            hisInpatientMedicalRecord.setMedicalRecordType(23);
            hisInpatientMedicalRecord.setTargetId(hisTransferRecord.getId());
            hisInpatientMedicalRecord.setHospitalManageId(hisTransferRecord.getHosptalManageId());
            HisHospitalManage hisHospitalManage =hisHospitalManageService.selectById(hisTransferRecord.getHosptalManageId());
            HisMedicalRecord hisMedicalRecord = hisMedicalRecordService.selectByMedicalRecordId(hisHospitalManage.getMedicalNumber());
            hisInpatientMedicalRecord.setRecordId(hisMedicalRecord.getId());
            hisInpatientMedicalRecordService.save(hisInpatientMedicalRecord);


            return MessageUtil.createMessage(true,"新增成功！");
        }else {
            hisTransferRecordMapper.updateByPrimaryKey(hisTransferRecord);
            return MessageUtil.createMessage(true,"更新成功！");
        }
    }


    /**
     * @Description
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 15:51
     * @Return core.message.Message
     * @Params [hisTransferRecord]
    **/
    @Override
    @Transactional(readOnly = false)
    public Message sign(HisTransferRecord hisTransferRecord) throws Exception {
        if (hisTransferRecord.getIsSign().equals(1)){
            return MessageUtil.createMessage(false,"已签字，请勿重复签字");
        }else{
            hisTransferRecord.setIsSign(1);
            hisTransferRecordMapper.updateByPrimaryKey(hisTransferRecord);
         return MessageUtil.createMessage(true,"签名成功！");
        }
    }

    /**
     * @Description 根据id查询
     * @Author  muxu
     * @Date  2019/9/16
     * @Time 16:12
     * @Return com.ahsj.hiscore.entity.HisTransferRecord
     * @Params [id]
    **/
    @Override
    @Transactional(readOnly = true)
    public HisTransferRecord selectById(Long id) {
        return hisTransferRecordMapper.selectByPrimaryKey(id);
    }
}
