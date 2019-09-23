package com.ahsj.hiscore.services.impl;

import com.ahsj.hiscore.core.CodeHelper;
import com.ahsj.hiscore.dao.HisInpatientMedicalRecordMapper;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import com.ahsj.hiscore.services.HisInpatientMedicalRecordService;
import core.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HisInpatientMedicalRecordServicelmpl implements HisInpatientMedicalRecordService {
    @Autowired
    HisInpatientMedicalRecordMapper hisInpatientMedicalRecordMapper;

    /**
     *@Description 根据住院编号查询对应住院病历
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 7:59
    **/
    @Override
    @Transactional(readOnly = true)
    public PageBean<HisInpatientMedicalRecord> listByHospitalManageId(PageBean<HisInpatientMedicalRecord> pageBean) throws Exception {
        pageBean.setData(CodeHelper.getInstance().setCodeValue(hisInpatientMedicalRecordMapper.listByHospitalManageId(pageBean)));
        return pageBean;
    }

    /**
     *@Description 新增大病历
     *@Params
     *@return
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 9:28
    **/
    @Override
    @Transactional(readOnly = true)
    public void save(HisInpatientMedicalRecord hisInpatientMedicalRecord) throws Exception {
        hisInpatientMedicalRecordMapper.insert(hisInpatientMedicalRecord);
    }

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisInpatientMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 9:37
    **/
    @Override
    @Transactional(readOnly = true)
    public HisInpatientMedicalRecord selectById(Long id) throws Exception {
        return hisInpatientMedicalRecordMapper.selectByPrimaryKey(id);
    }

    /**
     *@Description 
     *@Params 根据小病历ID 和类型查询大病历中对应的病历
     *@return com.ahsj.hiscore.entity.HisInpatientMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 10:37
    **/
    @Override
    @Transactional(readOnly = true)
    public HisInpatientMedicalRecord selectByTargetIdAndType(HisInpatientMedicalRecord check) throws Exception {
        return CodeHelper.getInstance().setCodeValue(hisInpatientMedicalRecordMapper.selectByTargetIdAndType(check));
    }

    @Override
    @Transactional(readOnly = false)
    public void update(HisInpatientMedicalRecord hisInpatientMedicalRecord) throws Exception {
        hisInpatientMedicalRecordMapper.updateByPrimaryKeySelective(hisInpatientMedicalRecord);
    }
}
