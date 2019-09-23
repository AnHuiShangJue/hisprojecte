package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.PreoperativeSummaryRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PreoperativeSummaryRecordMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PreoperativeSummaryRecord preoperativeSummaryRecord);

    int insertSelective(PreoperativeSummaryRecord preoperativeSummaryRecord);

    PreoperativeSummaryRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PreoperativeSummaryRecord preoperativeSummaryRecord);

    int updateByPrimaryKey(PreoperativeSummaryRecord preoperativeSummaryRecord);
}