package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisUserDoctor;

public interface HisUserDoctorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisUserDoctor record);

    int insertSelective(HisUserDoctor record);

    HisUserDoctor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisUserDoctor record);

    int updateByPrimaryKey(HisUserDoctor record);

    /**
     * @Description 根据医生id查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:39
     * @Return com.ahsj.hiscore.entity.HisUserDoctor
     * @Params [id]
    **/
    HisUserDoctor selectByDoctorId (Long id);

    /**
     * @Description 根据usetId查询
     * @Author  muxu
     * @Date  2019/9/11
     * @Time 14:40
     * @Return com.ahsj.hiscore.entity.HisUserDoctor
     * @Params [id]
    **/
    HisUserDoctor selectByUserId (Long id);
}