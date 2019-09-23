package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisRelatedMedicationandexit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisRelatedMedicationandexitMapper extends BaseMapper<HisRelatedMedicationandexit>{
    int deleteByPrimaryKey(Long id);

    int insert(HisRelatedMedicationandexit record);

    int insertSelective(HisRelatedMedicationandexit record);

    HisRelatedMedicationandexit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisRelatedMedicationandexit record);

    int updateByPrimaryKey(HisRelatedMedicationandexit record);

    void insertBatch(List<HisRelatedMedicationandexit> hisRelatedMedicationandexitList);


}