package com.ahsj.hiscore.dao;

import com.ahsj.hiscore.entity.HisConsultationNote;

public interface HisConsultationNoteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HisConsultationNote record);

    int insertSelective(HisConsultationNote record);

    HisConsultationNote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HisConsultationNote record);

    int updateByPrimaryKey(HisConsultationNote record);
}