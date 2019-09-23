package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisAnkle;
import core.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisAnkleMapper extends BaseMapper<HisAnkle>{
    int deleteByPrimaryKey(Long id);

    int insert(HisAnkle record);

    int insertSelective(HisAnkle record);

    HisAnkle selectByPrimaryKey(Long id);
    HisAnkle selectByMedicalRecordId(Long medicalRecordId);

    int updateByPrimaryKeySelective(HisAnkle record);

    int updateByPrimaryKey(HisAnkle record);

    List<? extends HisAnkle> listByRecordId(PageBean<HisAnkle> pageBean);
    List<? extends HisAnkle> listByMedicalRecordId(PageBean<HisAnkle> pageBean);

    List<HisAnkle> selectNumberByRecordId(String id);

    void updateBatch(List<HisAnkle> hisAnkleList);




}