package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisHospitalizedRecord;

public interface HisHospitalizedRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisHospitalizedRecord record);

    int insertSelective(HisHospitalizedRecord record);

    HisHospitalizedRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisHospitalizedRecord record);

    int updateByPrimaryKey(HisHospitalizedRecord record);

    /**
     * @Description
     * @Params: [record]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisHospitalizedRecord
     * @Date 2019/9/18
     * @Time 11:19
     **/
    int countByIdCard(String idcard);
}