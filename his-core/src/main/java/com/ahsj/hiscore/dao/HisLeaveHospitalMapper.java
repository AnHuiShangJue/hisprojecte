package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisLeaveHospital;

public interface HisLeaveHospitalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisLeaveHospital record);

    int insertSelective(HisLeaveHospital record);

    HisLeaveHospital selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisLeaveHospital record);

    int updateByPrimaryKey(HisLeaveHospital record);

    /**
     * @Description 根据住院管理id查询记录
     * @Params: [id]
     * @Author: dingli
     * @Return: com.ahsj.hiscore.entity.HisLeaveHospital
     * @Date 2019/9/14
     * @Time 17:31
     **/
    HisLeaveHospital selectByHisHospitalManageId(String number);
}