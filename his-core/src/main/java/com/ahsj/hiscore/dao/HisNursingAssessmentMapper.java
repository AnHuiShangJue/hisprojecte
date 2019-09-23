package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisNursingAssessment;

public interface HisNursingAssessmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisNursingAssessment record);

    int insertSelective(HisNursingAssessment record);

    HisNursingAssessment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisNursingAssessment record);

    int updateByPrimaryKey(HisNursingAssessment record);

    /**
     * @Description 根据住院管理id查询是否存在
     * @Params: [hisHospitalManageId]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/15
     * @Time 17:54
     **/
    HisNursingAssessment selectByhisHospitalManageId(Long hisHospitalManageId);

    /**
     * @Description 根据住院number查询入院护理信息
     * @Params: [number]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisNursingAssessment
     * @Date 2019/9/15
     * @Time 18:48
     **/
    HisNursingAssessment selectByNumber(String number);
}