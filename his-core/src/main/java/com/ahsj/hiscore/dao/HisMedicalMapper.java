package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisMedical;

public interface HisMedicalMapper extends BaseMapper<HisMedical>{
    int deleteByPrimaryKey(Long id);

    int insert(HisMedical record);

    int insertSelective(HisMedical record);

    HisMedical selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisMedical record);

    int updateByPrimaryKey(HisMedical record);

    HisMedical selectByRecordId(Long recordId);

    HisMedical selectPrint(String number);

    HisMedical selectByMedicalRecordId(Long medicalRecordid);
}