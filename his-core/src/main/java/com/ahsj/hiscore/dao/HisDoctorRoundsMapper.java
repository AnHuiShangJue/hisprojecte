package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDoctorRounds;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HisDoctorRoundsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisDoctorRounds record);

    int insertSelective(HisDoctorRounds record);

    HisDoctorRounds selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDoctorRounds record);

    int updateByPrimaryKey(HisDoctorRounds record);
}