package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisInpatientMedicalRecord;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisInpatientMedicalRecordMapper extends BaseMapper<HisInpatientMedicalRecord>{
    int deleteByPrimaryKey(Long id);

    int insert(HisInpatientMedicalRecord record);

    int insertSelective(HisInpatientMedicalRecord record);

    HisInpatientMedicalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisInpatientMedicalRecord record);

    int updateByPrimaryKey(HisInpatientMedicalRecord record);

    /**
     *@Description 根据住院编号查询对应住院病历
     *@Params [pageBean]
     *@return java.util.List<? extends com.ahsj.hiscore.entity.HisInpatientMedicalRecord>
     *@Author zhushixiang
     *@Date 2019-09-16
     *@Time 8:03
     **/
    List<? extends HisInpatientMedicalRecord> listByHospitalManageId(PageBean<HisInpatientMedicalRecord> pageBean);

    /**
     *@Description 根据小病历ID 和类型查询大病历中对应的病历
     *@Params [check]
     *@return com.ahsj.hiscore.entity.HisInpatientMedicalRecord
     *@Author zhushixiang
     *@Date 2019-09-17
     *@Time 11:07
    **/
    HisInpatientMedicalRecord selectByTargetIdAndType(HisInpatientMedicalRecord check);
}