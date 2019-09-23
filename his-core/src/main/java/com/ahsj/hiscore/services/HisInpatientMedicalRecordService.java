package com.ahsj.hiscore.services;

import com.ahsj.hiscore.common.utils.ZipUtils;
import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import core.entity.PageBean;

public interface HisInpatientMedicalRecordService {
    /**
     *@Description 根据住院编号查询对应住院病历
     *@Params [pageBean]
     *@return core.entity.PageBean<com.ahsj.hiscore.entity.HisInpatientMedicalRecord>
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 7:59
    **/
    PageBean<HisInpatientMedicalRecord> listByHospitalManageId(PageBean<HisInpatientMedicalRecord> pageBean)throws Exception;

    /**
     *@Description 新增大病历
     *@Params []
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 9:28
    **/
    void save(HisInpatientMedicalRecord hisInpatientMedicalRecord)throws Exception;

    /**
     *@Description
     *@Params [id]
     *@return com.ahsj.hiscore.entity.HisInpatientMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 9:37
    **/
    HisInpatientMedicalRecord selectById(Long id)throws Exception;

    /**
     *@Description 根据小病历ID 和类型查询大病历中对应的病历
     *@Params [check]
     *@return com.ahsj.hiscore.entity.HisInpatientMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 11:08
    **/
    HisInpatientMedicalRecord selectByTargetIdAndType(HisInpatientMedicalRecord check)throws Exception;

    /**
     *@Description 
     *@Params [hisInpatientMedicalRecord]
     *@return void
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 14:05
    **/
    void update(HisInpatientMedicalRecord hisInpatientMedicalRecord)throws Exception;
}
