package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisNursingRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HisNursingRecordMapper extends BaseMapper<HisNursingRecord>{
    int deleteByPrimaryKey(Long id);

    int insert(HisNursingRecord record);

    int insertSelective(HisNursingRecord record);

    HisNursingRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisNursingRecord record);

    int updateByPrimaryKey(HisNursingRecord record);

    List<HisNursingRecord> listByManageNumber(String manageNumber);

    List<HisNursingRecord> list(HisNursingRecord hisNursingRecord);
}