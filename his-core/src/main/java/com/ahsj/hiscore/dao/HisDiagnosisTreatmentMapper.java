package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisDiagnosisTreatment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HisDiagnosisTreatmentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(HisDiagnosisTreatment hisDiagnosisTreatment);

    int insertSelective(HisDiagnosisTreatment hisDiagnosisTreatment);

    HisDiagnosisTreatment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisDiagnosisTreatment hisDiagnosisTreatment);

    int updateByPrimaryKey(HisDiagnosisTreatment hisDiagnosisTreatment);
}